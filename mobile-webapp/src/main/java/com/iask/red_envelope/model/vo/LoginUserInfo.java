package com.iask.red_envelope.model.vo;

import java.io.Serializable;

/**
 * Created by wuqiang on 16-2-22.
 *
 * @author wuqiang
 */
public class LoginUserInfo implements Serializable {
    // 用户状态，非空，默认1（有效）;0：封号；1：有效；
    private Integer userStatus;

    //用户ID
    private Long userId;

    //意义等同于user_id，唯一，且一定不会发生改变，目前将会用在二维码上，意义类似与老师无忧App af_user.open_id
    private String afOpenId;


    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAfOpenId() {
        return afOpenId;
    }

    public void setAfOpenId(String afOpenId) {
        this.afOpenId = afOpenId;
    }
}
