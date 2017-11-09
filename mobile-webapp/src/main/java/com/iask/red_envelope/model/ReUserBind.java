package com.iask.red_envelope.model;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-08-26
 */
public class ReUserBind implements java.io.Serializable {

    // Fields

    // re_user.user_id，主键
    private Integer userId;
    // 绑定类型；0：微信，1：QQ，2：微博
    private Integer bindType;
    // 第三方登录获得的openId/uid
    private String bindId;
    // 创建时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
    private Long bindTime;

    // Constructors

    /**
     * default constructor
     */
    public ReUserBind() {
    }

    /**
     * full constructor
     */
    public ReUserBind(Integer userId, Integer bindType, String bindId, Long bindTime) {
        this.userId = userId;
        this.bindType = bindType;
        this.bindId = bindId;
        this.bindTime = bindTime;
    }

    // Property accessors

    /**
     * re_user.user_id，主键
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * re_user.user_id，主键
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 绑定类型；0：微信，1：QQ，2：微博
     */
    public Integer getBindType() {
        return this.bindType;
    }

    /**
     * 绑定类型；0：微信，1：QQ，2：微博
     */
    public void setBindType(Integer bindType) {
        this.bindType = bindType;
    }

    /**
     * 第三方登录获得的openId/uid
     */
    public String getBindId() {
        return this.bindId;
    }

    /**
     * 第三方登录获得的openId/uid
     */
    public void setBindId(String bindId) {
        this.bindId = bindId;
    }

    /**
     * 创建时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
     */
    public Long getBindTime() {
        return this.bindTime;
    }

    /**
     * 创建时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()
     */
    public void setBindTime(Long bindTime) {
        this.bindTime = bindTime;
    }

}