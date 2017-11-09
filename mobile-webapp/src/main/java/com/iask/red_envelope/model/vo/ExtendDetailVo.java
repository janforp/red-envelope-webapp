package com.iask.red_envelope.model.vo;

import java.io.Serializable;

/**
 * Created by Jan on 16/8/31.
 * 推广详情页数据
 */
public class ExtendDetailVo  implements Serializable {

    private Integer extendId;
    private String  customerImg;
    private String  customerName;
    private Integer redNumDayTotal;
    private Integer redNumDayLeft;
    private String  stepRule;

    public Integer getExtendId() {
        return extendId;
    }

    public void setExtendId(Integer extendId) {
        this.extendId = extendId;
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

    public Integer getRedNumDayTotal() {
        return redNumDayTotal;
    }

    public void setRedNumDayTotal(Integer redNumDayTotal) {
        this.redNumDayTotal = redNumDayTotal;
    }

    public Integer getRedNumDayLeft() {
        return redNumDayLeft;
    }

    public void setRedNumDayLeft(Integer redNumDayLeft) {
        this.redNumDayLeft = redNumDayLeft;
    }

    public String getStepRule() {
        return stepRule;
    }

    public void setStepRule(String stepRule) {
        this.stepRule = stepRule;
    }
}
