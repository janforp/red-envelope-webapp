package com.iask.red_envelope.consts;

/**
 * Created by wuqiang on 15-8-11.
 *
 * @author wuqiang
 */
public class CacheConsts {

    public static final long SECONDS_OF_HALF_MINUTE = 30;
    public static final long SECONDS_OF_ONE_MINUTE = 60;
    public static final long SECONDS_OF_TEN_MINUTE = 60 * 10;
    public static final long SECONDS_OF_HALF_HOUR = 60 * 30;
    public static final long SECONDS_OF_ONE_HOUR = 60 * 60;
    public static final long SECONDS_OF_ONE_DAY = 60 * 60 * 24;
    public static final long SECONDS_OF_THREE_DAY = 60 * 60 * 24 * 3;
    public static final long SECONDS_OF_ONE_WEEK = 60 * 60 * 24 * 7;

    public static final long SECONDS_OF_7000 = 7000L;

    public static final String CustomerCacheVo = "customer_";

    public static final String userId_reqId = "r";

    public static final String weixinOfficialAccountAccessToken = "weixinOfficialAccountAccessToken_";

    // WxmsgKeywordReplyRuleMinDto的缓存；格式：kr{ruleId}
    public static final String wxmsgKeywordReplyRuleMinDto = "kr";

    /**
     * keys for cache
     */
    public static final String CACHE_USER_KEY = "uk_";     // 用户秘钥对象
    public static final String CACHE_USER = "usr";        // 用户基本信息
    public static final String CACHE_TEACHER_IMAGE_PREPARE_JSON_RESULT = "p_";     // 用户秘钥对象


    public static final String  CACHE_TOKEN = "wx_js_token"; //access_token

    public static final String  CACHE_TICKET = "wx_js_ticket" ;  //jsapi_ticket


    public static final String   CACHE_SHARE_USER_ID = "share_user_id"; //分享用户ID
    public static final String   CACHE_SHARE_MISSION_ID = "share_mission_id"; //分享任务ID

}
