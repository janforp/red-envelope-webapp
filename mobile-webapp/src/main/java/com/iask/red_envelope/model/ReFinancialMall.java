package com.iask.red_envelope.model;

import java.math.*;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-11-18
 */
public class ReFinancialMall implements java.io.Serializable {

    // Fields

    // id，自增长
    private Long id;
    // 标题
    private String title;
    // 图标
    private String icon;
    // 描述
    private String desc;
    // 起投金额,如:70,表示70元
    private BigDecimal money;
    // 投资期限,如5,表示5天
    private Integer investmentTime;
    // 年化收益,如:15,表示15%
    private BigDecimal earning;
    // 包名
    private String packageName;
    // 安卓下载app链接
    private String androidPackageUrl;
    // 苹果下载app链接,一般为打开app store
    private String iosPackageUrl;
    // 标签,用逗号分开
    private String labels;
    // 点击按钮跳转的链接
    private String clickUrl;
    // 权重,越大越靠前
    private Integer mallOrder;
    // 平台,0:IOS,1:ANDROID,2:全开,3:全关
    private Integer platform;
    // 创建时间,如:2016-08-18 12:53:30
    private String createTime;
    // 修改时间,如:2016-08-18 12:53:30
    private String updateTime;
    // 活动是否已经结束，0:已结束，1:进行中
    private Integer isEnd;

    // Constructors

    /**
     * default constructor
     */
    public ReFinancialMall() {
    }

    /**
     * full constructor
     */
    public ReFinancialMall(String title, String icon, String desc, BigDecimal money, Integer investmentTime, BigDecimal earning, String packageName, String androidPackageUrl, String iosPackageUrl, String labels, String clickUrl, Integer mallOrder, Integer platform, String createTime, String updateTime, Integer isEnd) {
        this.title = title;
        this.icon = icon;
        this.desc = desc;
        this.money = money;
        this.investmentTime = investmentTime;
        this.earning = earning;
        this.packageName = packageName;
        this.androidPackageUrl = androidPackageUrl;
        this.iosPackageUrl = iosPackageUrl;
        this.labels = labels;
        this.clickUrl = clickUrl;
        this.mallOrder = mallOrder;
        this.platform = platform;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isEnd = isEnd;
    }

    // Property accessors

    /**
     * id，自增长
     */
    public Long getId() {
        return this.id;
    }

    /**
     * id，自增长
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 标题
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 图标
     */
    public String getIcon() {
        return this.icon;
    }

    /**
     * 图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 描述
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * 描述
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 起投金额,如:70,表示70元
     */
    public BigDecimal getMoney() {
        return this.money;
    }

    /**
     * 起投金额,如:70,表示70元
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * 投资期限,如5,表示5天
     */
    public Integer getInvestmentTime() {
        return this.investmentTime;
    }

    /**
     * 投资期限,如5,表示5天
     */
    public void setInvestmentTime(Integer investmentTime) {
        this.investmentTime = investmentTime;
    }

    /**
     * 年化收益,如:15,表示15%
     */
    public BigDecimal getEarning() {
        return this.earning;
    }

    /**
     * 年化收益,如:15,表示15%
     */
    public void setEarning(BigDecimal earning) {
        this.earning = earning;
    }

    /**
     * 包名
     */
    public String getPackageName() {
        return this.packageName;
    }

    /**
     * 包名
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * 安卓下载app链接
     */
    public String getAndroidPackageUrl() {
        return this.androidPackageUrl;
    }

    /**
     * 安卓下载app链接
     */
    public void setAndroidPackageUrl(String androidPackageUrl) {
        this.androidPackageUrl = androidPackageUrl;
    }

    /**
     * 苹果下载app链接,一般为打开app store
     */
    public String getIosPackageUrl() {
        return this.iosPackageUrl;
    }

    /**
     * 苹果下载app链接,一般为打开app store
     */
    public void setIosPackageUrl(String iosPackageUrl) {
        this.iosPackageUrl = iosPackageUrl;
    }

    /**
     * 标签,用逗号分开
     */
    public String getLabels() {
        return this.labels;
    }

    /**
     * 标签,用逗号分开
     */
    public void setLabels(String labels) {
        this.labels = labels;
    }

    /**
     * 点击按钮跳转的链接
     */
    public String getClickUrl() {
        return this.clickUrl;
    }

    /**
     * 点击按钮跳转的链接
     */
    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    /**
     * 权重,越大越靠前
     */
    public Integer getMallOrder() {
        return this.mallOrder;
    }

    /**
     * 权重,越大越靠前
     */
    public void setMallOrder(Integer mallOrder) {
        this.mallOrder = mallOrder;
    }

    /**
     * 平台,0:IOS,1:ANDROID,2:全开,3:全关
     */
    public Integer getPlatform() {
        return this.platform;
    }

    /**
     * 平台,0:IOS,1:ANDROID,2:全开,3:全关
     */
    public void setPlatform(Integer platform) {
        this.platform = platform;
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

    /**
     * 修改时间,如:2016-08-18 12:53:30
     */
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 修改时间,如:2016-08-18 12:53:30
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 活动是否已经结束，0:已结束，1:进行中
     */
    public Integer getIsEnd() {
        return this.isEnd;
    }

    /**
     * 活动是否已经结束，0:已结束，1:进行中
     */
    public void setIsEnd(Integer isEnd) {
        this.isEnd = isEnd;
    }

}