package com.iask.red_envelope.model;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-09-20
 */
public class ReUserFeedback implements java.io.Serializable {

    // Fields

    // ID
    private Long id;
    // 用户ID
    private Integer userId;
    // 联系方式
    private String userContact;
    // 内容
    private String feedbackDetail;
    // 图片
    private String feedbackImg;
    // 确认时间,如:2016-08-18 12:53:30
    private String feedbackTime;

    // Constructors

    /**
     * default constructor
     */
    public ReUserFeedback() {
    }

    /**
     * full constructor
     */
    public ReUserFeedback(Integer userId, String userContact, String feedbackDetail, String feedbackImg, String feedbackTime) {
        this.userId = userId;
        this.userContact = userContact;
        this.feedbackDetail = feedbackDetail;
        this.feedbackImg = feedbackImg;
        this.feedbackTime = feedbackTime;
    }

    // Property accessors

    /**
     * ID
     */
    public Long getId() {
        return this.id;
    }

    /**
     * ID
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
     * 联系方式
     */
    public String getUserContact() {
        return this.userContact;
    }

    /**
     * 联系方式
     */
    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    /**
     * 内容
     */
    public String getFeedbackDetail() {
        return this.feedbackDetail;
    }

    /**
     * 内容
     */
    public void setFeedbackDetail(String feedbackDetail) {
        this.feedbackDetail = feedbackDetail;
    }

    /**
     * 图片
     */
    public String getFeedbackImg() {
        return this.feedbackImg;
    }

    /**
     * 图片
     */
    public void setFeedbackImg(String feedbackImg) {
        this.feedbackImg = feedbackImg;
    }

    /**
     * 确认时间,如:2016-08-18 12:53:30
     */
    public String getFeedbackTime() {
        return this.feedbackTime;
    }

    /**
     * 确认时间,如:2016-08-18 12:53:30
     */
    public void setFeedbackTime(String feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

}