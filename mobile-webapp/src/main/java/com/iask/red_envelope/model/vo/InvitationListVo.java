package com.iask.red_envelope.model.vo;

import java.io.Serializable;

/**
 * Created by Jan on 16/9/21.
 * 我的邀请列表
 */
public class InvitationListVo implements Serializable {

    private Integer userId;
    private String icon;
    private String name;
    private String time;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
