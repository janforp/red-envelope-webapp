package com.iask.red_envelope.web.controller.page.mission;

import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.model.ReAndriodIntegralWall;
import com.iask.red_envelope.service.mission.SelfIntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jan on 2016/12/5.
 * 自己的积分墙
 */
@RequestMapping(value = "/p/a/selfIntegral", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class SelfIntegralController {

    @Autowired
    private SelfIntegralService selfIntegralService;

    /**
     * 自己的积分墙的详情页
     * @param request
     * @param wallId
     * @return
     */
    @RequestMapping(value = "/detail/{wallId}")
    public String detailPage(HttpServletRequest request,
                             @PathVariable(value = "wallId")Long wallId){

        ReAndriodIntegralWall wall = selfIntegralService.getWallById(wallId);

        request.setAttribute("wall",wall);

        return "/page/mission/self-integral";
    }
}