package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReAndriodUserTask;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-12-05
 */
@Repository
public class ReAndriodUserTaskDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long wallId, Integer userId) {
        ReAndriodUserTask _key = new ReAndriodUserTask();
        _key.setWallId(wallId);
        _key.setUserId(userId);
        return getSqlSession().delete("re_andriod_user_task.deleteByPrimaryKey", _key);
    }

    public void insert(ReAndriodUserTask record) {
        getSqlSession().insert("re_andriod_user_task.insert", record);
    }

    public void insertSelective(ReAndriodUserTask record) {
        getSqlSession().insert("re_andriod_user_task.insertSelective", record);
    }

    public void insertBatch(List<ReAndriodUserTask> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_andriod_user_task.insertBatch", records);
    }

    public ReAndriodUserTask selectByPrimaryKey(Long wallId, Integer userId) {
        ReAndriodUserTask _key = new ReAndriodUserTask();
        _key.setWallId(wallId);
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_andriod_user_task.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReAndriodUserTask record) {
        return getSqlSession().update("re_andriod_user_task.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReAndriodUserTask record) {
        return getSqlSession().update("re_andriod_user_task.updateByPrimaryKey", record);
    }

    /**
     * 查询超时任务
     * @param nowTime
     * @return
     */
    public List<ReAndriodUserTask> selectOvertimeTask(String nowTime) {
        return getSqlSession().selectList("re_andriod_user_task.selectOvertimeTask", nowTime);
    }

}