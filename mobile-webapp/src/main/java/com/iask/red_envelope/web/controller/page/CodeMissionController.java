package com.iask.red_envelope.web.controller.page;

import com.iask.red_envelope.consts.ParamConsts;
import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.service.mission.ExchangeCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 兑换码 任务
 * Created by Summer on 2016/11/10.
 */
@RequestMapping(value = "/p/code", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class CodeMissionController {

    @Autowired
    private ExchangeCodeService exchangeCodeService;

    /**
     * 兑换 页面
     *
     * @param request
     * @param codeId
     * @return
     */
    @RequestMapping(value = "/page/{codeId}")
    public String page(HttpServletRequest request,
                       @PathVariable(value = "codeId") String codeId) {
        request.setAttribute("codeId", codeId);
        return "page/mission/code-exchange";
    }


    /**
     * 兑换
     *
     * @param request
     * @param codeId
     * @param code
     * @return
     */
    @RequestMapping(value = "/exchange")
    @ResponseBody
    public String exchange(HttpServletRequest request,
                           @RequestParam(value = ParamConsts.codeId) String codeId,
                           @RequestParam(value = ParamConsts.code) String code) {
        return exchangeCodeService.exchange(codeId, code);
    }

}
