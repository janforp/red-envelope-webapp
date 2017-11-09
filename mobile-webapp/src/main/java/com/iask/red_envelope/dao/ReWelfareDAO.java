package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReWelfare;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-12-14
 */
@Repository
public class ReWelfareDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long welfareId) {
        ReWelfare _key = new ReWelfare();
        _key.setWelfareId(welfareId);
        return getSqlSession().delete("re_welfare.deleteByPrimaryKey", _key);
    }

    public void insert(ReWelfare record) {
        getSqlSession().insert("re_welfare.insert", record);
    }

    public void insertSelective(ReWelfare record) {
        getSqlSession().insert("re_welfare.insertSelective", record);
    }

    public void insertBatch(List<ReWelfare> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_welfare.insertBatch", records);
    }

    public ReWelfare selectByPrimaryKey(Long welfareId) {
        ReWelfare _key = new ReWelfare();
        _key.setWelfareId(welfareId);
        return getSqlSession().selectOne("re_welfare.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReWelfare record) {
        return getSqlSession().update("re_welfare.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReWelfare record) {
        return getSqlSession().update("re_welfare.updateByPrimaryKey", record);
    }
}