package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReRecommendTask;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-10-31
 */
@Repository
public class ReRecommendTaskDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long taskId) {
        ReRecommendTask _key = new ReRecommendTask();
        _key.setTaskId(taskId);
        return getSqlSession().delete("re_recommend_task.deleteByPrimaryKey", _key);
    }

    public void insert(ReRecommendTask record) {
        getSqlSession().insert("re_recommend_task.insert", record);
    }

    public void insertSelective(ReRecommendTask record) {
        getSqlSession().insert("re_recommend_task.insertSelective", record);
    }

    public void insertBatch(List<ReRecommendTask> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_recommend_task.insertBatch", records);
    }

    public ReRecommendTask selectByPrimaryKey(Long taskId) {
        ReRecommendTask _key = new ReRecommendTask();
        _key.setTaskId(taskId);
        return getSqlSession().selectOne("re_recommend_task.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReRecommendTask record) {
        return getSqlSession().update("re_recommend_task.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReRecommendTask record) {
        return getSqlSession().update("re_recommend_task.updateByPrimaryKey", record);
    }

    /**
     * 查询个人任务
     *
     * @param userId
     * @param missionId
     * @return
     */
    public ReRecommendTask selectByUserIdAndMissionId(Integer userId, Long missionId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("missionId", missionId);
        return getSqlSession().selectOne("re_recommend_task.selectByUserIdAndMissionId", map);
    }

    /**
     * 查询个人任务
     *
     * @param userId
     * @param missionId
     * @return
     */
    public ReRecommendTask selectByUserIdAndMissionIdAndStatus(Integer userId, Long missionId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("missionId", missionId);
        return getSqlSession().selectOne("re_recommend_task.selectByUserIdAndMissionIdAndStatus", map);
    }

    /**
     * 查询个人超时任务
     *
     * @param userId
     * @param missionId
     * @return
     */
    public ReRecommendTask getOvertimeMissionByUserIdMissionId(Integer userId, Long missionId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("missionId", missionId);
        return getSqlSession().selectOne("re_recommend_task.getOvertimeMissionByUserIdMissionId", map);
    }




    /**
     * 查询超时任务列表
     *
     * @return
     */
    public List<ReRecommendTask> selectOvertimeTask(Long releaseTime) {
        return getSqlSession().selectList("re_recommend_task.selectOvertimeTask", releaseTime);
    }

}