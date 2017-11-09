package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.WxShareDetail;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-10-26
 */
@Repository
public class WxShareDetailDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long detailId) {
        WxShareDetail _key = new WxShareDetail();
        _key.setDetailId(detailId);
        return getSqlSession().delete("wx_share_detail.deleteByPrimaryKey", _key);
    }

    public void insert(WxShareDetail record) {
        getSqlSession().insert("wx_share_detail.insert", record);
    }

    public void insertSelective(WxShareDetail record) {
        getSqlSession().insert("wx_share_detail.insertSelective", record);
    }

    public void insertBatch(List<WxShareDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("wx_share_detail.insertBatch", records);
    }

    public WxShareDetail selectByPrimaryKey(Long detailId) {
        WxShareDetail _key = new WxShareDetail();
        _key.setDetailId(detailId);
        return getSqlSession().selectOne("wx_share_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(WxShareDetail record) {
        return getSqlSession().update("wx_share_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(WxShareDetail record) {
        return getSqlSession().update("wx_share_detail.updateByPrimaryKey", record);
    }
}