package com.iask.red_envelope.service;

import com.iask.red_envelope.dao.ReCustomerDAO;
import com.iask.red_envelope.dao.ReCustomerExtendDAO;
import com.iask.red_envelope.model.ReCustomer;
import com.iask.red_envelope.model.ReCustomerExtend;
import com.iask.red_envelope.model.vo.ExtendDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jan on 16/8/31.
 * 推广 re_customer_extend
 */
@Service
public class ExtendService {

    @Autowired
    private ReCustomerExtendDAO reCustomerExtendDAO;
    @Autowired
    private ReCustomerDAO reCustomerDAO;

    /**
     * 获取推广详情页面数据
     * @param extendId
     * @return
     */
    public ExtendDetailVo getExtendDetail(Integer extendId) {

        ReCustomerExtend extend = reCustomerExtendDAO.selectByPrimaryKey(extendId);


        return turnExtendToVo(extend);
    }


    public ExtendDetailVo turnExtendToVo(ReCustomerExtend extend){

        Integer customerId = extend.getCustomerId() ;

        ReCustomer customer = reCustomerDAO.selectByPrimaryKey(customerId);

        ExtendDetailVo vo = new ExtendDetailVo();

        vo.setExtendId(extend.getId());
        vo.setCustomerImg(customer.getCustomerImg()==null?"":customer.getCustomerImg());
        vo.setCustomerName(customer.getCustomerName()==null?"":customer.getCustomerName());
        vo.setRedNumDayLeft(extend.getRedNumDayLeft()==null?0:extend.getRedNumDayLeft());
        vo.setRedNumDayTotal(extend.getRedNumDayTotal()==null?0:extend.getRedNumDayTotal());
        vo.setStepRule(extend.getStepRule()==null?"":extend.getStepRule());

        return vo;
    }
}
