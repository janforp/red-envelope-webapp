package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReShareMissionDetail;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-11-14
 */
@Repository
public class ReShareMissionDetailDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long missionId, String openId) {
        ReShareMissionDetail _key = new ReShareMissionDetail();
        _key.setMissionId(missionId);
        _key.setOpenId(openId);
        return getSqlSession().delete("re_share_mission_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReShareMissionDetail record) {
        getSqlSession().insert("re_share_mission_detail.insert", record);
    }

    public void insertSelective(ReShareMissionDetail record) {
        getSqlSession().insert("re_share_mission_detail.insertSelective", record);
    }

    public void insertBatch(List<ReShareMissionDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_share_mission_detail.insertBatch", records);
    }

    public ReShareMissionDetail selectByPrimaryKey(Long missionId, String openId) {
        ReShareMissionDetail _key = new ReShareMissionDetail();
        _key.setMissionId(missionId);
        _key.setOpenId(openId);
        return getSqlSession().selectOne("re_share_mission_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReShareMissionDetail record) {
        return getSqlSession().update("re_share_mission_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReShareMissionDetail record) {
        return getSqlSession().update("re_share_mission_detail.updateByPrimaryKey", record);
    }

    /**
     * 查询此用户分享的分享点击任务共点击了都多少次
     * @param userId
     * @param missionId
     * @return
     */
    public int selectClickTimesByUserIdAndMissionId(Integer userId,Long missionId){

        Map<String,Object> params = new HashMap<>(2);
        params.put("userId",userId);
        params.put("missionId",missionId);

        return getSqlSession().selectOne("re_share_mission_detail.selectClickTimesByUserIdAndMissionId",params);
    }

    /**
     * 获该任务已经有多少个用户参加了
     * @param missionId
     * @return
     */
    public int selectPartInNum(Long missionId){
        return getSqlSession().selectOne("re_share_mission_detail.selectPartInNum",missionId);
    }
}