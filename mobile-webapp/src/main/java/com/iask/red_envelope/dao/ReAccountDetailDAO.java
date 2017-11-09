package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReAccountDetail;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-08-16
 */
@Repository
public class ReAccountDetailDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long detailId) {
        ReAccountDetail _key = new ReAccountDetail();
        _key.setDetailId(detailId);
        return getSqlSession().delete("re_account_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReAccountDetail record) {
        getSqlSession().insert("re_account_detail.insert", record);
    }

    public void insertSelective(ReAccountDetail record) {
        getSqlSession().insert("re_account_detail.insertSelective", record);
    }

    public void insertBatch(List<ReAccountDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_account_detail.insertBatch", records);
    }

    public ReAccountDetail selectByPrimaryKey(Long detailId) {
        ReAccountDetail _key = new ReAccountDetail();
        _key.setDetailId(detailId);
        return getSqlSession().selectOne("re_account_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReAccountDetail record) {
        return getSqlSession().update("re_account_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReAccountDetail record) {
        return getSqlSession().update("re_account_detail.updateByPrimaryKey", record);
    }

    /**
     * 获取次用户 上次签到的纪录
     * @param userId
     * @return
     */
    public ReAccountDetail getLastSignDetail(Integer userId){

        return getSqlSession().selectOne("re_account_detail.getLastSignDetail",userId);
    }

    /**
     * 获取个人的记录
     * @param userId
     * @return
     */
    public List<ReAccountDetail> getMoneyList(Integer userId, RowBounds bounds) {

        return getSqlSession().selectList("re_account_detail.getMoneyList",userId, bounds);
    }

    /**
     * 个人记录数
     * @param userId
     * @return
     */
    public Integer getTotalNum(Integer userId) {

        return getSqlSession().selectOne("re_account_detail.getTotalNum",userId) ;
    }
}