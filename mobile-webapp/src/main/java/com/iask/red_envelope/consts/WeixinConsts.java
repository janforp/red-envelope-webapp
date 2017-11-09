package com.iask.red_envelope.consts;

/**
 * Created by craig on 16/2/14.
 */
public class WeixinConsts {
    /**
     * 在微信浏览器中授权登录，获取code的地址
     */
    public static final String WEIXIN_GET_CODE_URL_IN_WEIXIN_BROWSER = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
            "appid=APPID&" +
            "redirect_uri=REDIRECT_URI&" +
            "response_type=code&" +
            "scope=SCOPE&" +
            "state=STATE#wechat_redirect";
    /**
     * 在普通浏览器中扫描二维码授权登录，获取code的地址
     * APPID要使用老师无忧网页版的APPID，而不能直接使用公众号的，否则会报错：Scope 参数错误或没有 Scope 权限
     * <p/>
     * 应用授权作用域，拥有多个作用域用逗号（,）分隔，网页应用目前仅填写snsapi_login即可
     */
    public static final String WEIXIN_GET_CODE_URL_IN_COMMON_BROWSER = "https://open.weixin.qq.com/connect/qrconnect?" +
            "appid=APPID&" +
            "redirect_uri=REDIRECT_URI&" +
            "response_type=code&" +
            "scope=snsapi_login&" +
            "state=STATE#wechat_redirect";
    public static final String scope_snsapi_base = "snsapi_base";
    public static final String scope_snsapi_userinfo = "snsapi_userinfo";

    /**
     * 生成微信分享签名之前字符串
     */
    public static final String sign_before_string = "jsapi_ticket=TICKET&noncestr=NONCESTR&timestamp=TIMESTAMP&url=URL";
}
