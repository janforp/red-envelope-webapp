package com.iask.red_envelope.service;

import com.iask.red_envelope.config.Config;
import com.iask.red_envelope.consts.CacheConsts;
import com.iask.red_envelope.consts.WeixinConsts;
import com.iask.red_envelope.model.dto.WxNotShareSign;
import com.wujie.common.redis.StringKeyRedisTemplate;
import com.wujie.common.sdk.weixin.official_account.AutoRetryWeixinClient;
import com.wujie.common.sdk.weixin.official_account.DefaultWeixinClient;
import com.wujie.common.sdk.weixin.official_account.request.ticket.GetJsApiTicketRequest;
import com.wujie.common.sdk.weixin.official_account.request.token.TokenRequest;
import com.wujie.common.sdk.weixin.official_account.response.ticket.GetJsApiTicketResponse;
import com.wujie.common.sdk.weixin.official_account.response.token.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 * Created by Jan on 16/8/30.
 *
 * jsapi_ticket及access_token在缓存中的存取
 */
@Service
public class TicketCacheService {

    @Autowired
    private StringKeyRedisTemplate cacheRedisTemplate_ticket;

    private static final String APPID = Config.getWeixinLoginCfgInWeixinBrowser().getAppId();
    private static final String  APPSECRET = Config.getWeixinLoginCfgInWeixinBrowser().getAppSecret();

    /**
     * 存token
     * @param token
     */
    public void saveAccessToken(String token){
        cacheRedisTemplate_ticket.setex(CacheConsts.CACHE_TOKEN , token , CacheConsts.SECONDS_OF_7000);
    }


    /**
     * 取token
     * @return
     */
    public String getAccessToken(){

        String token = cacheRedisTemplate_ticket.getObj(CacheConsts.CACHE_TOKEN) ;

        if (token == null) {
            AutoRetryWeixinClient client = new AutoRetryWeixinClient(APPID , APPSECRET);
            TokenRequest request = new TokenRequest(APPID, APPSECRET);
            TokenResponse response = client.execute(request);
            token = response.getToken().getAccess_token();
            saveAccessToken(token);
        }

        return token;
    }


    /**
     * 存ticket
     * @param ticket
     */
    public void  saveJSApiTicket(String ticket) {
        cacheRedisTemplate_ticket.setex(CacheConsts.CACHE_TICKET , ticket , CacheConsts.SECONDS_OF_7000);
    }


    /**
     * 取ticket
     * @return
     */
    public String getJSApiTicket() {

        String ticket =  cacheRedisTemplate_ticket.getObj(CacheConsts.CACHE_TICKET);

        if (ticket == null) {
            GetJsApiTicketRequest request = new GetJsApiTicketRequest();
            DefaultWeixinClient client = new DefaultWeixinClient(APPID , APPSECRET) ;
            GetJsApiTicketResponse response = client.execute(request);
            ticket = response.getTicket();
            saveJSApiTicket(ticket);
        }

        return ticket;
    }


    /**
     * 签名
     * @param shareSign
     * @return
     */
    public String createSign(WxNotShareSign shareSign) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        String ticket = getJSApiTicket();

        String before = WeixinConsts.sign_before_string
                .replace("TICKET",ticket)
                .replace("NONCESTR",shareSign.getNonceStr())
                .replace("TIMESTAMP",shareSign.getTimestamp())
                .replace("URL",shareSign.getUrl());

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
