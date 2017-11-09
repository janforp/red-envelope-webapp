package com.iask.red_envelope.web.controller.page.mission;

import com.hongbao.api.model.cache.UserInfo;
import com.iask.red_envelope.config.Config;
import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.service.user.UserCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jan on 16/11/19.
 * re_commend_mission表中 type=2 的任务:
 * 一般为点击按钮跳转到客户的注册页面之类的
 * 用户点击按钮时,调用次接口,会经过登录过滤器
 *
 * 中转控制
 */
@Controller
@RequestMapping(value = "/p/a/transit", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class MissionTransitController {

    @Autowired
    private UserCacheService userCacheService;

    /**
     * 高额任务表中,type=2的任务,用户点击的时候
     * @param request
     * @return
     */
    @RequestMapping(value = "/go")
    public String gotoCustomer(HttpServletRequest request){

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        UserInfo info = userCacheService.getUser(userId);
        String userKey = info.getUserKey();
        return "redirect:http://www.baidu.com?userKey="+userKey;
    }
}
