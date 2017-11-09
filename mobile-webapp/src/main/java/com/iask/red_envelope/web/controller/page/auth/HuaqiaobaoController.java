package com.iask.red_envelope.web.controller.page.auth;

import com.hongbao.api.model.cache.UserInfo;
import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.service.user.UserCacheService;
import com.iask.red_envelope.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Summer on 2016/11/11.
 */
@Controller
@RequestMapping(value = "/p/a/huaqiaobao", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class HuaqiaobaoController {

    @Autowired
    private UserCacheService userCacheService;

    /**
     * 中转 页面
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request) {

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        if(userId == null) {
            // 判断是否是ios
            Boolean isIos = CommonMethod.isAppleBrower(request);
            request.setAttribute("isIos", isIos);

            return "page/mine/login";
        }else {

            UserInfo info = userCacheService.getUser(userId);

            request.setAttribute("type", 0);
            request.setAttribute("uid", info.getUserKey());
            return "page/huaqiaobao/index";

        }

    }

}
