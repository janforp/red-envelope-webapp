package com.iask.red_envelope.web.controller.page.auth;

import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.model.ReUserFeedback;
import com.iask.red_envelope.service.FeedbackService;
import com.iask.red_envelope.util.CommonMethod;
import com.iask.red_envelope.util.el.ElBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Summer on 16/9/20.
 */
@Controller
@RequestMapping(value = "/p/a/feedback", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    /**
     * 反馈页面
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request) {

        Boolean isIos = CommonMethod.isAppleBrower(request);
        request.setAttribute("isIos", isIos);

        return "page/feedback/feedback";

    }

    /**
     * 提交
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(HttpServletRequest request, ReUserFeedback feedback) {

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        feedback.setUserId(userId);
        feedback.setFeedbackTime(ElBase.fmtTime(System.currentTimeMillis()));

        return feedbackService.save(feedback);

    }

}
