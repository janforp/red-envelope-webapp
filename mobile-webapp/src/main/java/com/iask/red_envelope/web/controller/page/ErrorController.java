package com.iask.red_envelope.web.controller.page;

import com.iask.red_envelope.consts.RequestConsts;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wuqiang on 16-1-29.
 *
 * @author wuqiang
 */
@RequestMapping(value = "/p/error", produces = RequestConsts.CONTENT_TYPE_HTML,
        method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.TRACE})
@Controller
public class ErrorController {
    @RequestMapping(value = "/404")
    public String e404(HttpServletRequest request) {
        return RequestConsts.RESULT_ERROR_404_PAGE;
    }

    @RequestMapping(value = "/403")
    public String e403(HttpServletRequest request) {
        return RequestConsts.RESULT_ERROR_403_PAGE;
    }

    @RequestMapping(value = "/500")
    public String e500(HttpServletRequest request) {
        return RequestConsts.RESULT_ERROR_500_PAGE;
    }
}
