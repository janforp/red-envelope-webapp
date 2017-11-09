package com.iask.red_envelope.service;

import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.consts.ValueConsts;
import com.iask.red_envelope.dao.ReWithdrawDetailDAO;
import com.iask.red_envelope.model.ReWithdrawDetail;
import com.iask.red_envelope.model.vo.WithdrawRecordVo;
import com.iask.red_envelope.util.StringUtil;
import com.iask.red_envelope.util.el.ElBase;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 16/9/23.
 * 提现记录
 */
@Service
public class WithdrawService {

    @Autowired
    private ReWithdrawDetailDAO reWithdrawDetailDAO;


    /**
     * 获取用户提现记录数
     * 若没有登录,则返回0
     * @param request
     * @return
     */
    public Integer getWithdrawListNumByUserId(HttpServletRequest request){

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        if (userId == null) {
            return 0;
        }

        return reWithdrawDetailDAO.getWithdrawListNumByUserId(userId);

    }


    /**
     * 获取用户提现记录
     * @param request
     * @param pageNum
     * @return
     */
    public List<WithdrawRecordVo> getWithdrawList(HttpServletRequest request, Integer pageNum) throws Exception {

        List<ReWithdrawDetail> details = null ;
        List<WithdrawRecordVo> vos = null;

        int offset = (pageNum - 1)* ValueConsts.pageSize;

        RowBounds bounds = new RowBounds(offset,ValueConsts.pageSize);

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        if (userId != null) {

            details = reWithdrawDetailDAO.getWithdrawListByUserId(userId,bounds);

            vos = new ArrayList<>(details.size());

            for (ReWithdrawDetail detail : details) {

                vos.add(turnVo(detail));
            }
        }

        return vos;
    }

    public WithdrawRecordVo turnVo(ReWithdrawDetail detail) throws Exception {

        WithdrawRecordVo vo = null ;

        if (detail != null) {

            vo = new WithdrawRecordVo();

            BigDecimal money = detail.getApplyMoney() ;
            if (StringUtil.isEmpty(money)) {
                money = new BigDecimal("0.00");
            }else {
                money = new BigDecimal(money.toString());
            }
            vo.setMoney(money.toString());

            Integer status = detail.getWithdrawStatus();
            if (status == 0) {
                vo.setStatus("审核中");
            }else if (status == 1){
                vo.setStatus("已结算");
            }else if (status == 2) {
                vo.setStatus("未通过");
            }

            String type = detail.getWithdrawType();
            if ("huafei".equals(type)){
                vo.setType("话费");
            }else if ("zhifubao".equals(type)){
                vo.setType("支付宝");
            }else if ("weixin".equals(type)){
                vo.setType("微信");
            }

            String applyTime = detail.getApplyTime();
            if (! StringUtil.isEmpty(applyTime)){
                String timestamp = String.valueOf(ElBase.get13Timestamp(applyTime));
                String date = ElBase.fmtDay(Long.parseLong(timestamp));
                String time = ElBase.fmtHMSS(Long.parseLong(timestamp));
                vo.setDate(date);
                vo.setTime(time);
            }
        }

        return vo;

    }
}
