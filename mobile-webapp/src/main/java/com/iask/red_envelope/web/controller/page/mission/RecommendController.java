package com.iask.red_envelope.web.controller.page.mission;

import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.model.vo.mission.ExchangeCodeMissionDetail;
import com.iask.red_envelope.model.vo.mission.RegisterMissionVo;
import com.iask.red_envelope.model.vo.mission.VerifyMissionDetail;
import com.iask.red_envelope.model.vo.mission.RecommendStep;
import com.iask.red_envelope.service.RecommendMissionService;
import com.iask.red_envelope.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Summer on 2016/10/31.
 * re_recommend_mission表
 */
@RequestMapping(value = "/p/recommend", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class RecommendController {

    @Autowired
    private RecommendMissionService recommendMissionService;

    /**
     * 进入任务详情页面
     * @param request
     * @param missionId
     * @return
     */
    @RequestMapping(value = "/detail/{missionId}")
    public String detail(HttpServletRequest request,
                         @PathVariable(value = "missionId") Long missionId){

        // 用户id
        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        if(userId == null) {
            request.setAttribute("isLogin", false);
        }else {
            request.setAttribute("isLogin", true);
        }

        // 是否是ios
        boolean isIos = CommonMethod.isAppleBrower(request);
        request.setAttribute("isIos", isIos);

        // 任务步骤
        List<RecommendStep> stepList = recommendMissionService.getSteps(missionId, isIos);
        request.setAttribute("stepList", stepList);

        // 获取不同任务页面详情数据
        Integer missionType = recommendMissionService.getMissionType(missionId);
        if(missionType != null) {

            int type = missionType.intValue();

            if (type == 0){ // 0:高额任务

                VerifyMissionDetail detail = recommendMissionService.getVerifyMissionDetail(request,missionId, userId);
                request.setAttribute("detail", detail);
                return "page/mission/verify-mission-detail";

            }else if (type == 1){ // 1:兑换码红包

                ExchangeCodeMissionDetail detail = recommendMissionService.getExchangeMissionDetail(missionId, userId);
                request.setAttribute("detail", detail);
                return "page/mission/exchange-code-mission-detail";

            }else if (type == 2){ //2.类似与对接任务,用户只要通过我们提高的按钮链接到他的注册页面,通过回调获得相应奖励

                RegisterMissionVo detail = recommendMissionService.getRegisterMissionDetail(missionId,userId);
                request.setAttribute("detail",detail);
                return "/page/mission/register-mission-detail";
            }
        }
        return "error/404";
    }



    /**
     * 任务超时
     * @param request
     * @param missionId
     * @return
     */
    @RequestMapping(value = "/overtime")
    @ResponseBody
    public String invalid(HttpServletRequest request,Long missionId){

        return recommendMissionService.invalid(request,missionId);
    }
}
