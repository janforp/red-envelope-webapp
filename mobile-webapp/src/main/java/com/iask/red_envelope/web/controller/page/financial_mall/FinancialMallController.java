package com.iask.red_envelope.web.controller.page.financial_mall;

import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.model.ReFinancialMall;
import com.iask.red_envelope.model.vo.financial_mall.FinancialListVo;
import com.iask.red_envelope.service.financial_mall.FinancialMallService;
import com.iask.red_envelope.util.CommonMethod;
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
 * Created by Jan on 16/11/18.
 * 理财大厅
 */
@RequestMapping(value = "/p/financial", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class FinancialMallController {

    @Autowired
    private FinancialMallService financialMallService;


    /**
     * 跳转到理财中心页面
     * @param request
     * @param orderType     排序规则0:综合性排序,1:按收益排序,2:按流动性排序
     * @return
     */
    @RequestMapping(value = "/mall")
    public String financialMallList(HttpServletRequest request,
                                   @RequestParam(value = "orderType", defaultValue = "0") Integer orderType){

        request.setAttribute("isIos", CommonMethod.isAppleBrower(request));
        List<FinancialListVo> missions = financialMallService.getListOrderByType(request,orderType);
        request.setAttribute("vos",missions);

        return "/page/financial_mall/financial_mall";
    }

    /**
     *
     * @param request
     * @param orderType 排序规则0:综合性排序,1:按收益排序,2:按流动性排序
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public String getList(HttpServletRequest request,
                          @RequestParam(value = "orderType", defaultValue = "0") Integer orderType){

        List<FinancialListVo> missions = financialMallService.getListOrderByType(request,orderType);

        return JsonUtil.buildSuccessData(missions);
    }
}
