package com.iask.red_envelope.web.controller.page.auth;

import com.iask.red_envelope.consts.ParamConsts;
import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.service.RecommendMissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Summer on 2016/10/31.
 */
@RequestMapping(value = "/p/a/recommend", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class RecommendMissionController {

    @Autowired
    private RecommendMissionService recommendMissionService;

    /**
     * 抢任务
     *
     * @param request
     * @param missionId
     * @return
     */
    @RequestMapping(value = "/getMission")
    @ResponseBody
    public String getMission(HttpServletRequest request,
                             @RequestParam(value = ParamConsts.id, required = true) Long missionId) {

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        return recommendMissionService.getMission(userId, missionId);

    }
}
