package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReCodeRed;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-08-29
 */
@Repository
public class ReCodeRedDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer codeId) {
        ReCodeRed _key = new ReCodeRed();
        _key.setCodeId(codeId);
        return getSqlSession().delete("re_code_red.deleteByPrimaryKey", _key);
    }

    public void insert(ReCodeRed record) {
        getSqlSession().insert("re_code_red.insert", record);
    }

    public void insertSelective(ReCodeRed record) {
        getSqlSession().insert("re_code_red.insertSelective", record);
    }

    public void insertBatch(List<ReCodeRed> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_code_red.insertBatch", records);
    }

    public ReCodeRed selectByPrimaryKey(Integer codeId) {
        ReCodeRed _key = new ReCodeRed();
        _key.setCodeId(codeId);
        return getSqlSession().selectOne("re_code_red.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReCodeRed record) {
        return getSqlSession().update("re_code_red.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReCodeRed record) {
        return getSqlSession().update("re_code_red.updateByPrimaryKey", record);
    }

    /**
     * 查询兑换码红包详情
     *
     * @param codeId
     * @return
     */
    public ReCodeRed selectLockByCodeId(Integer codeId) {
        return getSqlSession().selectOne("re_code_red.selectLockByCodeId", codeId);
    }

}