package com.iask.red_envelope.model.cache;

import java.io.Serializable;

/**
 * Created by Jan on 16/10/27.
 * 分享任务用户信息
 */
public class ShareUserInfo implements Serializable{

    private Long userId;
    private String ip;
    private String province;
    private String city;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
