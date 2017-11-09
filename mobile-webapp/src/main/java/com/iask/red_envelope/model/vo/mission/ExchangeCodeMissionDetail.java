package com.iask.red_envelope.model.vo.mission;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Jan on 16/11/10.
 * 兑换码任务页面详情数据
 */
public class ExchangeCodeMissionDetail extends MissionDetail implements Serializable {
    // id，自增长
    private Long missionId;
    // 任务分类,0:推荐任务, 1:兑换码红包
    private Integer missionType;
    // 任务图标
    private String missionIcon;
    // 任务名字
    private String missionTitle;
    // 任务标签
    private List<String> missionLabel;
    // 最小金额
    private BigDecimal minMoney;
    // 结束事件,如:2016-08-18 12:53:30
    private String endTime;
    // 剩余数量
    private Integer leftNum;
    // 任务简介
    private String missionDesc;
    // 兑换码
    private String exchangeCode;
    // 审核要求
    private String verifyRequire;
    // 金额状态 (0-金额固定, 1-金额不定)
    private int moneyStatus;

    public int getMoneyStatus() {
        return moneyStatus;
    }

    public void setMoneyStatus(int moneyStatus) {
        this.moneyStatus = moneyStatus;
    }

    public String getExchangeCode() {
        return exchangeCode;
    }

    public void setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
    }

    public String getVerifyRequire() {
        return verifyRequire;
    }

    public void setVerifyRequire(String verifyRequire) {
        this.verifyRequire = verifyRequire;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public Integer getMissionType() {
        return missionType;
    }

    public void setMissionType(Integer missionType) {
        this.missionType = missionType;
    }

    public String getMissionIcon() {
        return missionIcon;
    }

    public void setMissionIcon(String missionIcon) {
        this.missionIcon = missionIcon;
    }

    public String getMissionTitle() {
        return missionTitle;
    }

    public void setMissionTitle(String missionTitle) {
        this.missionTitle = missionTitle;
    }

    public List<String> getMissionLabel() {
        return missionLabel;
    }

    public void setMissionLabel(List<String> missionLabel) {
        this.missionLabel = missionLabel;
    }

    public BigDecimal getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(BigDecimal minMoney) {
        this.minMoney = minMoney;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getLeftNum() {
        return leftNum;
    }

    public void setLeftNum(Integer leftNum) {
        this.leftNum = leftNum;
    }

    public String getMissionDesc() {
        return missionDesc;
    }

    public void setMissionDesc(String missionDesc) {
        this.missionDesc = missionDesc;
    }
}
