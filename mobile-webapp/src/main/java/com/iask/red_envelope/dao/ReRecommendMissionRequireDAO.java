package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReRecommendMissionRequire;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-11-07
 */
@Repository
public class ReRecommendMissionRequireDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long requireId) {
        ReRecommendMissionRequire _key = new ReRecommendMissionRequire();
        _key.setRequireId(requireId);
        return getSqlSession().delete("re_recommend_mission_require.deleteByPrimaryKey", _key);
    }

    public void insert(ReRecommendMissionRequire record) {
        getSqlSession().insert("re_recommend_mission_require.insert", record);
    }

    public void insertSelective(ReRecommendMissionRequire record) {
        getSqlSession().insert("re_recommend_mission_require.insertSelective", record);
    }

    public void insertBatch(List<ReRecommendMissionRequire> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_recommend_mission_require.insertBatch", records);
    }

    public ReRecommendMissionRequire selectByPrimaryKey(Long requireId) {
        ReRecommendMissionRequire _key = new ReRecommendMissionRequire();
        _key.setRequireId(requireId);
        return getSqlSession().selectOne("re_recommend_mission_require.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReRecommendMissionRequire record) {
        return getSqlSession().update("re_recommend_mission_require.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReRecommendMissionRequire record) {
        return getSqlSession().update("re_recommend_mission_require.updateByPrimaryKey", record);
    }
}