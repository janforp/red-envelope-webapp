package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReParamConfig;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-07-13
 */
@Repository
public class ReParamConfigDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(String paramType) {
        ReParamConfig _key = new ReParamConfig();
        _key.setParamType(paramType);
        return getSqlSession().delete("re_param_config.deleteByPrimaryKey", _key);
    }

    public void insert(ReParamConfig record) {
        getSqlSession().insert("re_param_config.insert", record);
    }

    public void insertSelective(ReParamConfig record) {
        getSqlSession().insert("re_param_config.insertSelective", record);
    }

    public void insertBatch(List<ReParamConfig> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_param_config.insertBatch", records);
    }

    public ReParamConfig selectByPrimaryKey(String paramType) {
        ReParamConfig _key = new ReParamConfig();
        _key.setParamType(paramType);
        return getSqlSession().selectOne("re_param_config.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReParamConfig record) {
        return getSqlSession().update("re_param_config.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReParamConfig record) {
        return getSqlSession().update("re_param_config.updateByPrimaryKey", record);
    }
}