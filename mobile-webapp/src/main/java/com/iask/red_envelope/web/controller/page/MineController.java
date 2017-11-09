package com.iask.red_envelope.web.controller.page;

import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.util.CommonMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jan on 16/9/20.
 * App上的我们菜单里面的
 * 1.商务合作页面
 * 2.联系客服页面
 */
@RequestMapping(value = "/p/mine", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class MineController {

    /**
     * 去商务合作页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/cooperation")
    public String gotoCooperation(HttpServletRequest request) {
        return "page/mine/business_cooperation";
    }

    /**
     * 去联系客户页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/contact")
    public String gotoCustomer(HttpServletRequest request) {
        return "page/mine/contact_us";
    }


    /**
     * 评价APP
     * @param request
     * @return
     */
    @RequestMapping(value = "/valuation")
    public String valuation(HttpServletRequest request) {
        boolean isIos = CommonMethod.isAppleBrower(request);
        request.setAttribute("isIos", isIos);
        return "page/mine/valuation";
    }

    /**
     * 使用帮助
     * @param request
     * @return
     */
    @RequestMapping(value = "/help")
    public String help(HttpServletRequest request) {
        return "page/mine/help";
    }


}
