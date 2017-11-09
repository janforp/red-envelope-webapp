package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.WxShareMission;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-10-26
 */
@Repository
public class WxShareMissionDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long missionId) {
        WxShareMission _key = new WxShareMission();
        _key.setMissionId(missionId);
        return getSqlSession().delete("wx_share_mission.deleteByPrimaryKey", _key);
    }

    public void insert(WxShareMission record) {
        getSqlSession().insert("wx_share_mission.insert", record);
    }

    public void insertSelective(WxShareMission record) {
        getSqlSession().insert("wx_share_mission.insertSelective", record);
    }

    public void insertBatch(List<WxShareMission> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("wx_share_mission.insertBatch", records);
    }

    public WxShareMission selectByPrimaryKey(Long missionId) {
        WxShareMission _key = new WxShareMission();
        _key.setMissionId(missionId);
        return getSqlSession().selectOne("wx_share_mission.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(WxShareMission record) {
        return getSqlSession().update("wx_share_mission.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(WxShareMission record) {
        return getSqlSession().update("wx_share_mission.updateByPrimaryKey", record);
    }
}