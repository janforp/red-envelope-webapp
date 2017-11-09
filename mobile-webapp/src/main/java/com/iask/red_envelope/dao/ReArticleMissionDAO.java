package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReArticleMission;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-12-08
 */
@Repository
public class ReArticleMissionDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long articleId) {
        ReArticleMission _key = new ReArticleMission();
        _key.setArticleId(articleId);
        return getSqlSession().delete("re_article_mission.deleteByPrimaryKey", _key);
    }

    public void insert(ReArticleMission record) {
        getSqlSession().insert("re_article_mission.insert", record);
    }

    public void insertSelective(ReArticleMission record) {
        getSqlSession().insert("re_article_mission.insertSelective", record);
    }

    public void insertBatch(List<ReArticleMission> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_article_mission.insertBatch", records);
    }

    public ReArticleMission selectByPrimaryKey(Long articleId) {
        ReArticleMission _key = new ReArticleMission();
        _key.setArticleId(articleId);
        return getSqlSession().selectOne("re_article_mission.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReArticleMission record) {
        return getSqlSession().update("re_article_mission.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReArticleMission record) {
        return getSqlSession().update("re_article_mission.updateByPrimaryKey", record);
    }

    /**
     * 锁住
     * @param articleId
     * @return
     */
    public ReArticleMission selectByPrimaryKeyAndLock(Long articleId){

        return getSqlSession().selectOne("re_article_mission.selectByPrimaryKeyAndLock",articleId);
    }
}