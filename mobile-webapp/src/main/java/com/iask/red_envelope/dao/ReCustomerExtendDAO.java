package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReCustomerExtend;

import com.iask.red_envelope.model.vo.RedVo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-07-19
 */
@Repository
public class ReCustomerExtendDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer id) {
        ReCustomerExtend _key = new ReCustomerExtend();
        _key.setId(id);
        return getSqlSession().delete("re_customer_extend.deleteByPrimaryKey", _key);
    }

    public void insert(ReCustomerExtend record) {
        getSqlSession().insert("re_customer_extend.insert", record);
    }

    public void insertSelective(ReCustomerExtend record) {
        getSqlSession().insert("re_customer_extend.insertSelective", record);
    }

    public void insertBatch(List<ReCustomerExtend> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_customer_extend.insertBatch", records);
    }

    public ReCustomerExtend selectByPrimaryKey(Integer id) {
        ReCustomerExtend _key = new ReCustomerExtend();
        _key.setId(id);
        return getSqlSession().selectOne("re_customer_extend.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReCustomerExtend record) {
        return getSqlSession().update("re_customer_extend.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReCustomerExtend record) {
        return getSqlSession().update("re_customer_extend.updateByPrimaryKey", record);
    }

    /**
     * 查询推广详情
     * @param customerId
     * @return
     */
    public ReCustomerExtend selectLockByCustomerId(int customerId) {
        return getSqlSession().selectOne("re_customer_extend.selectLockByCustomerId", customerId);
    }

    /**
     * 查询3条当日剩余红包最多的推广记录
     * @param customerId
     * @return
     */
    public List<RedVo> selectTop3RedList(int customerId) {
        return getSqlSession().selectList("re_customer_extend.selectTop3RedList", customerId);
    }

    /**
     * 查询进行中的推广记录
     * @return
     */
    public List<RedVo> selectRedList() {
        return getSqlSession().selectList("re_customer_extend.selectRedList");
    }

    /**
     * 查询已结束的推广记录
     * @param id
     * @return
     */
    public List<RedVo> selectFinishRedList(int id) {
        return getSqlSession().selectList("re_customer_extend.selectFinishRedList", id);
    }

    /**
     * 查询banner
     * @param customerId    客户id
     * @param id            推广id
     * @return
     */
    public ReCustomerExtend selectBanner(int customerId, int id) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("customerId", customerId);
        map.put("id", id);
        return getSqlSession().selectOne("re_customer_extend.selectBanner", map);
    }


}