package com.iask.red_envelope.model.dto;

import java.io.Serializable;

/**
 * Created by Summer on 2016/10/17.
 */
public class ReTaskInfo implements Serializable {

    // id
    private Long keywordId;
    // app图片
    private String appIcon;
    // 包名
    private String appPackage;
    // 关键词
    private String keyword;
    // 市场名
    private String appMarket;
    // 市场包名
    private String marketUrl;
    // 市场url
    private String marketPackage;
    // 标签
    private String appLabel;
    // 剩余次数
    private Integer leftNum;
    // 描述 (0-进行中, 其他表示金额)
    private String desc;

    public String getMarketUrl() {
        return marketUrl;
    }

    public void setMarketUrl(String marketUrl) {
        this.marketUrl = marketUrl;
    }

    public String getMarketPackage() {
        return marketPackage;
    }

    public void setMarketPackage(String marketPackage) {
        this.marketPackage = marketPackage;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getAppLabel() {
        return appLabel;
    }

    public void setAppLabel(String appLabel) {
        this.appLabel = appLabel;
    }

    public Long getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(Long keywordId) {
        this.keywordId = keywordId;
    }

    public String getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getAppMarket() {
        return appMarket;
    }

    public void setAppMarket(String appMarket) {
        this.appMarket = appMarket;
    }

    public Integer getLeftNum() {
        return leftNum;
    }

    public void setLeftNum(Integer leftNum) {
        this.leftNum = leftNum;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
