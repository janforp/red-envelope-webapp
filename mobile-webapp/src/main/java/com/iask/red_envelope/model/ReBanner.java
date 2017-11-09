package com.iask.red_envelope.model;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-08-12
 */
public class ReBanner implements java.io.Serializable {

    // Fields

    // BannerID
    private Integer bannerId;
    // Banner标题
    private String bannerTitle;
    // Banner图片
    private String bannerImg;
    // Banner状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
    private Integer bannerStatus;
    // Banner链接
    private String bannerUrl;
    // Banner排序，值越小，越靠前
    private Integer bannerOrder;

    // Constructors

    /**
     * default constructor
     */
    public ReBanner() {
    }

    /**
     * full constructor
     */
    public ReBanner(String bannerTitle, String bannerImg, Integer bannerStatus, String bannerUrl, Integer bannerOrder) {
        this.bannerTitle = bannerTitle;
        this.bannerImg = bannerImg;
        this.bannerStatus = bannerStatus;
        this.bannerUrl = bannerUrl;
        this.bannerOrder = bannerOrder;
    }

    // Property accessors

    /**
     * BannerID
     */
    public Integer getBannerId() {
        return this.bannerId;
    }

    /**
     * BannerID
     */
    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    /**
     * Banner标题
     */
    public String getBannerTitle() {
        return this.bannerTitle;
    }

    /**
     * Banner标题
     */
    public void setBannerTitle(String bannerTitle) {
        this.bannerTitle = bannerTitle;
    }

    /**
     * Banner图片
     */
    public String getBannerImg() {
        return this.bannerImg;
    }

    /**
     * Banner图片
     */
    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }

    /**
     * Banner状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
     */
    public Integer getBannerStatus() {
        return this.bannerStatus;
    }

    /**
     * Banner状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭
     */
    public void setBannerStatus(Integer bannerStatus) {
        this.bannerStatus = bannerStatus;
    }

    /**
     * Banner链接
     */
    public String getBannerUrl() {
        return this.bannerUrl;
    }

    /**
     * Banner链接
     */
    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    /**
     * Banner排序，值越小，越靠前
     */
    public Integer getBannerOrder() {
        return this.bannerOrder;
    }

    /**
     * Banner排序，值越小，越靠前
     */
    public void setBannerOrder(Integer bannerOrder) {
        this.bannerOrder = bannerOrder;
    }

}