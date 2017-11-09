package com.iask.red_envelope.model;

import java.math.*;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-12-19
 */
public class ReLoanMall implements java.io.Serializable {

    // Fields

    // id，自增长
    private Long id;
    // 标题
    private String title;
    // 图标
    private String icon;
    // 描述
    private String desc;
    // 排序金额(万元),如:0.1(万)
    private BigDecimal orderMoney;
    // 展示金额,根据实际情况输入
    private String displayMoney;
    // 月利率
    private BigDecimal monthInterestRate;
    // 标签,用逗号分开
    private String labels;
    // 已申请人数
    private Integer participantsNum;
    // 点击按钮跳转的链接
    private String clickUrl;
    // 到账时间,如5,表示5天
    private Integer toAccountTime;
    // 创建时间,如:2016-08-18 12:53:30
    private String createTime;
    // 修改时间,如:2016-08-18 12:53:30
    private String updateTime;
    // 活动是否已经结束，0:已结束，1:进行中
    private Integer isEnd;

    // Constructors

    /**
     * default constructor
     */
    public ReLoanMall() {
    }

    /**
     * full constructor
     */
    public ReLoanMall(String title, String icon, String desc, BigDecimal orderMoney, String displayMoney, BigDecimal monthInterestRate, String labels, Integer participantsNum, String clickUrl, Integer toAccountTime, String createTime, String updateTime, Integer isEnd) {
        this.title = title;
        this.icon = icon;
        this.desc = desc;
        this.orderMoney = orderMoney;
        this.displayMoney = displayMoney;
        this.monthInterestRate = monthInterestRate;
        this.labels = labels;
        this.participantsNum = participantsNum;
        this.clickUrl = clickUrl;
        this.toAccountTime = toAccountTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isEnd = isEnd;
    }

    // Property accessors

    /**
     * id，自增长
     */
    public Long getId() {
        return this.id;
    }

    /**
     * id，自增长
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 标题
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 图标
     */
    public String getIcon() {
        return this.icon;
    }

    /**
     * 图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 描述
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * 描述
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 排序金额(万元),如:0.1(万)
     */
    public BigDecimal getOrderMoney() {
        return this.orderMoney;
    }

    /**
     * 排序金额(万元),如:0.1(万)
     */
    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    /**
     * 展示金额,根据实际情况输入
     */
    public String getDisplayMoney() {
        return this.displayMoney;
    }

    /**
     * 展示金额,根据实际情况输入
     */
    public void setDisplayMoney(String displayMoney) {
        this.displayMoney = displayMoney;
    }

    /**
     * 月利率
     */
    public BigDecimal getMonthInterestRate() {
        return this.monthInterestRate;
    }

    /**
     * 月利率
     */
    public void setMonthInterestRate(BigDecimal monthInterestRate) {
        this.monthInterestRate = monthInterestRate;
    }

    /**
     * 标签,用逗号分开
     */
    public String getLabels() {
        return this.labels;
    }

    /**
     * 标签,用逗号分开
     */
    public void setLabels(String labels) {
        this.labels = labels;
    }

    /**
     * 已申请人数
     */
    public Integer getParticipantsNum() {
        return this.participantsNum;
    }

    /**
     * 已申请人数
     */
    public void setParticipantsNum(Integer participantsNum) {
        this.participantsNum = participantsNum;
    }

    /**
     * 点击按钮跳转的链接
     */
    public String getClickUrl() {
        return this.clickUrl;
    }

    /**
     * 点击按钮跳转的链接
     */
    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    /**
     * 到账时间,如5,表示5天
     */
    public Integer getToAccountTime() {
        return this.toAccountTime;
    }

    /**
     * 到账时间,如5,表示5天
     */
    public void setToAccountTime(Integer toAccountTime) {
        this.toAccountTime = toAccountTime;
    }

    /**
     * 创建时间,如:2016-08-18 12:53:30
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * 创建时间,如:2016-08-18 12:53:30
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间,如:2016-08-18 12:53:30
     */
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 修改时间,如:2016-08-18 12:53:30
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 活动是否已经结束，0:已结束，1:进行中
     */
    public Integer getIsEnd() {
        return this.isEnd;
    }

    /**
     * 活动是否已经结束，0:已结束，1:进行中
     */
    public void setIsEnd(Integer isEnd) {
        this.isEnd = isEnd;
    }

}