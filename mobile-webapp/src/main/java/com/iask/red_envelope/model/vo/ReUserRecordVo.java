package com.iask.red_envelope.model.vo;

import java.io.Serializable;

/**
 * Created by Summer on 16/7/13.
 */
public class ReUserRecordVo implements Serializable {

    // 昵称
    private String userNickname;
    // 头像
    private String userImg;
    // 红包金额
    private String envelopeMoney;

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getEnvelopeMoney() {
        return envelopeMoney;
    }

    public void setEnvelopeMoney(String envelopeMoney) {
        this.envelopeMoney = envelopeMoney;
    }
}
