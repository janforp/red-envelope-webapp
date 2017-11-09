package com.iask.red_envelope.web.controller.page;

import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.util.CommonMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Summer on 2016/12/19.
 */
@RequestMapping(value = "/p/zk", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class MakerController {

    @RequestMapping(value = "/rule")
    public String rule(HttpServletRequest request) {

        Boolean isIos = CommonMethod.isAppleBrower(request);
        Boolean isApp = CommonMethod.isHongBaoAPPRequest(request);

        request.setAttribute("isApp", isApp);
        request.setAttribute("isIos", isIos);

        return "page/code-mission/rule";
    }

    @RequestMapping(value = "/zk")
    public String zk(HttpServletRequest request) {

        Boolean isApp = CommonMethod.isHongBaoAPPRequest(request);
        Boolean isIos = CommonMethod.isAppleBrower(request);

        request.setAttribute("isApp", isApp);
        request.setAttribute("isIos", isIos);

        return "page/mission/zk";
    }
}
