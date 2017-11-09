package com.iask.red_envelope.consts;


/**
 * Created by wuqiang on 15-8-6.
 *
 * @author wuqiang
 */
public class RequestConsts {
    /**
     * 请求相关的编码方式
     */
    public static final String CHARSET = "utf-8";
    /**
     * 如果采用iframe异步提交时，需要自动带入此参数用于标记此请求也是ajax请求
     */
    public static final String IFRAME_AJAX_MARK_PARAM_NAME = "_ajax";
    /**
     * 标记用户需要登录的json错误代码(code)
     */
    public static final String ERROR_CODE_USER_LOGIN_REQUIRED = "user_login_required";
    /**
     * Controller方法的@RequestMapping{produces = RequestConsts.CONTENT_TYPE_JSON}
     */
    public static final String CONTENT_TYPE_JSON = "application/json;charset=" + CHARSET;
    public static final String CONTENT_TYPE_TEXT = "text/plain;charset=" + CHARSET;
    public static final String CONTENT_TYPE_HTML = "text/html;charset=" + CHARSET;
    public static final String CONTENT_TYPE_JAVASCRIPT = "application/x-javascript;charset=" + CHARSET;

    /**
     * 请求头中"userKey"的名称
     */
    public static final String HEADER_USER_KEY = "uk";

    /**
     * 只有当响应数据为密文时，采用这个key封装<p/>
     * requestBody用这个参数名来包装加密数据<p/>
     * 同时全局响应的加密数据，需要做成一个JSON对象:{data:"密文数据"}
     * <p/>
     * 注意：这个值一定要和EncryptDataDto.data的属性名相同
     */
    public static final String GLOBAL_BUSINESS_PARAMETER_DATA_NAME = "data";





    /**
     * 首页的路径
     */
    public static final String INDEX_PAGE = "/c/p/mission/index";
    /**
     * 首页的URL路径
     */
    public static final String RESULT_REDIRECT_INDEX_PAGE = "redirect:" + INDEX_PAGE;
    /**
     * 404错误页的路径（是Controller中返回的路径，而不是url）
     */
    public static final String RESULT_ERROR_404_PAGE = "error/404";
    /**
     * 404错误页的URL路径
     */
    public static final String RESULT_REDIRECT_ERROR_404_PAGE = "redirect:/c/p/error/404";
    /**
     * 500错误页的路径（是Controller中返回的路径，而不是url）
     */
    public static final String RESULT_ERROR_500_PAGE = "error/500";
    /**
     * 500错误页的URL路径
     */
    public static final String RESULT_REDIRECT_ERROR_500_PAGE = "redirect:/c/p/error/500";
    /**
     * 403 Forbidden错误页的路径（是Controller中返回的路径，而不是url）
     */
    public static final String RESULT_ERROR_403_PAGE = "error/403";
    /**
     * 403 Forbidden错误页的URL路径
     */
    public static final String RESULT_REDIRECT_ERROR_403_PAGE = "redirect:/c/p/error/403";
    /**
     * 登录页的URL路径（会进一步跳转到微信授权登录页，不获取用户信息）
     */
    public static final String RESULT_REDIRECT_LOGIN_PAGE_SNSAPI_BASE = "redirect:/c/p/login/weixin/base";
    /**
     * 登录页的URL路径（会进一步跳转到微信授权登录页，会获取用户信息）
     */
    public static final String RESULT_REDIRECT_LOGIN_PAGE_SNSAPI_USERINFO = "redirect:/c/p/login/weixin/userinfo";
    /**
     * 登录页的URL路径（会进一步跳转到微信授权登录页，不获取用户信息，且带有登录成功后，重定向的页面）
     */
    public static final String RESULT_REDIRECT_LOGIN_PAGE_SNSAPI_BASE_WITH_REDIRECT_URI = "redirect:/c/p/login/weixin/base?" + ParamConsts.uri + "=REDIRECT_URI";
    /**
     * 登录页的URL路径（会进一步跳转到微信授权登录页，会获取用户信息，且带有登录成功后，重定向的页面）
     */
    public static final String RESULT_REDIRECT_LOGIN_PAGE_SNSAPI_USERINFO_WITH_REDIRECT_URI = "redirect:/c/p/login/weixin/userinfo?" + ParamConsts.uri + "=REDIRECT_URI";

