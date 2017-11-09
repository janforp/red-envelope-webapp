package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReApp;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-10-17
 */
@Repository
public class ReAppDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long appId) {
        ReApp _key = new ReApp();
        _key.setAppId(appId);
        return getSqlSession().delete("re_app.deleteByPrimaryKey", _key);
    }

    public void insert(ReApp record) {
        getSqlSession().insert("re_app.insert", record);
    }

    public void insertSelective(ReApp record) {
        getSqlSession().insert("re_app.insertSelective", record);
    }

    public void insertBatch(List<ReApp> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_app.insertBatch", records);
    }

    public ReApp selectByPrimaryKey(Long appId) {
        ReApp _key = new ReApp();
        _key.setAppId(appId);
        return getSqlSession().selectOne("re_app.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReApp record) {
        return getSqlSession().update("re_app.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReApp record) {
        return getSqlSession().update("re_app.updateByPrimaryKey", record);
    }
}