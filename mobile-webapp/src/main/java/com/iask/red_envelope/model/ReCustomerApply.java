package com.iask.red_envelope.model;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-07-19
 */
public class ReCustomerApply implements java.io.Serializable {

    // Fields

    // id
    private Integer id;
    // 客户id
    private Integer customerId;
    // 链接参数
    private String drawParam;
    // 是否领取 0-未领取,1-已领取
    private Integer isDraw;

    // Constructors

    /**
     * default constructor
     */
    public ReCustomerApply() {
    }

    /**
     * full constructor
     */
    public ReCustomerApply(Integer customerId, String drawParam, Integer isDraw) {
        this.customerId = customerId;
        this.drawParam = drawParam;
        this.isDraw = isDraw;
    }

    // Property accessors

    /**
     * id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 客户id
     */
    public Integer getCustomerId() {
        return this.customerId;
    }

    /**
     * 客户id
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 链接参数
     */
    public String getDrawParam() {
        return this.drawParam;
    }

    /**
     * 链接参数
     */
    public void setDrawParam(String drawParam) {
        this.drawParam = drawParam;
    }

    /**
     * 是否领取 0-未领取,1-已领取
     */
    public Integer getIsDraw() {
        return this.isDraw;
    }

    /**
     * 是否领取 0-未领取,1-已领取
     */
    public void setIsDraw(Integer isDraw) {
        this.isDraw = isDraw;
    }

}