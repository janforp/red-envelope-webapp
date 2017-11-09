package com.iask.red_envelope.model.vo;

import java.io.Serializable;

/**
 * Created by Summer on 16/7/13.
 */
public class RedVo implements Serializable {

    // id
    private Integer id;
    // 客户号
    private Integer customerId;
    // 头像
    private String customerImg;
    // 名称
    private String customerName;
    // 剩余个数
    private Integer redNumLeft;
    // 总个数
    private Integer redNumTotal;
    // 今天剩余个数
    private Integer redNumDayLeft;
    // 今天总个数
    private Integer redNumDayTotal;
    // 状态 0-结束, 1-进行中
    private Integer customerStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerImg() {
        return customerImg;
    }

    public void setCustomerImg(String customerImg) {
        this.customerImg = customerImg;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getRedNumDayLeft() {
        return redNumDayLeft;
    }

    public void setRedNumDayLeft(Integer redNumDayLeft) {
        this.redNumDayLeft = redNumDayLeft;
    }

    public Integer getRedNumDayTotal() {
        return redNumDayTotal;
    }

    public void setRedNumDayTotal(Integer redNumDayTotal) {
        this.redNumDayTotal = redNumDayTotal;
    }

    public Integer getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(Integer customerStatus) {
        this.customerStatus = customerStatus;
    }

    public Integer getRedNumLeft() {
        return redNumLeft;
    }

    public void setRedNumLeft(Integer redNumLeft) {
        this.redNumLeft = redNumLeft;
    }

    public Integer getRedNumTotal() {
        return redNumTotal;
    }

    public void setRedNumTotal(Integer redNumTotal) {
        this.redNumTotal = redNumTotal;
    }
}
