package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReUserCommissionWithdraw;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-09-21
 */
@Repository
public class ReUserCommissionWithdrawDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long id) {
        ReUserCommissionWithdraw _key = new ReUserCommissionWithdraw();
        _key.setId(id);
        return getSqlSession().delete("re_user_commission_withdraw.deleteByPrimaryKey", _key);
    }

    public void insert(ReUserCommissionWithdraw record) {
        getSqlSession().insert("re_user_commission_withdraw.insert", record);
    }

    public void insertSelective(ReUserCommissionWithdraw record) {
        getSqlSession().insert("re_user_commission_withdraw.insertSelective", record);
    }

    public void insertBatch(List<ReUserCommissionWithdraw> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_user_commission_withdraw.insertBatch", records);
    }

    public ReUserCommissionWithdraw selectByPrimaryKey(Long id) {
        ReUserCommissionWithdraw _key = new ReUserCommissionWithdraw();
        _key.setId(id);
        return getSqlSession().selectOne("re_user_commission_withdraw.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReUserCommissionWithdraw record) {
        return getSqlSession().update("re_user_commission_withdraw.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReUserCommissionWithdraw record) {
        return getSqlSession().update("re_user_commission_withdraw.updateByPrimaryKey", record);
    }
}