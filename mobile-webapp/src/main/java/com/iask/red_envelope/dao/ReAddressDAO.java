package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReAddress;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-09-20
 */
@Repository
public class ReAddressDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer userId) {
        ReAddress _key = new ReAddress();
        _key.setUserId(userId);
        return getSqlSession().delete("re_address.deleteByPrimaryKey", _key);
    }

    public void insert(ReAddress record) {
        getSqlSession().insert("re_address.insert", record);
    }

    public void insertSelective(ReAddress record) {
        getSqlSession().insert("re_address.insertSelective", record);
    }

    public void insertBatch(List<ReAddress> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_address.insertBatch", records);
    }

    public ReAddress selectByPrimaryKey(Integer userId) {
        ReAddress _key = new ReAddress();
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_address.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReAddress record) {
        return getSqlSession().update("re_address.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReAddress record) {
        return getSqlSession().update("re_address.updateByPrimaryKey", record);
    }
}