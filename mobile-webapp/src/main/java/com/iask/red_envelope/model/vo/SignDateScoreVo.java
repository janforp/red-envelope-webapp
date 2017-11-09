package com.iask.red_envelope.model.vo;

import java.io.Serializable;

/**
 * Created by Jan on 16/8/18.
 *
 * 签到页面的 7 个 圈圈的数据
 */
public class SignDateScoreVo  implements Serializable {

    private String date;

    private Integer score;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
