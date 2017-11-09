package com.iask.red_envelope.model;

import java.math.*;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-11-07
 */
public class ReRecommendMission implements java.io.Serializable {

    // Fields

    // id，自增长
    private Long missionId;
    // 任务分类,0:推荐任务, 1:兑换码红包,2:注册类型的任务
    private Integer missionType;
    // 任务图标
    private String missionIcon;
    // 任务名字
    private String missionTitle;
    // 任务标签
    private String missionLabel;
    // 最小金额
    private BigDecimal minMoney;
    // 最大金额
    private BigDecimal maxMoney;
    // 开始事件,如:2016-08-18 12:53:30
    private String startTime;
    // 结束事件,如:2016-08-18 12:53:30
    private String endTime;
    // 总数量
    private Integer totalNum;
    // 剩余数量
    private Integer leftNum;
    // 任务简介
    private String missionDesc;
    // 显示平台, 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
    private Integer missionStatus;
    // 是否限时, 0:限时, 1:不限时
    private Integer isLimitTime;
    // 分钟
    private Integer limitMinute;
    // 是否需要审核, 0:审核, 1:不审核
    private Integer isVerify;
    // 文字要求
    private String verifyText;
    // 图片要求
    private String verifyImg;
    // 需审核图片
    private String missionImgs;
    // 任务排序,值较小者排在较前
    private Integer missionOrder;
    // 兑换码
    private String exchangeCode;
    // 审核要求
    private String verifyRequire;

    // Constructors

    /**
     * default constructor
     */
    public ReRecommendMission() {
    }

    /**
     * full constructor
     */
    public ReRecommendMission(Integer missionType, String missionIcon, String missionTitle, String missionLabel, BigDecimal minMoney, BigDecimal maxMoney, String startTime, String endTime, Integer totalNum, Integer leftNum, String missionDesc, Integer missionStatus, Integer isLimitTime, Integer limitMinute, Integer isVerify, String verifyText, String verifyImg, String missionImgs, Integer missionOrder, String exchangeCode, String verifyRequire) {
        this.missionType = missionType;
        this.missionIcon = missionIcon;
        this.missionTitle = missionTitle;
        this.missionLabel = missionLabel;
        this.minMoney = minMoney;
        this.maxMoney = maxMoney;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalNum = totalNum;
        this.leftNum = leftNum;
        this.missionDesc = missionDesc;
        this.missionStatus = missionStatus;
        this.isLimitTime = isLimitTime;
        this.limitMinute = limitMinute;
        this.isVerify = isVerify;
        this.verifyText = verifyText;
        this.verifyImg = verifyImg;
        this.missionImgs = missionImgs;
        this.missionOrder = missionOrder;
        this.exchangeCode = exchangeCode;
        this.verifyRequire = verifyRequire;
    }

    // Property accessors

    /**
     * id，自增长
     */
    public Long getMissionId() {
        return this.missionId;
    }

    /**
     * id，自增长
     */
    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    /**
     * 任务分类,0:推荐任务, 1:兑换码红包
     */
    public Integer getMissionType() {
        return this.missionType;
    }

    /**
     * 任务分类,0:推荐任务, 1:兑换码红包
     */
    public void setMissionType(Integer missionType) {
        this.missionType = missionType;
    }

    /**
     * 任务图标
     */
    public String getMissionIcon() {
        return this.missionIcon;
    }

    /**
     * 任务图标
     */
    public void setMissionIcon(String missionIcon) {
        this.missionIcon = missionIcon;
    }

    /**
     * 任务名字
     */
    public String getMissionTitle() {
        return this.missionTitle;
    }

    /**
     * 任务名字
     */
    public void setMissionTitle(String missionTitle) {
        this.missionTitle = missionTitle;
    }

    /**
     * 任务标签
     */
    public String getMissionLabel() {
        return this.missionLabel;
    }

    /**
     * 任务标签
     */
    public void setMissionLabel(String missionLabel) {
        this.missionLabel = missionLabel;
    }

    /**
     * 最小金额
     */
    public BigDecimal getMinMoney() {
        return this.minMoney;
    }

