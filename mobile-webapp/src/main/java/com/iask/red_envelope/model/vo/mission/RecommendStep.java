package com.iask.red_envelope.model.vo.mission;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jan on 16/11/2.
 */
public class RecommendStep implements Serializable{

    // id
    private Long missionId;
    // 任务步骤号
    private Integer stepNum;
    // 步骤内容
    private String stepContent;
    // 步骤图片
    private List<String> stepImgs;
    // 步骤按钮
    private String stepBtn;

    public String getStepBtn() {
        return stepBtn;
    }

    public void setStepBtn(String stepBtn) {
        this.stepBtn = stepBtn;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public Integer getStepNum() {
        return stepNum;
    }

    public void setStepNum(Integer stepNum) {
        this.stepNum = stepNum;
    }

    public String getStepContent() {
        return stepContent;
    }

    public void setStepContent(String stepContent) {
        this.stepContent = stepContent;
    }

    public List<String> getStepImgs() {
        return stepImgs;
    }

    public void setStepImgs(List<String> stepImgs) {
        this.stepImgs = stepImgs;
    }
}
