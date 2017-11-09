package com.iask.red_envelope.model.vo;

import java.math.BigDecimal;

/**
 * Created by Jan on 16/9/26.
 * 提现记录
 */
public class WithdrawRecordVo {

    private Integer id;
    //申请 金额
    private String  money;
    //2016-6-58
    private String date;
    //19:20:56
    private String time;
    //处理状态
    private String status;
    //提现类型
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
