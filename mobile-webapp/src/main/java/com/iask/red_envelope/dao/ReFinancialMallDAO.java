package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReFinancialMall;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-11-18
 */
@Repository
public class ReFinancialMallDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long id) {
        ReFinancialMall _key = new ReFinancialMall();
        _key.setId(id);
        return getSqlSession().delete("re_financial_mall.deleteByPrimaryKey", _key);
    }

    public void insert(ReFinancialMall record) {
        getSqlSession().insert("re_financial_mall.insert", record);
    }

    public void insertSelective(ReFinancialMall record) {
        getSqlSession().insert("re_financial_mall.insertSelective", record);
    }

    public void insertBatch(List<ReFinancialMall> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_financial_mall.insertBatch", records);
    }

    public ReFinancialMall selectByPrimaryKey(Long id) {
        ReFinancialMall _key = new ReFinancialMall();
        _key.setId(id);
        return getSqlSession().selectOne("re_financial_mall.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReFinancialMall record) {
        return getSqlSession().update("re_financial_mall.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReFinancialMall record) {
        return getSqlSession().update("re_financial_mall.updateByPrimaryKey", record);
    }

    /**
     *
     * @param orderType     排序规则0:综合性排序,1:按收益排序,2:按流动性排序
     * @param platform      显示平台:1:苹果设备,0:安卓设备
     * @return
     */
    public List<ReFinancialMall> getListOrderByType(Integer orderType,Integer platform){

        Map<String,Object> params = new HashMap<>(2);
        params.put("orderType",orderType);
        params.put("platform",platform);

        return getSqlSession().selectList("re_financial_mall.getListOrderByType",params);
    }
}