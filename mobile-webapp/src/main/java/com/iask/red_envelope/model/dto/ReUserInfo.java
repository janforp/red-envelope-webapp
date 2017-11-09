package com.iask.red_envelope.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Summer on 16/8/17.
 */
public class ReUserInfo implements Serializable {

    // 昵称，非空
    private String nickname;
    // 头像，可空
    private String portrait;
    // 账户余额
    private BigDecimal userMoney;
    // 账户积分
    private Integer userScore;
    // 连续签到次数
    private Integer signCount;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public BigDecimal getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(BigDecimal userMoney) {
        this.userMoney = userMoney;
    }

    public Integer getUserScore() {
        return userScore;
    }

    public void setUserScore(Integer userScore) {
        this.userScore = userScore;
    }

    public Integer getSignCount() {
        return signCount;
    }

    public void setSignCount(Integer signCount) {
        this.signCount = signCount;
    }
}
