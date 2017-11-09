package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReUserBind;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-08-26
 */
@Repository
public class ReUserBindDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer userId) {
        ReUserBind _key = new ReUserBind();
        _key.setUserId(userId);
        return getSqlSession().delete("re_user_bind.deleteByPrimaryKey", _key);
    }

    public void insert(ReUserBind record) {
        getSqlSession().insert("re_user_bind.insert", record);
    }

    public void insertSelective(ReUserBind record) {
        getSqlSession().insert("re_user_bind.insertSelective", record);
    }

    public void insertBatch(List<ReUserBind> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_user_bind.insertBatch", records);
    }

    public ReUserBind selectByPrimaryKey(Integer userId) {
        ReUserBind _key = new ReUserBind();
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_user_bind.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReUserBind record) {
        return getSqlSession().update("re_user_bind.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReUserBind record) {
        return getSqlSession().update("re_user_bind.updateByPrimaryKey", record);
    }
}