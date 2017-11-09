package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReRotateDetail;
import com.iask.red_envelope.model.ReScoreDetail;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-08-18
 */
@Repository
public class ReScoreDetailDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer scoreId) {
        ReScoreDetail _key = new ReScoreDetail();
        _key.setScoreId(scoreId);
        return getSqlSession().delete("re_score_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReScoreDetail record) {
        getSqlSession().insert("re_score_detail.insert", record);
    }

    public void insertSelective(ReScoreDetail record) {
        getSqlSession().insert("re_score_detail.insertSelective", record);
    }

    public void insertBatch(List<ReScoreDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_score_detail.insertBatch", records);
    }

    public ReScoreDetail selectByPrimaryKey(Integer scoreId) {
        ReScoreDetail _key = new ReScoreDetail();
        _key.setScoreId(scoreId);
        return getSqlSession().selectOne("re_score_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReScoreDetail record) {
        return getSqlSession().update("re_score_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReScoreDetail record) {
        return getSqlSession().update("re_score_detail.updateByPrimaryKey", record);
    }

    /**
     * 获取个人的金币记录
     * @param userId
     * @return
     */
    public List<ReScoreDetail> getScoreList(Integer userId, RowBounds bounds) {

        return getSqlSession().selectList("re_score_detail.getScoreList",userId, bounds);
    }

    /**
     * 个人记录数
     * @param userId
     * @return
     */
    public Integer getTotalNum(Integer userId) {

        return getSqlSession().selectOne("re_score_detail.getTotalNum",userId) ;
    }

    /***
     * 今天获得金币
     * @param userId
     * @param dayTime
     * @return
     */
    public Integer getScoreByDay(Integer userId, String dayTime){
        Map<String,Object> params = new HashMap<>(2);
        params.put("userId",userId);
        params.put("dayTime",dayTime);
        return getSqlSession().selectOne("re_score_detail.getScoreByDay",params);
    }

    /**
     * 总共金币
     * @param userId
     * @return
     */
    public Integer getTotalScore(Integer userId){
        return getSqlSession().selectOne("re_score_detail.getTotalScore",userId);
    }


}