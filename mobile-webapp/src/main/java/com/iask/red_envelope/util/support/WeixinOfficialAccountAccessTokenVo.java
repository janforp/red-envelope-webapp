package com.iask.red_envelope.util.support;

import java.io.Serializable;

/**
 * Created by wuqiang on 16-2-29.
 *
 * @author wuqiang
 */
public class WeixinOfficialAccountAccessTokenVo implements Serializable {
    private String appid;
    private String accessToken;
    // 过期的具体时间点
    private Long expireTime;

    WeixinOfficialAccountAccessTokenVo(String appid, String accessToken, Long expireTime) {
        if (appid == null || appid.length() == 0) {
            throw new IllegalArgumentException("appid");
        }
        if (accessToken == null || accessToken.length() == 0) {
            throw new IllegalArgumentException("accessToken");
        }
        if (expireTime == null || expireTime <= 0) {
            throw new IllegalArgumentException("expireTime");
        }
        this.appid = appid;
        this.accessToken = accessToken;
        this.expireTime = expireTime;
    }

    public String getAppid() {
        return appid;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Long getExpireTime() {
        return expireTime;
    }
}
