package com.iask.red_envelope.model.vo.financial_mall;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wujie5 on 16/11/18.
 */
public class FinancialListVo {

    // id，自增长
    private Long id;
    // 标题
    private String title;
    // 图标
    private String icon;
    // 描述
    private String desc;
    // 起投金额,如:70,表示70元
    private String money;
    // 投资期限,如5,表示5天
    private Integer investmentTime;
    // 年化收益,如:15,表示15%
    private String earning;
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

    private List<String> label;

    public String getEarning() {
        return earning;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public void setEarning(String earning) {
        this.earning = earning;
    }

    public Integer getInvestmentTime() {
        return investmentTime;
    }

    public void setInvestmentTime(Integer investmentTime) {
        this.investmentTime = investmentTime;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAndroidPackageUrl() {
        return androidPackageUrl;
    }

    public void setAndroidPackageUrl(String androidPackageUrl) {
        this.androidPackageUrl = androidPackageUrl;
    }

    public String getIosPackageUrl() {
        return iosPackageUrl;
    }

    public void setIosPackageUrl(String iosPackageUrl) {
        this.iosPackageUrl = iosPackageUrl;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    public Integer getMallOrder() {
        return mallOrder;
    }

    public void setMallOrder(Integer mallOrder) {
        this.mallOrder = mallOrder;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(Integer isEnd) {
        this.isEnd = isEnd;
    }

    public List<String> getLabel() {
        return label;
    }

    public void setLabel(List<String> label) {
        this.label = label;
    }
}
