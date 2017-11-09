package com.iask.red_envelope.web.controller.page.mission;

import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.model.vo.app_click_share_mission.ClickShareDetailVo;
import com.iask.red_envelope.service.app_share.AppShareService;
import com.iask.red_envelope.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Jan on 16/11/19.
 * 分享任务详情
 */
@Controller
@RequestMapping(value = "/p/a/appShare", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class ClickShareMissionDetailController {

    @Autowired
    private AppShareService appShareService;

    /**
     * app列表上点击进入分享点击任务详情页面
     * 此时应该生成该用户的短链接
     * IP+PORT/c/p/appShare/click/missionId/userKey
     * 这个地址就是他的好友点击的链接
     *
     * @param request
     * @param missionId 任务ID
     * @return
     */
    @RequestMapping(value = "/detail/{missionId}")
    public String detail(HttpServletRequest request,
                         @PathVariable(value = "missionId")Long missionId) throws IOException {

        ClickShareDetailVo vo = appShareService.getDetail(request, missionId);
        request.setAttribute("vo", vo);
        Boolean isIos = CommonMethod.isAppleBrower(request);
        request.setAttribute("isIos",isIos);

        return "/page/app-click-share-mission/click-share-mission-detail";
    }
}
