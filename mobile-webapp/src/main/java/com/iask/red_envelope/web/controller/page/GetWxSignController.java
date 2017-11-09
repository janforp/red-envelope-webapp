package com.iask.red_envelope.web.controller.page;

import com.iask.red_envelope.config.Config;
import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.consts.WeixinConsts;
import com.iask.red_envelope.model.dto.WxNotShareSign;
import com.iask.red_envelope.model.vo.WxSignVo;
import com.iask.red_envelope.service.TicketCacheService;
import com.iask.red_envelope.util.JsonUtil;
import com.iask.red_envelope.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 * Created by Jan on 16/10/26.
 * 获取微信前面对象
 */
@RequestMapping(value = "/p/wxSign", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class GetWxSignController {

    @Autowired
    private TicketCacheService ticketCacheService;

    /**
     * 微信签名
     * @param request
     * @param url
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/getSignVo")
    @ResponseBody
    public String getSignVo(HttpServletRequest request,
                            @RequestParam(value = "url")String url) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        String appId = Config.getWeixinLoginCfgInWeixinBrowser().getAppId();
        Long now = System.currentTimeMillis()/1000;
        String timestamp = now.toString();

        String nonceStr = RandomUtil.getRandomString(16);

        WxNotShareSign signVo = new WxNotShareSign();

        signVo.setAppId(appId);
        signVo.setNonceStr(nonceStr);
        signVo.setTimestamp(timestamp);
        signVo.setUrl(url);

        String  sign = ticketCacheService.createSign(signVo);

        signVo.setSignature(sign);

        String result =JsonUtil.buildSuccessData(signVo);

        System.out.println(result);

        return JsonUtil.buildSuccessData(signVo);
    }


}
