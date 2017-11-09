package com.iask.red_envelope.model;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-09-08
 */
public class ReRotateDetail implements java.io.Serializable {

    // Fields

    // id
    private Long id;
    // 用户ID
    private Integer userId;
    // 内容
    private String rotateContent;
    // 时间,如:2016-08-18 12:53:30
    private String rotateTime;

    // Constructors

    /**
     * default constructor
     */
    public ReRotateDetail() {
    }

    /**
     * full constructor
     */
    public ReRotateDetail(Integer userId, String rotateContent, String rotateTime) {
        this.userId = userId;
        this.rotateContent = rotateContent;
        this.rotateTime = rotateTime;
    }

    // Property accessors

    /**
     * id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户ID
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 内容
     */
    public String getRotateContent() {
        return this.rotateContent;
    }

    /**
     * 内容
     */
    public void setRotateContent(String rotateContent) {
        this.rotateContent = rotateContent;
    }

    /**
     * 时间,如:2016-08-18 12:53:30
     */
    public String getRotateTime() {
        return this.rotateTime;
    }

    /**
     * 时间,如:2016-08-18 12:53:30
     */
    public void setRotateTime(String rotateTime) {
        this.rotateTime = rotateTime;
    }

}