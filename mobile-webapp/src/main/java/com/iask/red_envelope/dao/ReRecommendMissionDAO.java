package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReRecommendMission;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-10-31
 */
@Repository
public class ReRecommendMissionDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long missionId) {
        ReRecommendMission _key = new ReRecommendMission();
        _key.setMissionId(missionId);
        return getSqlSession().delete("re_recommend_mission.deleteByPrimaryKey", _key);
    }

    public void insert(ReRecommendMission record) {
        getSqlSession().insert("re_recommend_mission.insert", record);
    }

    public void insertSelective(ReRecommendMission record) {
        getSqlSession().insert("re_recommend_mission.insertSelective", record);
    }

    public void insertBatch(List<ReRecommendMission> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_recommend_mission.insertBatch", records);
    }

    public ReRecommendMission selectByPrimaryKey(Long missionId) {
        ReRecommendMission _key = new ReRecommendMission();
        _key.setMissionId(missionId);
        return getSqlSession().selectOne("re_recommend_mission.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReRecommendMission record) {
        return getSqlSession().update("re_recommend_mission.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReRecommendMission record) {
        return getSqlSession().update("re_recommend_mission.updateByPrimaryKey", record);
    }


    /**
     * 查询任务
     *
     * @param missionId
     * @return
     */
    public ReRecommendMission selectLockByMissionId(Long missionId) {
        return getSqlSession().selectOne("re_recommend_mission.selectLockByMissionId", missionId);
    }

    /**
     * 查询兑换码任务
     *
     * @param exchangeCode
     * @return
     */
    public ReRecommendMission selectByCode(String exchangeCode){
        return getSqlSession().selectOne("re_recommend_mission.selectByCode", exchangeCode);
    }

}