package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.WxShareUserRelation;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-10-26
 */
@Repository
public class WxShareUserRelationDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long userId, Long missionId) {
        WxShareUserRelation _key = new WxShareUserRelation();
        _key.setUserId(userId);
        _key.setMissionId(missionId);
        return getSqlSession().delete("wx_share_user_relation.deleteByPrimaryKey", _key);
    }

    public void insert(WxShareUserRelation record) {
        getSqlSession().insert("wx_share_user_relation.insert", record);
    }

    public void insertSelective(WxShareUserRelation record) {
        getSqlSession().insert("wx_share_user_relation.insertSelective", record);
    }

    public void insertBatch(List<WxShareUserRelation> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("wx_share_user_relation.insertBatch", records);
    }

    public WxShareUserRelation selectByPrimaryKey(Long userId, Long missionId) {
        WxShareUserRelation _key = new WxShareUserRelation();
        _key.setUserId(userId);
        _key.setMissionId(missionId);
        return getSqlSession().selectOne("wx_share_user_relation.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(WxShareUserRelation record) {
        return getSqlSession().update("wx_share_user_relation.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(WxShareUserRelation record) {
        return getSqlSession().update("wx_share_user_relation.updateByPrimaryKey", record);
    }
}