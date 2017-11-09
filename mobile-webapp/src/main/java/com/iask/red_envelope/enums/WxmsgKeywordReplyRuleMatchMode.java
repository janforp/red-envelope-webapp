package com.iask.red_envelope.enums;

/**
 * Created by craig on 16/3/28.
 * 匹配模式；0：模糊匹配（所有的关键词中，任何一个包含了用户的关键词就触发）；1：精确匹配（所有关键词中，任何一个和用户输入的相同才触发）
 */
public enum WxmsgKeywordReplyRuleMatchMode {
    /**
     * 0：模糊匹配
     */
    contain(0),
    /**
     * 1：精确匹配
     */
    equal(1);
    // 值
    public final Integer val;

    WxmsgKeywordReplyRuleMatchMode(Integer val) {
        this.val = val;
    }
}
