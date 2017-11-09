package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReCustomer;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-07-19
 */
@Repository
public class ReCustomerDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer customerId) {
        ReCustomer _key = new ReCustomer();
        _key.setCustomerId(customerId);
        return getSqlSession().delete("re_customer.deleteByPrimaryKey", _key);
    }

    public void insert(ReCustomer record) {
        getSqlSession().insert("re_customer.insert", record);
    }

    public void insertSelective(ReCustomer record) {
        getSqlSession().insert("re_customer.insertSelective", record);
    }

    public void insertBatch(List<ReCustomer> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_customer.insertBatch", records);
    }

    public ReCustomer selectByPrimaryKey(Integer customerId) {
        ReCustomer _key = new ReCustomer();
        _key.setCustomerId(customerId);
        return getSqlSession().selectOne("re_customer.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReCustomer record) {
        return getSqlSession().update("re_customer.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReCustomer record) {
        return getSqlSession().update("re_customer.updateByPrimaryKey", record);
    }

    public ReCustomer selectByCustomerSecret(String customerSecret) {
        return getSqlSession().selectOne("re_customer.selectByCustomerSecret", customerSecret);
    }

    /**
     * 所有代开发的微信公众号
     * @return
     */
    public List<ReCustomer> selectAllDevelopmentWxApp() {
        return getSqlSession().selectList("re_customer.selectAllDevelopmentWxApp");
    }


}