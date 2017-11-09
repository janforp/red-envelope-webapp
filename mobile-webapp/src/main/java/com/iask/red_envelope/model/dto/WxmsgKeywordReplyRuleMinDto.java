package com.iask.red_envelope.model.dto;

import java.io.Serializable;

/**
 * Created by craig on 16/3/28.
 */
public class WxmsgKeywordReplyRuleMinDto implements Serializable {
    // 规则ID
    private Integer ruleId;
    // 规则名称，非空，长度64
    private String ruleName;
    // 客户号
    private Integer customerId;
    // 匹配模式；0：模糊匹配（所有的关键词中，任何一个包含了用户的关键词就触发）；1：精确匹配（所有关键词中，任何一个和用户输入的相同才触发）
    private Integer matchMode;
    // 回复的消息类型：目前支持文字/图文消息；text:文本消息;news:图文消息;
    private String msgType;
    // 回复的消息的JSON数据，非空，长度1024
    private String msgBody;

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getMatchMode() {
        return matchMode;
    }

    public void setMatchMode(Integer matchMode) {
        this.matchMode = matchMode;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }
}
