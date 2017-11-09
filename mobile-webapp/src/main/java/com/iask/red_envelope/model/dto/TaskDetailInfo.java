package com.iask.red_envelope.model.dto;

import java.math.BigDecimal;

/**
 * Created by Jan on 16/10/19.
 * 任务详情
 */
public class TaskDetailInfo {

    // id
    private Long keywordId;
    // app图片
    private String appIcon;
    //app 包名
    private String appPackage;
    //市场 包名
    private String marketPackage;
    //市场下载地址
    private String marketUrl;
    // 关键词
    private String keyword;
    // 市场名
    private String appMarket;
    //大小
    private String size;
    //奖励金额
    private BigDecimal money;

    private Long leftTime;

    public String getMarketUrl() {
        return marketUrl;
    }

    public void setMarketUrl(String marketUrl) {
        this.marketUrl = marketUrl;
    }

    public Long getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(Long leftTime) {
        this.leftTime = leftTime;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getMarketPackage() {
        return marketPackage;
    }

    public void setMarketPackage(String marketPackage) {
        this.marketPackage = marketPackage;
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
}
