package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReBanner;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-08-09
 */
@Repository
public class ReBannerDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer bannerId) {
        ReBanner _key = new ReBanner();
        _key.setBannerId(bannerId);
        return getSqlSession().delete("re_banner.deleteByPrimaryKey", _key);
    }

    public void insert(ReBanner record) {
        getSqlSession().insert("re_banner.insert", record);
    }

    public void insertSelective(ReBanner record) {
        getSqlSession().insert("re_banner.insertSelective", record);
    }

    public void insertBatch(List<ReBanner> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_banner.insertBatch", records);
    }

    public ReBanner selectByPrimaryKey(Integer bannerId) {
        ReBanner _key = new ReBanner();
        _key.setBannerId(bannerId);
        return getSqlSession().selectOne("re_banner.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReBanner record) {
        return getSqlSession().update("re_banner.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReBanner record) {
        return getSqlSession().update("re_banner.updateByPrimaryKey", record);
    }

    public List<ReBanner> getAllBanner() {
        return getSqlSession().selectList("re_banner.getAllBanner");
    }

}