package com.iask.red_envelope.web.controller.page.auth;

import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.service.ReRotateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Summer on 16/9/9.
 */
@Controller
@RequestMapping(value = "/p/a/rotate", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class DrawRotateController {

    @Autowired
    private ReRotateService reRotateService;

    @RequestMapping(value = "/draw")
    @ResponseBody
    public String draw(HttpServletRequest request) {

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        return reRotateService.doRotate(userId);

    }

}
