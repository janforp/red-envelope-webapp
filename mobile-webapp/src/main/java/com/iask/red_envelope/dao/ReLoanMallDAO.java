package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReFinancialMall;
import com.iask.red_envelope.model.ReLoanMall;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-12-19
 */
@Repository
public class ReLoanMallDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long id) {
        ReLoanMall _key = new ReLoanMall();
        _key.setId(id);
        return getSqlSession().delete("re_loan_mall.deleteByPrimaryKey", _key);
    }

    public void insert(ReLoanMall record) {
        getSqlSession().insert("re_loan_mall.insert", record);
    }

    public void insertSelective(ReLoanMall record) {
        getSqlSession().insert("re_loan_mall.insertSelective", record);
    }

    public void insertBatch(List<ReLoanMall> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_loan_mall.insertBatch", records);
    }

    public ReLoanMall selectByPrimaryKey(Long id) {
        ReLoanMall _key = new ReLoanMall();
        _key.setId(id);
        return getSqlSession().selectOne("re_loan_mall.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReLoanMall record) {
        return getSqlSession().update("re_loan_mall.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReLoanMall record) {
        return getSqlSession().update("re_loan_mall.updateByPrimaryKey", record);
    }


    /**
     *
     * @param orderType     排序规则0:贷款额度,1:利息,2:到账时间
     * @return
     */
    public List<ReLoanMall> getListOrderByType(Integer orderType){

        Map<String,Object> map = new HashMap<>(1);
        map.put("orderType",orderType);

        return getSqlSession().selectList("re_loan_mall.getListOrderByType",map);
    }
}