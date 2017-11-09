package com.iask.red_envelope.service;

import com.iask.red_envelope.config.Config;
import com.iask.red_envelope.consts.WeixinConsts;
import com.iask.red_envelope.model.dto.WxNotShareSign;
import com.iask.red_envelope.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 * Created by Jan on 16/8/30.
 * 微信无法分享的签名生成
 */
@Service
public class WxNotShareService {

    @Autowired
    private TicketCacheService ticketCacheService;

    private  static final String APPID = Config.getWeixinLoginCfgInWeixinBrowser().getAppId();

    /**
     * 生成微信页面签名
     * @param request
     * @return
     */
    public WxNotShareSign getSign(HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        WxNotShareSign shareSign = new WxNotShareSign();

        String url = request.getRequestURL().toString();
        /**
         * 确保你获取用来签名的url是动态获取的，
         * 动态页面可参见实例代码中php的实现方式。
         * 如果是html的静态页面在前端通过ajax将url传到后台签名，
         * 前端需要用js获取当前页面除去'#'hash部分的链接（可用location.href.split('#')[0]获取,
         * 而且需要encodeURIComponent），因为页面一旦分享，微信客户端会在你的链接末尾加入其它参数，
         * 如果不是动态获取当前链接，将导致分享后的页面签名失败
         */
        url = url.split("#")[0];

        shareSign.setUrl(url);

        shareSign.setNonceStr(RandomUtil.getRandomString(16));

        shareSign.setAppId(APPID);

        Long time = System.currentTimeMillis()/1000;
        shareSign.setTimestamp(time.toString());

        String sign = createSign(shareSign);
        shareSign.setSignature(sign);

//        System.out.println("nonceStr==="+shareSign.getNonceStr());
//        System.out.println("timeStamp==="+shareSign.getTimestamp());
//        System.out.println("url==="+shareSign.getUrl());
//        System.out.println("sign==="+shareSign.getSignature());
//        System.out.println("APPID==="+APPID);

        return shareSign;
    }

    /**
     * 签名
     * @param shareSign
     * @return
     */
    public String createSign(WxNotShareSign shareSign) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        String ticket = ticketCacheService.getJSApiTicket();

        String before = WeixinConsts.sign_before_string
                        .replace("TICKET",ticket)
                        .replace("NONCESTR",shareSign.getNonceStr())
                        .replace("TIMESTAMP",shareSign.getTimestamp())
                        .replace("URL",shareSign.getUrl());

//        String after = SHA1.encode(before);

        String after = shaSign(before);
        return after;
    }




    public String shaSign(String sign) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.update(sign.getBytes("UTF-8"));
        sign = byteToHex(crypt.digest());

        return sign;
    }

    public static String byteToHex(final  byte[] hash){

        Formatter formatter = new Formatter();
        for (byte b: hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();;
        return result;
    }

}
