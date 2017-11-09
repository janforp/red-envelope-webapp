package com.iask.red_envelope.web.controller.loan_mall;

import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.model.vo.loan_mall.LoanListVo;
import com.iask.red_envelope.service.loan_mall.LoanService;
import com.iask.red_envelope.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Janita on 2016/12/19.
 * 贷款中心
 */

@RequestMapping(value = "/p/loan", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class LoanController {
    @Autowired
    private LoanService loanService;


    /**
     * 跳转到理财中心页面
     * @param request
     * @param orderType    排序规则0:贷款额度,1:利息,2:到账时间
     * @return
     */
    @RequestMapping(value = "/mall")
    public String financialMallList(HttpServletRequest request,
                                    @RequestParam(value = "orderType", defaultValue = "0") Integer orderType){

        List<LoanListVo> missions = loanService.getListOrderByType(request,orderType);
        request.setAttribute("vos",missions);

        return "/page/loan_mall/loan_mall";
    }

    /**
     *
     * @param request
     * @param orderType 排序规则0:贷款额度,1:利息,2:到账时间
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public String getList(HttpServletRequest request,
                          @RequestParam(value = "orderType", defaultValue = "0") Integer orderType){

        List<LoanListVo> missions = loanService.getListOrderByType(request,orderType);

        return JsonUtil.buildSuccessData(missions);
    }
}