    public static final String WEIXIN_CALLBACK_IN_WEIXIN_BROWSER_PATH = "/c/p/callback/weixin";

    public static final String BIND_WX_CALLBACK_PATH = "/c/p/callback/bindWx";

    public static final String COIN_WX_CALLBACK_PATH = "/c/p/callback/coin/";

    public static final String SHARE_MISSION_CALLBACK_PATH = "/c/p/callback/share/";
    /**
     * app上的分享点击任务,回调地址
     */
    public static final String APP_SHARE_CLICK_MISSION_CALLBACK_PATH = "/c/p/callback/appShare/";

    public static final String WEIXIN_CALLBACK_IN_COMMON_BROWSER_PATH = "/c/p/callback/weixinscan";
    /**
     * 登录页面的路径
     */
    public static final String LOGIN_PAGE = "/c/p/login/index";

    /**
     * 个人中心的路径
     */
    public static final String USER_INDEX_PAGE = "/c/p/user/index";
    /**
     * 个人中心的URL路径
     */
    public static final String RESULT_REDIRECT_USER_INDEX_PAGE = "redirect:" + USER_INDEX_PAGE;


    /**
     * 登录页的URL路径（会进一步跳转到微信授权登录页，不获取用户信息，且带有登录成功后，重定向的页面）
     */
    public static final String RESULT_REDIRECT_LOGIN_PAGE_SNSAPI_BASE_WITH_REDIRECT_URI_1 = "redirect:/c/p/login/weixin/base?" + ParamConsts.uri + "=";


    /**
     * Request属性范围中"REQUEST_TYPE"的名称，意义：该请求的请求数据采用的数据方式；0：明文；1：AES加密；
     */
    public static final String ATTR_REQUEST_TYPE = "REQUEST_TYPE";
    /**
     * Request属性范围中"RESPONSE_DATA_TYPE"的名称，意义：该请求的响应数据应当采用的数据方式；0：明文；1：AES加密；
     * <p/>
     * 注：如果发生UnableEncryptResponseException异常，无论应当采用何种方式都采用原文方式响应，并把响应头中的data-type设置为0
     */
    public static final String ATTR_RESPONSE_DATA_TYPE = "RESPONSE_DATA_TYPE";
    /**
     * 响应头中"etype"的名称：响应头中如果没有此属性表明是原文响应；0：明文；1：AES加密；
     */
    public static final String RESPONSE_HEADER_RESPONSE_DATA_TYPE = "data-type";
    /**
     * 响应头中"data-type"和request属性"REQUEST_DATA_TYPE"的对应的值：定义数据方式为明文
     */
    public static final String VALUE_DATA_TYPE_PUB = "0";
    /**
     * 响应头中"data-type"和request属性"REQUEST_DATA_TYPE"的对应的值：定义数据方式为AES加密
     */
    public static final String VALUE_DATA_TYPE_AES = "1";
    /**
     * 请求类型是：全明文类型请求
     */
    public static final Integer VALUE_REQUEST_TYPE_PUB = 0;
    /**
     * 请求类型是：使用userSecret作为密钥AES双向加密请求
     */
    public static final Integer VALUE_REQUEST_TYPE_AES_USER_SECRET_FULL = 1;
    /**
     * 请求类型是：使用userSecret作为密钥AES单向加密请求（请求加密，响应不加密）
     */
    public static final Integer VALUE_REQUEST_TYPE_AES_USER_SECRET_SEMI = 2;
    /**
     * Request属性范围中"userId"的名称
     */
    public static final String ATTR_USER_ID = "USER_ID";
    /**
     * Request属性范围中"userKey"的名称
     */
    public static final String ATTR_USER_KEY = "USER_KEY";
    /**
     * Request属性范围中"version"的名称
     */
    public static final String ATTR_VERSION = "ATTR_VERSION";
    /**
     * Request属性范围中"os"的名称
     */
    public static final String ATTR_OS = "ATTR_OS";
    /**
     * Request属性范围中"ma"的名称
     */
    public static final String ATTR_MANUFACTURER = "ATTR_MANUFACTURER";
    /**
     * Request属性范围中"deviceId"的名称
     */
    public static final String ATTR_DEVICE_ID = "ATTR_DEVICE_ID";
}
