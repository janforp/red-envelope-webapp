package com.iask.red_envelope.model.vo.sign;

import java.io.Serializable;

/**
 * Created by Jan on 16/11/16.
 * 签到页面数据
 */
public class SignPageVo implements Serializable {

    //连续签到天数
    private Integer count;
    //今日获得金币
    private Integer scoreToday;
    //累计获得金币
    private Integer totalScore;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getScoreToday() {
        return scoreToday;
    }

    public void setScoreToday(Integer scoreToday) {
        this.scoreToday = scoreToday;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }
}
