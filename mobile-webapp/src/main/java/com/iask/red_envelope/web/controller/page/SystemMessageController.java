package com.iask.red_envelope.web.controller.page;

import com.iask.red_envelope.consts.RequestConsts;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Summer on 16/9/21.
 */
@RequestMapping(value = "/p/sys", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.POST, RequestMethod.GET})
@Controller
public class SystemMessageController {

    @RequestMapping(value = "/message")
    public String message() {
        return "page/system/message";
    }


}
