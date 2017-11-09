package com.iask.red_envelope.model;

import java.math.*;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-10-17
 */
public class ReAppKeywords implements java.io.Serializable {

    // Fields

    // id
    private Long keywordId;
    // 此关键词对应的appID
    private Long appId;
    // 关键词
    private String keyword;
    // 金额
    private BigDecimal money;
    // 总次数
    private Integer totalNum;
    // 剩余次数
    private Integer leftNum;
    // 标签
    private String taskLabel;
    // 投放时间,如:2016-08-18 12:53:30
    private String releaseTime;
    // 创建时间,如:2016-08-18 12:53:30
    private String createTime;

    // Constructors

    /**
     * default constructor
     */
    public ReAppKeywords() {
    }

    /**
     * full constructor
     */
    public ReAppKeywords(Long appId, String keyword, BigDecimal money, Integer totalNum, Integer leftNum, String taskLabel, String releaseTime, String createTime) {
        this.appId = appId;
        this.keyword = keyword;
        this.money = money;
        this.totalNum = totalNum;
        this.leftNum = leftNum;
        this.taskLabel = taskLabel;
        this.releaseTime = releaseTime;
        this.createTime = createTime;
    }

    // Property accessors

    /**
     * id
     */
    public Long getKeywordId() {
        return this.keywordId;
    }

    /**
     * id
     */
    public void setKeywordId(Long keywordId) {
        this.keywordId = keywordId;
    }

    /**
     * 此关键词对应的appID
     */
    public Long getAppId() {
        return this.appId;
    }

    /**
     * 此关键词对应的appID
     */
    public void setAppId(Long appId) {
        this.appId = appId;
    }

    /**
     * 关键词
     */
    public String getKeyword() {
        return this.keyword;
    }

    /**
     * 关键词
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * 金额
     */
    public BigDecimal getMoney() {
        return this.money;
    }

    /**
     * 金额
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * 总次数
     */
    public Integer getTotalNum() {
        return this.totalNum;
    }

    /**
     * 总次数
     */
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * 剩余次数
     */
    public Integer getLeftNum() {
        return this.leftNum;
    }

    /**
     * 剩余次数
     */
    public void setLeftNum(Integer leftNum) {
        this.leftNum = leftNum;
    }

    /**
     * 标签
     */
    public String getTaskLabel() {
        return this.taskLabel;
    }

    /**
     * 标签
     */
    public void setTaskLabel(String taskLabel) {
        this.taskLabel = taskLabel;
    }

    /**
     * 投放时间,如:2016-08-18 12:53:30
     */
    public String getReleaseTime() {
        return this.releaseTime;
    }

    /**
     * 投放时间,如:2016-08-18 12:53:30
     */
    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
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

}