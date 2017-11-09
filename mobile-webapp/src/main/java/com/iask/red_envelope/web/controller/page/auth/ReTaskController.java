package com.iask.red_envelope.web.controller.page.auth;

import com.iask.red_envelope.consts.ParamConsts;
import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.model.dto.ReTaskInfo;
import com.iask.red_envelope.model.dto.TaskDetailInfo;
import com.iask.red_envelope.service.ReTaskService;
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
 * Created by Summer on 2016/10/17.
 */
@Controller
@RequestMapping(value = "/p/a/task", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class ReTaskController {

    @Autowired
    private ReTaskService reTaskService;

    /**
     * 过渡页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request) {
        return "page/task/index";
    }


    /**
     * 记录用户app列表
     *
     * @param request
     * @param app
     * @return
     */
    @RequestMapping(value = "/app")
    @ResponseBody
    public String app(HttpServletRequest request,
                      @RequestParam(value = ParamConsts.app, required = false) String app) {
        int userId = (int) request.getAttribute(RequestConsts.ATTR_USER_ID);
        return reTaskService.saveAppData(userId, app);
    }


    /**
     * 限时任务列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/dataList")
    @ResponseBody
    public String dataList(HttpServletRequest request) {
        List<ReTaskInfo> list = reTaskService.getTimedList(request);
        return JsonUtil.buildSuccessData(list);
    }


    /**
     * 限时任务 页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/timedList")
    public String timedList(HttpServletRequest request) {
//        List<ReTaskInfo> list = reTaskService.getTimedList(request);
//        request.setAttribute("list", list);
        return "page/task/timed_list";
    }

    /**
     * 任务是否超时了
     * @param request
     * @param keywordId
     * @return
     */
    @RequestMapping(value = "/isOvertime")
    @ResponseBody
    public String isOvertime(HttpServletRequest request,
                             @RequestParam(value = ParamConsts.keywordId, required = false)Long keywordId){

        return reTaskService.isOvertime(request,keywordId);

    }

    /**
     * 显示任务详情 页面
     * @param request
     * @param keywordId
     * @return
     */
    @RequestMapping(value = "/timedDetail")
    public String timedDetail(HttpServletRequest request,
                              @RequestParam(value = ParamConsts.keywordId, required = false)Long keywordId){

        TaskDetailInfo info = reTaskService.getTaskDetailByKeywordId(request,keywordId);

        request.setAttribute("detail",info);

        return "page/task/timed-detail";
    }

    /**
     * 页面倒计时结束,任务自动放弃
     * @param request
     * @param keywordId
     * @return
     */
    @RequestMapping(value = "/overtimeToGiveUp")
    @ResponseBody
    public String overtime(HttpServletRequest request,
                           @RequestParam(value = ParamConsts.keywordId, required = false)Long keywordId){

        return reTaskService.overtimeToGiveUp(request,keywordId);
    }


    /**
     * 该用户要放弃之前的任务,接受id为keywordId的新任务
     * @param request
     * @param keywordId
     * @return
     */
    @RequestMapping(value = "/anotherMission")
    @ResponseBody
    public String getAnotherMission(HttpServletRequest request,
                                    @RequestParam(value = ParamConsts.keywordId, required = false)Long keywordId){

        return reTaskService.getAnotherMission(request,keywordId);
    }

    /**
     * 用户抢到了此任务ID:keywordId
     * @param request
     * @param keywordId
     * @return
     */
    @RequestMapping(value = "/getMission")
    @ResponseBody
    public String getMission(HttpServletRequest request,
                             @RequestParam(value = ParamConsts.keywordId, required = false)Long keywordId){

        //抢到任务逻辑
        return reTaskService.getMission(request,keywordId);
    }

    /**
     * 完成任务,领取奖励
     * @param request
     * @param keywordId
     * @return
     */
    @RequestMapping(value = "/getMoney")
    @ResponseBody
    public String getMoney(HttpServletRequest request,
                           @RequestParam(value = ParamConsts.keywordId, required = false)Long keywordId){

        return reTaskService.getMoney(request,keywordId);
    }



}
