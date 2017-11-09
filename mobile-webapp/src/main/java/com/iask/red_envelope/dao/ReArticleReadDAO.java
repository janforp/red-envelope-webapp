package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReArticleRead;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-12-08
 */
@Repository
public class ReArticleReadDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long articleId, Integer userId) {
        ReArticleRead _key = new ReArticleRead();
        _key.setArticleId(articleId);
        _key.setUserId(userId);
        return getSqlSession().delete("re_article_read.deleteByPrimaryKey", _key);
    }

    public void insert(ReArticleRead record) {
        getSqlSession().insert("re_article_read.insert", record);
    }

    public void insertSelective(ReArticleRead record) {
        getSqlSession().insert("re_article_read.insertSelective", record);
    }

    public void insertBatch(List<ReArticleRead> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_article_read.insertBatch", records);
    }

    public ReArticleRead selectByPrimaryKey(Long articleId, Integer userId) {
        ReArticleRead _key = new ReArticleRead();
        _key.setArticleId(articleId);
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_article_read.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReArticleRead record) {
        return getSqlSession().update("re_article_read.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReArticleRead record) {
        return getSqlSession().update("re_article_read.updateByPrimaryKey", record);
    }
}