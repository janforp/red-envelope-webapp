package com.iask.red_envelope.model;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-10-19
 */
public class ReAppMarket implements java.io.Serializable {

    // Fields

    // id
    private Integer marketId;
    // 市场名称
    private String marketName;
    // 包名
    private String marketPackage;
    // 市场下载地址
    private String marketUrl;
    // 排序,值较小者排在较前
    private Integer marketOrder;

    // Constructors

    /**
     * default constructor
     */
    public ReAppMarket() {
    }

    /**
     * full constructor
     */
    public ReAppMarket(String marketName, String marketPackage, String marketUrl, Integer marketOrder) {
        this.marketName = marketName;
        this.marketPackage = marketPackage;
        this.marketUrl = marketUrl;
        this.marketOrder = marketOrder;
    }

    // Property accessors

    /**
     * id
     */
    public Integer getMarketId() {
        return this.marketId;
    }

    /**
     * id
     */
    public void setMarketId(Integer marketId) {
        this.marketId = marketId;
    }

    /**
     * 市场名称
     */
    public String getMarketName() {
        return this.marketName;
    }

    /**
     * 市场名称
     */
    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    /**
     * 包名
     */
    public String getMarketPackage() {
        return this.marketPackage;
    }

    /**
     * 包名
     */
    public void setMarketPackage(String marketPackage) {
        this.marketPackage = marketPackage;
    }

    /**
     * 市场下载地址
     */
    public String getMarketUrl() {
        return this.marketUrl;
    }

    /**
     * 市场下载地址
     */
    public void setMarketUrl(String marketUrl) {
        this.marketUrl = marketUrl;
    }

    /**
     * 排序,值较小者排在较前
     */
    public Integer getMarketOrder() {
        return this.marketOrder;
    }

    /**
     * 排序,值较小者排在较前
     */
    public void setMarketOrder(Integer marketOrder) {
        this.marketOrder = marketOrder;
    }

}