package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReCodeExchangeDetail;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-11-10
 */
@Repository
public class ReCodeExchangeDetailDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long missionId, Integer userId) {
        ReCodeExchangeDetail _key = new ReCodeExchangeDetail();
        _key.setMissionId(missionId);
        _key.setUserId(userId);
        return getSqlSession().delete("re_code_exchange_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReCodeExchangeDetail record) {
        getSqlSession().insert("re_code_exchange_detail.insert", record);
    }

    public void insertSelective(ReCodeExchangeDetail record) {
        getSqlSession().insert("re_code_exchange_detail.insertSelective", record);
    }

    public void insertBatch(List<ReCodeExchangeDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_code_exchange_detail.insertBatch", records);
    }

    public ReCodeExchangeDetail selectByPrimaryKey(Long missionId, Integer userId) {
        ReCodeExchangeDetail _key = new ReCodeExchangeDetail();
        _key.setMissionId(missionId);
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_code_exchange_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReCodeExchangeDetail record) {
        return getSqlSession().update("re_code_exchange_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReCodeExchangeDetail record) {
        return getSqlSession().update("re_code_exchange_detail.updateByPrimaryKey", record);
    }

    /**
     * 查询兑换码记录
     *
     * @param code
     * @param missionId
     * @return
     */
    public ReCodeExchangeDetail selectLockByCodeAndMissionId(String code, Long missionId){
        Map<String,Object> map = new HashMap<>(2);
        map.put("code", code);
        map.put("missionId", missionId);
        return getSqlSession().selectOne("re_code_exchange_detail.selectLockByCodeAndMissionId", map);
    }

}