    /**
     * 最小金额
     */
    public void setMinMoney(BigDecimal minMoney) {
        this.minMoney = minMoney;
    }

    /**
     * 最大金额
     */
    public BigDecimal getMaxMoney() {
        return this.maxMoney;
    }

    /**
     * 最大金额
     */
    public void setMaxMoney(BigDecimal maxMoney) {
        this.maxMoney = maxMoney;
    }

    /**
     * 开始事件,如:2016-08-18 12:53:30
     */
    public String getStartTime() {
        return this.startTime;
    }

    /**
     * 开始事件,如:2016-08-18 12:53:30
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * 结束事件,如:2016-08-18 12:53:30
     */
    public String getEndTime() {
        return this.endTime;
    }

    /**
     * 结束事件,如:2016-08-18 12:53:30
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * 总数量
     */
    public Integer getTotalNum() {
        return this.totalNum;
    }

    /**
     * 总数量
     */
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * 剩余数量
     */
    public Integer getLeftNum() {
        return this.leftNum;
    }

    /**
     * 剩余数量
     */
    public void setLeftNum(Integer leftNum) {
        this.leftNum = leftNum;
    }

    /**
     * 任务简介
     */
    public String getMissionDesc() {
        return this.missionDesc;
    }

    /**
     * 任务简介
     */
    public void setMissionDesc(String missionDesc) {
        this.missionDesc = missionDesc;
    }

    /**
     * 显示平台, 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
     */
    public Integer getMissionStatus() {
        return this.missionStatus;
    }

    /**
     * 显示平台, 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
     */
    public void setMissionStatus(Integer missionStatus) {
        this.missionStatus = missionStatus;
    }

    /**
     * 是否限时, 0:限时, 1:不限时
     */
    public Integer getIsLimitTime() {
        return this.isLimitTime;
    }

    /**
     * 是否限时, 0:限时, 1:不限时
     */
    public void setIsLimitTime(Integer isLimitTime) {
        this.isLimitTime = isLimitTime;
    }

    /**
     * 分钟
     */
    public Integer getLimitMinute() {
        return this.limitMinute;
    }

    /**
     * 分钟
     */
    public void setLimitMinute(Integer limitMinute) {
        this.limitMinute = limitMinute;
    }

    /**
     * 是否需要审核, 0:审核, 1:不审核
     */
    public Integer getIsVerify() {
        return this.isVerify;
    }

    /**
     * 是否需要审核, 0:审核, 1:不审核
     */
    public void setIsVerify(Integer isVerify) {
        this.isVerify = isVerify;
    }

    /**
     * 文字要求
     */
    public String getVerifyText() {
        return this.verifyText;
    }

    /**
     * 文字要求
     */
    public void setVerifyText(String verifyText) {
        this.verifyText = verifyText;
    }

    /**
     * 图片要求
     */
    public String getVerifyImg() {
        return this.verifyImg;
    }

    /**
     * 图片要求
     */
    public void setVerifyImg(String verifyImg) {
        this.verifyImg = verifyImg;
    }

    /**
     * 需审核图片
     */
    public String getMissionImgs() {
        return this.missionImgs;
    }

    /**
     * 需审核图片
     */
    public void setMissionImgs(String missionImgs) {
        this.missionImgs = missionImgs;
    }

    /**
     * 任务排序,值较小者排在较前
     */
    public Integer getMissionOrder() {
        return this.missionOrder;
    }

    /**
     * 任务排序,值较小者排在较前
     */
    public void setMissionOrder(Integer missionOrder) {
        this.missionOrder = missionOrder;
    }

    /**
     * 兑换码
     */
    public String getExchangeCode() {
        return this.exchangeCode;
    }

    /**
     * 兑换码
     */
    public void setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
    }

    /**
     * 审核要求
     */
    public String getVerifyRequire() {
        return this.verifyRequire;
    }

    /**
     * 审核要求
     */
    public void setVerifyRequire(String verifyRequire) {
        this.verifyRequire = verifyRequire;
    }

}