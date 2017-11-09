package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.WxmsgKeywordReplyRule;

import com.iask.red_envelope.model.dto.WxmsgKeywordReplyRuleMinDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-08-04
 */
@Repository
public class WxmsgKeywordReplyRuleDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer ruleId) {
        WxmsgKeywordReplyRule _key = new WxmsgKeywordReplyRule();
        _key.setRuleId(ruleId);
        return getSqlSession().delete("wxmsg_keyword_reply_rule.deleteByPrimaryKey", _key);
    }

    public void insert(WxmsgKeywordReplyRule record) {
        getSqlSession().insert("wxmsg_keyword_reply_rule.insert", record);
    }

    public void insertSelective(WxmsgKeywordReplyRule record) {
        getSqlSession().insert("wxmsg_keyword_reply_rule.insertSelective", record);
    }

    public void insertBatch(List<WxmsgKeywordReplyRule> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("wxmsg_keyword_reply_rule.insertBatch", records);
    }

    public WxmsgKeywordReplyRule selectByPrimaryKey(Integer ruleId) {
        WxmsgKeywordReplyRule _key = new WxmsgKeywordReplyRule();
        _key.setRuleId(ruleId);
        return getSqlSession().selectOne("wxmsg_keyword_reply_rule.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(WxmsgKeywordReplyRule record) {
        return getSqlSession().update("wxmsg_keyword_reply_rule.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(WxmsgKeywordReplyRule record) {
        return getSqlSession().update("wxmsg_keyword_reply_rule.updateByPrimaryKey", record);
    }

    public WxmsgKeywordReplyRuleMinDto selectMinDtoByRuleId(Integer ruleId) {
        return getSqlSession().selectOne("wxmsg_keyword_reply_rule.selectMinDtoByRuleId", ruleId);
    }

    public List<WxmsgKeywordReplyRule> selectAll() {
        return getSqlSession().selectList("wxmsg_keyword_reply_rule.selectAll");
    }

}