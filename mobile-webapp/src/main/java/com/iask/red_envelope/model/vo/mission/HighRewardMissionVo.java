package com.iask.red_envelope.model.vo.mission;

import java.io.Serializable;

/**
 * Created by Janita on 2016/12/17.
 * 高额任务详情页面
 */
public class HighRewardMissionVo implements Serializable {

    private Long missionId;

    private String banner;

    private String missionTitle;

    private String missionReward;

    private String merchantIcon;

    private String merchantName;

    private String missionDetail;

    private Integer participantsNum;
    // 状态 0-进行中 1-审核中 2-审核通过 3-未通过 4-可以领取,5-已结束
    private Integer taskStatus;

    //状态 0-进行中 1-审核中 2-审核通过 3-未通过 4-可以领取,5-已结束
    private String buttonName;

    private Long  taskId;

    private String verifyRequire;

    private String verifyRemark;

    private Integer imgNum;

    public Integer getImgNum() {
        return imgNum;
    }

    public void setImgNum(Integer imgNum) {
        this.imgNum = imgNum;
    }

    public String getVerifyRemark() {
        return verifyRemark;
    }

    public void setVerifyRemark(String verifyRemark) {
        this.verifyRemark = verifyRemark;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getVerifyRequire() {
        return verifyRequire;
    }

    public void setVerifyRequire(String verifyRequire) {
        this.verifyRequire = verifyRequire;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getMissionTitle() {
        return missionTitle;
    }

    public void setMissionTitle(String missionTitle) {
        this.missionTitle = missionTitle;
    }

    public String getMissionReward() {
        return missionReward;
    }

    public void setMissionReward(String missionReward) {
        this.missionReward = missionReward;
    }

    public String getMerchantIcon() {
        return merchantIcon;
    }

    public void setMerchantIcon(String merchantIcon) {
        this.merchantIcon = merchantIcon;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMissionDetail() {
        return missionDetail;
    }

    public void setMissionDetail(String missionDetail) {
        this.missionDetail = missionDetail;
    }

    public Integer getParticipantsNum() {
        return participantsNum;
    }

    public void setParticipantsNum(Integer participantsNum) {
        this.participantsNum = participantsNum;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }
}
