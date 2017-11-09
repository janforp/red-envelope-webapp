package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReMission;

import com.iask.red_envelope.model.vo.MissionListVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-08-10
 */
@Repository
public class ReMissionDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer missionId) {
        ReMission _key = new ReMission();
        _key.setMissionId(missionId);
        return getSqlSession().delete("re_mission.deleteByPrimaryKey", _key);
    }

    public void insert(ReMission record) {
        getSqlSession().insert("re_mission.insert", record);
    }

    public void insertSelective(ReMission record) {
        getSqlSession().insert("re_mission.insertSelective", record);
    }

    public void insertBatch(List<ReMission> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_mission.insertBatch", records);
    }

    public ReMission selectByPrimaryKey(Integer missionId) {
        ReMission _key = new ReMission();
        _key.setMissionId(missionId);
        return getSqlSession().selectOne("re_mission.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReMission record) {
        return getSqlSession().update("re_mission.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReMission record) {
        return getSqlSession().update("re_mission.updateByPrimaryKey", record);
    }

    /**
     * 获取进行中的 热门任务
     * @return
     */
    public List<ReMission> getHotMission() {
        return getSqlSession().selectList("re_mission.getHotMission");
    }

    /**
     * 获取任务分类列表
     * @param sortId
     * @return
     */
    public List<ReMission> getMissionBySortId(Integer sortId, RowBounds bounds) {
        return getSqlSession().selectList("re_mission.getMissionBySortId",sortId,bounds);
    }

    /**
     * 分页用 总数
     * @param sortId
     * @return
     */
    public Integer getMissionListBySortIdNum (Integer sortId) {
        return getSqlSession().selectOne("re_mission.getMissionListBySortIdNum",sortId);
    }
}