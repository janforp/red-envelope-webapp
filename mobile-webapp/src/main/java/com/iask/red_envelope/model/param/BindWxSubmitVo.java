package com.iask.red_envelope.model.param;

/**
 * Created by Jan on 16/8/26.
 * 绑定微信 提交页面的参数
 */
public class BindWxSubmitVo {

    private String phone ;
    private String smsCode;
    private String openId;
    private String nickname;
    private String icon;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
