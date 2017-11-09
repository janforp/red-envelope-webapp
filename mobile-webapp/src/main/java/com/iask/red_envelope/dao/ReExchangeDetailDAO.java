package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReExchangeDetail;

import com.iask.red_envelope.model.ReScoreDetail;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-09-09
 */
@Repository
public class ReExchangeDetailDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long id) {
        ReExchangeDetail _key = new ReExchangeDetail();
        _key.setId(id);
        return getSqlSession().delete("re_exchange_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReExchangeDetail record) {
        getSqlSession().insert("re_exchange_detail.insert", record);
    }

    public void insertSelective(ReExchangeDetail record) {
        getSqlSession().insert("re_exchange_detail.insertSelective", record);
    }

    public void insertBatch(List<ReExchangeDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_exchange_detail.insertBatch", records);
    }

    public ReExchangeDetail selectByPrimaryKey(Long id) {
        ReExchangeDetail _key = new ReExchangeDetail();
        _key.setId(id);
        return getSqlSession().selectOne("re_exchange_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReExchangeDetail record) {
        return getSqlSession().update("re_exchange_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReExchangeDetail record) {
        return getSqlSession().update("re_exchange_detail.updateByPrimaryKey", record);
    }

    /**
     * 获取个人的兑换记录
     * @param userId
     * @return
     */
    public List<ReExchangeDetail> getExchangeList(Integer userId, RowBounds bounds) {

        return getSqlSession().selectList("re_exchange_detail.getExchangeList",userId, bounds);
    }

    /**
     * 个人记录数
     * @param userId
     * @return
     */
    public Integer getTotalNum(Integer userId) {

        return getSqlSession().selectOne("re_exchange_detail.getTotalNum",userId) ;
    }
}