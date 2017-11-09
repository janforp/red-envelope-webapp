package com.iask.red_envelope.model;

import java.math.*;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-10-26
 */
public class WxShareDetail implements java.io.Serializable {

    // Fields

    // ID
    private Long detailId;
    // 分享用户id
    private Long userId;
    // 任务id
    private Long missionId;
    // 金额
    private BigDecimal money;
    // 时间,如:2016-08-18 12:53:30
    private String detailTime;

    // Constructors

    /**
     * default constructor
     */
    public WxShareDetail() {
    }

    /**
     * full constructor
     */
    public WxShareDetail(Long userId, Long missionId, BigDecimal money, String detailTime) {
        this.userId = userId;
        this.missionId = missionId;
        this.money = money;
        this.detailTime = detailTime;
    }

    // Property accessors

    /**
     * ID
     */
    public Long getDetailId() {
        return this.detailId;
    }

    /**
     * ID
     */
    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    /**
     * 分享用户id
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * 分享用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 任务id
     */
    public Long getMissionId() {
        return this.missionId;
    }

    /**
     * 任务id
     */
    public void setMissionId(Long missionId) {
        this.missionId = missionId;
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
     * 时间,如:2016-08-18 12:53:30
     */
    public String getDetailTime() {
        return this.detailTime;
    }

    /**
     * 时间,如:2016-08-18 12:53:30
     */
    public void setDetailTime(String detailTime) {
        this.detailTime = detailTime;
    }

}