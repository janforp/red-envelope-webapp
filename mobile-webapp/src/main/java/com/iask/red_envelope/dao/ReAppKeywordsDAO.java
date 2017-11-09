package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReAppKeywords;

import com.iask.red_envelope.model.dto.TaskInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-10-17
 */
@Repository
public class ReAppKeywordsDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long keywordId) {
        ReAppKeywords _key = new ReAppKeywords();
        _key.setKeywordId(keywordId);
        return getSqlSession().delete("re_app_keywords.deleteByPrimaryKey", _key);
    }

    public void insert(ReAppKeywords record) {
        getSqlSession().insert("re_app_keywords.insert", record);
    }

    public void insertSelective(ReAppKeywords record) {
        getSqlSession().insert("re_app_keywords.insertSelective", record);
    }

    public void insertBatch(List<ReAppKeywords> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_app_keywords.insertBatch", records);
    }

    public ReAppKeywords selectByPrimaryKey(Long keywordId) {
        ReAppKeywords _key = new ReAppKeywords();
        _key.setKeywordId(keywordId);
        return getSqlSession().selectOne("re_app_keywords.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReAppKeywords record) {
        return getSqlSession().update("re_app_keywords.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReAppKeywords record) {
        return getSqlSession().update("re_app_keywords.updateByPrimaryKey", record);
    }

    /**
     * 查询任务
     *
     * @param keywordId
     * @return
     */
    public TaskInfo selectByKeywordId(Long keywordId) {
        return getSqlSession().selectOne("re_app_keywords.selectByKeywordId", keywordId);
    }

    /**
     * 查询用户任务列表
     *
     * @param releaseTime
     * @param userId
     * @return
     */
    public List<TaskInfo> selectAllByLeftAndTime(String releaseTime, Integer userId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("releaseTime", releaseTime);
        map.put("userId", userId);
        return getSqlSession().selectList("re_app_keywords.selectAllByLeftAndTime", map);
    }

    /**
     * 查询任务
     *
     * @param keywordId
     * @return
     */
    public ReAppKeywords selectLockByKeywordId(Long keywordId) {
        return getSqlSession().selectOne("re_app_keywords.selectLockByKeywordId", keywordId);
    }

}