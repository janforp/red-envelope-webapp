package com.iask.red_envelope.web.controller.page.auth;

import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.model.vo.SignDateScoreVo;
import com.iask.red_envelope.model.vo.sign.SignPageVo;
import com.iask.red_envelope.service.sign.SignService;
import com.iask.red_envelope.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jan on 16/8/15.
 * 签到 按钮
 *
 * 需要经过 loginFilter
 */
@Controller
@RequestMapping(value = "/p/a/sign", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class SignController {

    @Autowired
    private SignService signService;

    /**
     * 跳转到签到页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request) {

        Boolean isIos = CommonMethod.isAppleBrower(request);
        request.setAttribute("isIos",isIos);

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        boolean isSign = signService.isSignToday(userId);
        request.setAttribute("isSign", isSign);

        SignPageVo vo = signService.getSingVo(userId);
        request.setAttribute("vo",vo);

        List<SignDateScoreVo> scores = signService.getSignV4Data(userId);
        for (int i = 0; i < scores.size(); i++) {
            request.setAttribute("signVo"+i, scores.get(i));
        }

        return "page/sign/sign-index";

    }

    /**
     * 签到
     * @param request
     * @return
     */
    @RequestMapping(value = "/sign")
    @ResponseBody
    public String sign(HttpServletRequest request) {

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        return signService.sign(userId);

    }

}
