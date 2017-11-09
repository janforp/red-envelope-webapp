package com.iask.red_envelope.model.vo.loan_mall;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Janita on 2016/12/19.
 */
public class LoanListVo {

    private Long id;
    // 标题
    private String title;
    // 图标
    private String icon;
    // 展示金额,根据实际情况输入
    private String displayMoney;
    // 月利率
    private BigDecimal monthInterestRate;
    // 标签,用逗号分开
    private String labels;
    // 已申请人数
    private Integer participantsNum;
    // 点击按钮跳转的链接
    private String clickUrl;
    // 到账时间,如5,表示5天
    private Integer toAccountTime;

    private String desc;

    private List<String> label;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public String getDisplayMoney() {
        return displayMoney;
    }

    public void setDisplayMoney(String displayMoney) {
        this.displayMoney = displayMoney;
    }

    public BigDecimal getMonthInterestRate() {
        return monthInterestRate;
    }

    public void setMonthInterestRate(BigDecimal monthInterestRate) {
        this.monthInterestRate = monthInterestRate;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public Integer getParticipantsNum() {
        return participantsNum;
    }

    public void setParticipantsNum(Integer participantsNum) {
        this.participantsNum = participantsNum;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    public Integer getToAccountTime() {
        return toAccountTime;
    }

    public void setToAccountTime(Integer toAccountTime) {
        this.toAccountTime = toAccountTime;
    }

    public List<String> getLabel() {
        return label;
    }

    public void setLabel(List<String> label) {
        this.label = label;
    }
}
