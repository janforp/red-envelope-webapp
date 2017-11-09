package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReUserFeedback;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-09-20
 */
@Repository
public class ReUserFeedbackDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long id) {
        ReUserFeedback _key = new ReUserFeedback();
        _key.setId(id);
        return getSqlSession().delete("re_user_feedback.deleteByPrimaryKey", _key);
    }

    public void insert(ReUserFeedback record) {
        getSqlSession().insert("re_user_feedback.insert", record);
    }

    public void insertSelective(ReUserFeedback record) {
        getSqlSession().insert("re_user_feedback.insertSelective", record);
    }

    public void insertBatch(List<ReUserFeedback> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_user_feedback.insertBatch", records);
    }

    public ReUserFeedback selectByPrimaryKey(Long id) {
        ReUserFeedback _key = new ReUserFeedback();
        _key.setId(id);
        return getSqlSession().selectOne("re_user_feedback.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReUserFeedback record) {
        return getSqlSession().update("re_user_feedback.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReUserFeedback record) {
        return getSqlSession().update("re_user_feedback.updateByPrimaryKey", record);
    }
}