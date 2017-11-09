package com.iask.red_envelope.web.controller.page;

import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.dao.WxShareRedDetailDAO;
import com.iask.red_envelope.model.WxShareRedDetail;
import com.iask.red_envelope.service.*;
import com.iask.red_envelope.util.CommonMethod;
import jxl.Workbook;
import jxl.write.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.OutputStream;
import java.util.List;
import java.util.Vector;

/**
 * Created by Jan on 16/8/26.
 *
 * 测试
 */
@RequestMapping(value = "/p/test", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class TestController {

    @Autowired
    private WxNotShareService wxNotShareService;
    @Autowired
    private WxShareRedDetailDAO wxShareRedDetailDAO;

    //提交审核
    @RequestMapping("/submit")
    public String task(HttpServletRequest request){

        boolean isIos = CommonMethod.isAppleBrower(request);
        request.setAttribute("isIos", isIos);

        return "/test/test1";
    }

    /**
     * 显示任务详情
     * @return
     */
    @RequestMapping("/task")
    public String task(){

        return "/page/task/index";
    }

    @RequestMapping(value = "home")
    public String testHomeRotate(HttpServletRequest request){

        request.setAttribute("lotteryIndex",2);

        return "/test/rotate_home";
    }

    /**
     * 推荐任务
     * @param request
     * @return
     */
    @RequestMapping(value = "/recommend")
    public String testRecommendMission(HttpServletRequest request){
        return "/page/recommend_mission/recommend_mission";
    }
    /**
     * 推荐审核
     * @param request
     * @return
     */
    @RequestMapping(value = "/examine")
    public String testExamine(HttpServletRequest request){
        return "/page/recommend_mission/submit_examine";
    }


    @RequestMapping(value = "/share")
    public String testRed(HttpServletRequest request){

        return "/page/share/open_red";
    }

    @RequestMapping(value = "/share2")
    public String testN(HttpServletRequest request){

        request.setAttribute("firstTime",0);

        return "/test/wx_share";
    }

    @RequestMapping(value = "/img")
    public String hideImg(HttpServletRequest request){

        return "/test/hide-img";
    }
}


