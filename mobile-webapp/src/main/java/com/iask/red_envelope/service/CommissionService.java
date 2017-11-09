package com.iask.red_envelope.service;

import com.iask.red_envelope.consts.JsonCodeConsts;
import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.consts.ValueConsts;
import com.iask.red_envelope.dao.ReUserCommissionDAO;
import com.iask.red_envelope.dao.ReUserCommissionDetailDAO;
import com.iask.red_envelope.dao.ReUserCommissionWithdrawDAO;
import com.iask.red_envelope.model.ReUserCommission;
import com.iask.red_envelope.model.ReUserCommissionDetail;
import com.iask.red_envelope.model.ReUserCommissionWithdraw;
import com.iask.red_envelope.util.JsonUtil;
import com.iask.red_envelope.util.el.ElBase;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Jan on 16/9/21.
 * 邀请佣金
 */
@Service
public class CommissionService {

    @Autowired
    private ReUserCommissionDetailDAO reUserCommissionDetailDAO ;
    @Autowired
    private ReUserCommissionDAO reUserCommissionDAO;
    @Autowired
    private ReUserCommissionWithdrawDAO reUserCommissionWithdrawDAO;

    /**
     * 获取佣金明细列表
     * @param request
     * @param pageNum
     * @return
     */
    public List<ReUserCommissionDetail> getCommissionList(HttpServletRequest request,Integer pageNum) {

        int offset = (pageNum - 1) * ValueConsts.pageSize ;

        RowBounds bounds = new RowBounds(offset , ValueConsts.pageSize) ;

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        List<ReUserCommissionDetail> details = reUserCommissionDetailDAO.getCommissionList(userId , bounds);

        return details ;
    }

    /**
     * 获取用户的佣金详情记录数量
     * @param request
     * @return
     */
    public Integer getCommissionNum(HttpServletRequest request) {

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        return reUserCommissionDetailDAO.getCommissionNumByUserId(userId);
    }

    /**
     * 获取总佣金
     * @param request
     * @return
     */
    public BigDecimal getTotalCommissionByUserId(HttpServletRequest request) {

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        ReUserCommission commission = reUserCommissionDAO.selectByPrimaryKey(userId) ;

        if (commission != null) {

            return commission.getTotalMoney();
        }

        return new BigDecimal("0.00");
    }

    /**
     * 获取总佣金
     * @param request
     * @return
     */
    public BigDecimal getCurrentCommissionByUserId(HttpServletRequest request) {

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        ReUserCommission commission = reUserCommissionDAO.selectByPrimaryKey(userId) ;

        if (commission != null) {

            return commission.getCurrentMoney();
        }

        return new BigDecimal("0.00");
    }

    /**
     * 佣金提现
     * @param request
     * @param money
     * @return
     */
    public String doWithdraw(HttpServletRequest request,BigDecimal money){

        Long now = System.currentTimeMillis();

        if (money.compareTo(new BigDecimal(10)) < 0) {
            return JsonUtil.buildError(JsonCodeConsts.error_normal,"提现最低额度为10元");
        }

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
//        userId = 32 ;
        if (userId == null) {
            return JsonUtil.buildError(JsonCodeConsts.error_normal,"请登录");
        }
        ReUserCommission commission = reUserCommissionDAO.selectByPrimaryKey( userId);
        if (commission == null) {
            return JsonUtil.buildError(JsonCodeConsts.error_normal,"佣金账户不存在");
        }
        BigDecimal currentMoney = commission.getCurrentMoney() ;
        if (currentMoney.compareTo(money) < 0){
            return JsonUtil.buildError(JsonCodeConsts.error_normal,"佣金不足");
        }

        commission.setCurrentMoney(currentMoney.subtract(money));
        reUserCommissionDAO.updateByPrimaryKeySelective(commission);

        ReUserCommissionWithdraw withdraw = new ReUserCommissionWithdraw() ;
        withdraw.setUserId(userId);
        withdraw.setWithdrawStatus(0);
        withdraw.setApplyMoney(money);
        withdraw.setApplyTime(ElBase.fmtTime(now));

        reUserCommissionWithdrawDAO.insertSelective(withdraw);

        return JsonUtil.buildSuccess("成功提现"+money+"元");
    }
}

