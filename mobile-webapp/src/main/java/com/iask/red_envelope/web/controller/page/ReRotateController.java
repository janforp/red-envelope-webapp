package com.iask.red_envelope.web.controller.page;

import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.model.vo.RotateRandomVo;
import com.iask.red_envelope.service.RandomService;
import com.iask.red_envelope.service.ReRotateService;
import com.iask.red_envelope.util.CommonMethod;
import com.iask.red_envelope.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Summer on 16/9/8.
 */
@RequestMapping(value = "/p/rotate", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class ReRotateController {

    @Autowired
    private ReRotateService reRotateService;
    @Autowired
    private RandomService randomService ;

    /**
     * 大转盘页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/index")
    public String rotate(HttpServletRequest request) {

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        if(userId != null) { // 已登录

            int times = reRotateService.rotateTimes(userId);

            if(times >= 3) {
                request.setAttribute("times", 0);
            }else {
                request.setAttribute("times", 3 - times);
            }

        }else { // 未登录
            request.setAttribute("times", 3);
        }

        Boolean isIos = CommonMethod.isAppleBrower(request);
        request.setAttribute("isIos", isIos);

        List<RotateRandomVo> rotateRandomVos = randomService.getNRandomDetail(500);

        request.setAttribute("awards",rotateRandomVos);

        return "page/discover/rotate";

    }

    @RequestMapping(value = "/getNews")
    @ResponseBody
    public String getNews(HttpServletRequest request){

        List<RotateRandomVo> rotateRandomVos = randomService.getNRandomDetail(100);

        Map<String ,Object> map = new HashMap<>(1);
        map.put("awards",rotateRandomVos);

        return JsonUtil.buildSuccessData(map);
    }

}
