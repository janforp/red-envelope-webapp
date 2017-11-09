package com.iask.red_envelope.web.controller.page;

import com.iask.red_envelope.consts.RequestConsts;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wuqiang on 16-2-22.
 *
 * @author wuqiang
 */
@RequestMapping(value = "/p", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class IndexController {
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request) {
        return "red_rule";
    }
}
