package com.iask.red_envelope.model.vo;

/**
 * Created by Jan on 16/10/10.
 * 幸运大转盘随机中奖人
 */
public class RotateRandomVo {

    private String nickname;
    private String time;
    private String award;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }
}
