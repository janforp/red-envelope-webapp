package com.iask.red_envelope.model;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-10-26
 */
public class WxShareUser implements java.io.Serializable {

    // Fields

    // id，自增长
    private Long userId;
    // 微信openid
    private String openId;
    // 用户ip
    private String userIp;
    // 更新时间,如:2016-08-18 12:53:30
    private String updateTime;
    // 创建时间,如:2016-08-18 12:53:30
    private String createTime;

    // Constructors

    /**
     * default constructor
     */
    public WxShareUser() {
    }

    /**
     * full constructor
     */
    public WxShareUser(String openId, String userIp, String updateTime, String createTime) {
        this.openId = openId;
        this.userIp = userIp;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    // Property accessors

    /**
     * id，自增长
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * id，自增长
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 微信openid
     */
    public String getOpenId() {
        return this.openId;
    }

    /**
     * 微信openid
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 用户ip
     */
    public String getUserIp() {
        return this.userIp;
    }

    /**
     * 用户ip
     */
    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    /**
     * 更新时间,如:2016-08-18 12:53:30
     */
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 更新时间,如:2016-08-18 12:53:30
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 创建时间,如:2016-08-18 12:53:30
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * 创建时间,如:2016-08-18 12:53:30
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}