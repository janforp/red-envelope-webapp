package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReUserRecord;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-07-19
 */
@Repository
public class ReUserRecordDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer id) {
        ReUserRecord _key = new ReUserRecord();
        _key.setId(id);
        return getSqlSession().delete("re_user_record.deleteByPrimaryKey", _key);
    }

    public void insert(ReUserRecord record) {
        getSqlSession().insert("re_user_record.insert", record);
    }

    public void insertSelective(ReUserRecord record) {
        getSqlSession().insert("re_user_record.insertSelective", record);
    }

    public void insertBatch(List<ReUserRecord> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_user_record.insertBatch", records);
    }

    public ReUserRecord selectByPrimaryKey(Integer id) {
        ReUserRecord _key = new ReUserRecord();
        _key.setId(id);
        return getSqlSession().selectOne("re_user_record.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReUserRecord record) {
        return getSqlSession().update("re_user_record.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReUserRecord record) {
        return getSqlSession().update("re_user_record.updateByPrimaryKey", record);
    }

    /**
     * 查询最大的红包记录
     * @param extendId
     * @return
     */
    public ReUserRecord selectMaxByExtendId(int extendId) {
        return getSqlSession().selectOne("re_user_record.selectMaxByExtendId", extendId);
    }

    /**
     * 土豪榜
     * @param extendId
     * @return
     */
    public List<ReUserRecord> selectTopByExtendId(int extendId) {
        return getSqlSession().selectList("re_user_record.selectTopByExtendId", extendId);
    }

    /**
     * 查询领取记录
     * @param extendId
     * @param openid
     * @return
     */
    public ReUserRecord selectByExtendIdAndOpenid(int extendId, String openid) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("extendId", extendId);
        map.put("openid", openid);
        return getSqlSession().selectOne("re_user_record.selectByExtendIdAndOpenid", map);
    }


}