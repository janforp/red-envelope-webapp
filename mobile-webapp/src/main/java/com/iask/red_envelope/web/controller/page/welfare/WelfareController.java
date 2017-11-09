package com.iask.red_envelope.web.controller.page.welfare;

/**
 * Created by Janita on 2016/12/14.
 * 福利
 */

import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.model.ReWelfare;
import com.iask.red_envelope.service.mission.WelfareService;
import com.iask.red_envelope.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value = "/p/a/welfare", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class WelfareController {

    @Autowired
    private WelfareService welfareService;

    /**
     * 查询福利详情页面
     * @param request
     * @param welfareId 点击查看的id
     * @return
     */
    @RequestMapping(value = "/detail/{welfareId}")
    public String welfare(HttpServletRequest request,
                          @PathVariable(value = "welfareId")Long welfareId){

        ReWelfare welfare = welfareService.getWelfareById(welfareId);
        request.setAttribute("welfare",welfare);

        Boolean isIos = CommonMethod.isAppleBrower(request);
        request.setAttribute("isIos",isIos);

        return "/page/welfare/welfare";
    }
}
