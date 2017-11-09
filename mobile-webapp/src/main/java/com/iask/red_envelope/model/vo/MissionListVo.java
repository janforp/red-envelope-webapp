package com.iask.red_envelope.model.vo;

import java.io.Serializable;

/**
 * Created by Jan on 16/8/10.
 *
 * 任务列表
 */
public class MissionListVo  implements Serializable {

    // 任务id
    private Integer missionId;
    // 任务名字
    private String missionName;
    // 任务图标
    private String missionImg;
    // 奖励,如:100元红包券,10元观影券,购物券等
    private String missionReward;
    // 参加人数
    private Integer participantsNum;
    // 结束时间
    private Integer endTime;

    //2016年12月1
    private String  showEndTime;

    public String getShowEndTime() {
        return showEndTime;
    }

    public void setShowEndTime(String showEndTime) {
        this.showEndTime = showEndTime;
    }

    public Integer getMissionId() {
        return missionId;
    }

    public void setMissionId(Integer missionId) {
        this.missionId = missionId;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public String getMissionImg() {
        return missionImg;
    }

    public void setMissionImg(String missionImg) {
        this.missionImg = missionImg;
    }

    public String getMissionReward() {
        return missionReward;
    }

    public void setMissionReward(String missionReward) {
        this.missionReward = missionReward;
    }

    public Integer getParticipantsNum() {
        return participantsNum;
    }

    public void setParticipantsNum(Integer participantsNum) {
        this.participantsNum = participantsNum;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }
}
