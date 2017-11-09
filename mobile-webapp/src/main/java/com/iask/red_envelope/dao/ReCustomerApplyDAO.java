package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReCustomerApply;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-07-19
 */
@Repository
public class ReCustomerApplyDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer id) {
        ReCustomerApply _key = new ReCustomerApply();
        _key.setId(id);
        return getSqlSession().delete("re_customer_apply.deleteByPrimaryKey", _key);
    }

    public void insert(ReCustomerApply record) {
        getSqlSession().insert("re_customer_apply.insert", record);
    }

    public void insertSelective(ReCustomerApply record) {
        getSqlSession().insert("re_customer_apply.insertSelective", record);
    }

    public void insertBatch(List<ReCustomerApply> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_customer_apply.insertBatch", records);
    }

    public ReCustomerApply selectByPrimaryKey(Integer id) {
        ReCustomerApply _key = new ReCustomerApply();
        _key.setId(id);
        return getSqlSession().selectOne("re_customer_apply.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReCustomerApply record) {
        return getSqlSession().update("re_customer_apply.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReCustomerApply record) {
        return getSqlSession().update("re_customer_apply.updateByPrimaryKey", record);
    }

    /**
     * 查询申请记录
     * @param customerId 客户id
     * @param drawParam 参数
     * @return
     */
    public ReCustomerApply selectByIdAndParam(int customerId, String drawParam) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("customerId", customerId);
        map.put("drawParam", drawParam);
        return getSqlSession().selectOne("re_customer_apply.selectByIdAndParam", map);
    }

}