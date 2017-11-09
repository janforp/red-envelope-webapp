package com.iask.red_envelope.web.controller.page;


import com.iask.red_envelope.consts.ParamConsts;
import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.consts.ValueConsts;
import com.iask.red_envelope.dao.ReUserCommissionDAO;
import com.iask.red_envelope.model.ReUserCommissionDetail;
import com.iask.red_envelope.service.CommissionService;
import com.iask.red_envelope.util.CommonMethod;
import com.iask.red_envelope.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 16/9/21.
 * 邀请佣金
 */
@RequestMapping(value = "/p/commission", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class CommissionController {

    @Autowired
    private CommissionService commissionService;


    /**
     * 佣金记录,
     * 默认第一页
     */
    @RequestMapping(value = "/commissionList")
    public String goCommissionDetailList(HttpServletRequest request){

        List<ReUserCommissionDetail> details = commissionService.getCommissionList(request,1);

        Integer total = commissionService.getCommissionNum(request);

        BigDecimal commission = commissionService.getTotalCommissionByUserId(request);

        Integer totalPage = total % ValueConsts.pageSize > 0 ? total / ValueConsts.pageSize + 1 : total / ValueConsts.pageSize;

        Integer pageNum = 1 ;

        if (total == 0) {

            pageNum = 0 ;
            totalPage = 0 ;
        }

        request.setAttribute("details" , details);
        request.setAttribute("commission" , commission);
        request.setAttribute("totalPage" , totalPage);
        request.setAttribute("pageNum" , pageNum);
        request.setAttribute("total" , total);

        boolean isIos = CommonMethod.isAppleBrower(request);
        if(isIos) {
            return "page/commission/commission_list_ios";
        }

        return "page/commission/commission_list_andriod";

    }

    /**
     * 上下滑动
     * 加载新的佣金记录
     * @param request
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/turnCommission")
    @ResponseBody
    public String turnCommissionListPage(HttpServletRequest request ,
                                         @RequestParam(value = ParamConsts.pageNum, defaultValue = "1") Integer pageNum) {

        List<ReUserCommissionDetail> details = commissionService.getCommissionList(request,pageNum);

        Map<String,Object> map = new HashMap<>(4);

        map.put("details",details);
        map.put("pageNum",pageNum);

        return JsonUtil.buildSuccessData(map);
    }

    /**
     * 申请提现页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/withdrawPage")
    public String goWithdrawPage(HttpServletRequest request){

        BigDecimal currentMoney = commissionService.getCurrentCommissionByUserId(request);

        request.setAttribute("money",currentMoney);

        return "page/commission/withdraw_page";
    }

    /**
     * 提现
     * @param request
     * @return
     */
    @RequestMapping(value = "/withdraw")
    @ResponseBody
    public String withdraw(HttpServletRequest request,
                           @RequestParam(value = "money") BigDecimal money) {

        return commissionService.doWithdraw(request,money);
    }
}
