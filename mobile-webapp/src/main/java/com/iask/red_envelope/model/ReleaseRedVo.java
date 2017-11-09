package com.iask.red_envelope.model;

import java.io.Serializable;

/**
 * Created by Jan on 16/10/27.
 * 发放红包
 */
public class ReleaseRedVo implements Serializable {

    private Long redId;

    private Integer money;

    private Long  userId;

    private Long missionId;

    public Long getRedId() {
        return redId;
    }

    public void setRedId(Long redId) {
        this.redId = redId;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }
}
