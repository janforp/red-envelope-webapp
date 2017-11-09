package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReRotateDetail;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-09-08
 */
@Repository
public class ReRotateDetailDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long id) {
        ReRotateDetail _key = new ReRotateDetail();
        _key.setId(id);
        return getSqlSession().delete("re_rotate_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReRotateDetail record) {
        getSqlSession().insert("re_rotate_detail.insert", record);
    }

    public void insertSelective(ReRotateDetail record) {
        getSqlSession().insert("re_rotate_detail.insertSelective", record);
    }

    public void insertBatch(List<ReRotateDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_rotate_detail.insertBatch", records);
    }

    public ReRotateDetail selectByPrimaryKey(Long id) {
        ReRotateDetail _key = new ReRotateDetail();
        _key.setId(id);
        return getSqlSession().selectOne("re_rotate_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReRotateDetail record) {
        return getSqlSession().update("re_rotate_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReRotateDetail record) {
        return getSqlSession().update("re_rotate_detail.updateByPrimaryKey", record);
    }

    /**
     * 查询抽奖次数
     * @param userId
     * @param dayTime
     * @return
     */
    public int selectRotateTimesByUserId(Integer userId, String dayTime) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("dayTime", dayTime);
        return getSqlSession().selectOne("re_rotate_detail.selectRotateTimesByUserId", map);
    }

    public List<ReRotateDetail> get8RotateDetail(){
        return getSqlSession().selectList("re_score_detail.get8RotateDetail");
    }

}