package com.iask.red_envelope.model.vo.mission;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Jan on 16/11/19.
 * 注册高额任务:re_recommend_mission 表中type=2的
 */
public class RegisterMissionVo implements Serializable {

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
    // 是否限时, 0:限时, 1:不限时
    private Integer isLimitTime;
    // 是否需要审核, 0:审核, 1:不审核
    private Integer isVerify;
    // 文字要求
    private String verifyText;
    // 图片要求
    private String verifyImg;
    // 需审核图片
    private List<String> missionImgs;
    // 提交审核图片的张数
    private Integer imgNum;
    // 若此人已经抢了该任务,则要向页面传task_Id
    private Long taskId;
    // 状态 0-进行中 1-审核中 2-审核通过 3-未通过 4-已过期
    private Integer taskStatus;
    // 剩余时间
    private long leftTime;
    // 兑换码
    private String exchangeCode;
    // 审核要求
    private String verifyRequire;
    // 金额状态 (0-金额固定, 1-金额不定)
    private int moneyStatus;

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

    public Integer getIsLimitTime() {
        return isLimitTime;
    }

    public void setIsLimitTime(Integer isLimitTime) {
        this.isLimitTime = isLimitTime;
    }

    public Integer getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Integer isVerify) {
        this.isVerify = isVerify;
    }

    public String getVerifyText() {
        return verifyText;
    }

    public void setVerifyText(String verifyText) {
        this.verifyText = verifyText;
    }

    public String getVerifyImg() {
        return verifyImg;
    }

    public void setVerifyImg(String verifyImg) {
        this.verifyImg = verifyImg;
    }

    public List<String> getMissionImgs() {
        return missionImgs;
    }

    public void setMissionImgs(List<String> missionImgs) {
        this.missionImgs = missionImgs;
    }

    public Integer getImgNum() {
        return imgNum;
    }

    public void setImgNum(Integer imgNum) {
        this.imgNum = imgNum;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public long getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(long leftTime) {
        this.leftTime = leftTime;
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

    public int getMoneyStatus() {
        return moneyStatus;
    }

    public void setMoneyStatus(int moneyStatus) {
        this.moneyStatus = moneyStatus;
    }
}
