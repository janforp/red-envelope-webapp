package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReWithdrawDetail;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-09-23
 */
@Repository
public class ReWithdrawDetailDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long withdrawId) {
        ReWithdrawDetail _key = new ReWithdrawDetail();
        _key.setWithdrawId(withdrawId);
        return getSqlSession().delete("re_withdraw_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReWithdrawDetail record) {
        getSqlSession().insert("re_withdraw_detail.insert", record);
    }

    public void insertSelective(ReWithdrawDetail record) {
        getSqlSession().insert("re_withdraw_detail.insertSelective", record);
    }

    public void insertBatch(List<ReWithdrawDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_withdraw_detail.insertBatch", records);
    }

    public ReWithdrawDetail selectByPrimaryKey(Long withdrawId) {
        ReWithdrawDetail _key = new ReWithdrawDetail();
        _key.setWithdrawId(withdrawId);
        return getSqlSession().selectOne("re_withdraw_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReWithdrawDetail record) {
        return getSqlSession().update("re_withdraw_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReWithdrawDetail record) {
        return getSqlSession().update("re_withdraw_detail.updateByPrimaryKey", record);
    }

    /**
     * 提现记录数量
     * @param userId
     * @return
     */
    public Integer getWithdrawListNumByUserId(Integer userId) {
        return getSqlSession().selectOne("re_withdraw_detail.getWithdrawListNumByUserId",userId);
    }

    /**
     * 获取个人提现记录
     * @param userId
     * @return
     */
    public List<ReWithdrawDetail> getWithdrawListByUserId(Integer userId, RowBounds bounds){
        return getSqlSession().selectList("re_withdraw_detail.getWithdrawListByUserId",userId,bounds);
    }
}