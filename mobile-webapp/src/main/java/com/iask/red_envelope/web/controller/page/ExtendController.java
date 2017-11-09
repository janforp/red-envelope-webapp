package com.iask.red_envelope.web.controller.page;

import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.model.dto.WxNotShareSign;
import com.iask.red_envelope.model.vo.ExtendDetailVo;
import com.iask.red_envelope.service.ExtendService;
import com.iask.red_envelope.service.WxNotShareService;
import com.iask.red_envelope.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Jan on 16/8/31.
 * 推广 re_customer_extend
 */
@RequestMapping(value = "/p/extend", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class ExtendController {

    @Autowired
    private ExtendService extendService;
    @Autowired
    private WxNotShareService wxNotShareService;



    /**
     * 推广 详情页
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/extendDetail/{id}")
    public String codeDetail(HttpServletRequest request,
                             @PathVariable(value = "id")Integer id) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        ExtendDetailVo red = extendService.getExtendDetail(id);

        request.setAttribute("red",red);

        WxNotShareSign share = wxNotShareService.getSign(request);

        Boolean isIos = CommonMethod.isAppleBrower(request) ;

        request.setAttribute("share",share);

        request.setAttribute("isIos",isIos);

        return "extend_detail";
    }
}
