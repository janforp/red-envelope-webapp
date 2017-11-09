package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReUserCommissionDetail;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-09-21
 */
@Repository
public class ReUserCommissionDetailDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long id) {
        ReUserCommissionDetail _key = new ReUserCommissionDetail();
        _key.setId(id);
        return getSqlSession().delete("re_user_commission_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReUserCommissionDetail record) {
        getSqlSession().insert("re_user_commission_detail.insert", record);
    }

    public void insertSelective(ReUserCommissionDetail record) {
        getSqlSession().insert("re_user_commission_detail.insertSelective", record);
    }

    public void insertBatch(List<ReUserCommissionDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_user_commission_detail.insertBatch", records);
    }

    public ReUserCommissionDetail selectByPrimaryKey(Long id) {
        ReUserCommissionDetail _key = new ReUserCommissionDetail();
        _key.setId(id);
        return getSqlSession().selectOne("re_user_commission_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReUserCommissionDetail record) {
        return getSqlSession().update("re_user_commission_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReUserCommissionDetail record) {
        return getSqlSession().update("re_user_commission_detail.updateByPrimaryKey", record);
    }

    /**
     * 分页查询佣金详情
     * @param userId
     * @param bounds
     * @return
     */
    public List<ReUserCommissionDetail> getCommissionList(Integer userId , RowBounds bounds) {

        return getSqlSession().selectList("re_user_commission_detail.getCommissionList" , userId , bounds);
    }

    /**
     * 获取用户的佣金详情记录数量
     * @param userId
     * @return
     */
    public Integer getCommissionNumByUserId(Integer userId) {

        return getSqlSession().selectOne("re_user_commission_detail.getCommissionNumByUserId" , userId);
    }
}