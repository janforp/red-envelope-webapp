package com.iask.red_envelope.util.weixin;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 各种签名相关的工具
 *
 * @author Administrator
 */
public final class WeixinMsgUtil {
    /**
     * 微信验证此地址
     * <p/>
     * 不要使用WXBizMsgCrypt.verifyUrl，经测试无法验证通过
     *
     * @param serverToken
     * @param request
     * @return
     * @throws IOException
     */
    public static String verifyUrl(String serverToken, HttpServletRequest request) throws IOException {
        if (serverToken == null) {
            return "fail";
        }
        String signature = request.getParameter("signature");// 微信加密签名
        String timestamp = request.getParameter("timestamp");// 时间戳
        String nonce = request.getParameter("nonce");// 随机数
        String echostr = request.getParameter("echostr");//
        if (signature == null || timestamp == null || nonce == null) {
            return "fail";
        }
        // 验证
        if (WeixinMsgUtil.checkSignForVerifyUrl(serverToken, signature, timestamp, nonce)) {
            return String.valueOf(echostr);
        } else {
            return "fail";
        }
    }

    /**
     * 验证微信开发者模式时，第一次验证请求的签名（从微信服务器推送来的）
     *
     * @param token
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    private static final boolean checkSignForVerifyUrl(String token, String signature,
                                                       String timestamp, String nonce) {
        List<String> params = new ArrayList<String>();
        params.add(token);
        params.add(timestamp);
        params.add(nonce);
        Collections.sort(params, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        String temp = params.get(0) + params.get(1) + params.get(2);
        return SHA1.encode(temp).equals(signature);
    }
}