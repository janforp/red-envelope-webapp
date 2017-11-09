package com.iask.red_envelope.model.vo;

import java.io.Serializable;

/**
 * Created by Jan on 16/8/30.
 * 定时红包领取详情 分隔数据:
 *
 * 1000个红包, 4秒抢光!
 *
 * 1000个红包,剩余不足600个!
 */
public class DetailSeparateVo implements Serializable {

    // 0:抢完 , 1 : 未抢完
    private Integer status;
    // 总数
    private Integer amount;
    // 剩余数
    private Integer remaind;
    // 耗时
    private long time;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getRemaind() {
        return remaind;
    }

    public void setRemaind(Integer remaind) {
        this.remaind = remaind;
    }


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
