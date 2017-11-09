package com.iask.red_envelope.model.vo;

import java.math.BigDecimal;

/**
 * Created by Jan on 16/10/11.
 * 定时红包
 */
public class GrabFixRedVo {
    //1:抢到了,0:没有抢到
    private Integer status;
    //金额
    private BigDecimal money;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
