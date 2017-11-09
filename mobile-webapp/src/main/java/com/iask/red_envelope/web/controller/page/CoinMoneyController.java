package com.iask.red_envelope.web.controller.page;

import com.iask.red_envelope.consts.ParamConsts;
import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.consts.ValueConsts;
import com.iask.red_envelope.model.ReAccountDetail;
import com.iask.red_envelope.model.ReScoreDetail;
import com.iask.red_envelope.model.dto.WxNotShareSign;
import com.iask.red_envelope.service.CoinMoneyService;
import com.iask.red_envelope.service.WxNotShareService;
import com.iask.red_envelope.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 16/9/12.
 * 金币及余额
 */
@RequestMapping(value = "/p/coinMoney", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class CoinMoneyController {

    @Autowired
    private CoinMoneyService coinMoneyService;
    @Autowired
    private WxNotShareService wxNotShareService ;

    /**
     * 默认是第一页
     * @param request
     * @return
     */
    @RequestMapping(value = "/coinList")
    public String goScoreListPage(HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        List<ReScoreDetail> details = coinMoneyService.getScoreList(request,1);

        Integer score = coinMoneyService.getMyCoin(request);

        request.setAttribute("scores",details);
        request.setAttribute("score",score);
        request.setAttribute("pageNum",1);

        Integer total = coinMoneyService.getScoreTotalNum(request);

        Integer totalPage = total % ValueConsts.pageSize > 0 ? total / ValueConsts.pageSize+1 :total / ValueConsts.pageSize;

        request.setAttribute("totalPage",totalPage);

        WxNotShareSign share = wxNotShareService.getSign(request);

        request.setAttribute("share",share);

        return "coin_list";
    }


    /**
     * 上下滑动屏幕
     * @param request
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public String turnPage(HttpServletRequest request,
                           @RequestParam(value = ParamConsts.pageNum, defaultValue = "1") Integer pageNum) {

        List<ReScoreDetail> details = coinMoneyService.getScoreList(request,pageNum);

        Integer total = coinMoneyService.getScoreTotalNum(request);

        Integer totalPage = total % ValueConsts.pageSize > 0 ? total / ValueConsts.pageSize+1 :total / ValueConsts.pageSize;

        Map<String,Object> map = new HashMap<>(4);

        map.put("scores",details);
        map.put("pageNum",pageNum);
        map.put("totalPage",totalPage);

        return JsonUtil.buildSuccessData(map);
    }



    /**
     * 默认是第一页
     * @param request
     * @return
     */
    @RequestMapping(value = "/moneyList")
    public String goMoneyListPage(HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        List<ReAccountDetail> details = coinMoneyService.getMoneyList(request,1);

        BigDecimal score = coinMoneyService.getMyMoney(request);

        request.setAttribute("scores",details);
        request.setAttribute("score",score);
        request.setAttribute("pageNum",1);

        Integer total = coinMoneyService.getMoneyListNum(request);

        Integer totalPage = total % ValueConsts.pageSize > 0 ? total / ValueConsts.pageSize+1 :total / ValueConsts.pageSize;

        request.setAttribute("totalPage",totalPage);

        WxNotShareSign share = wxNotShareService.getSign(request);

        request.setAttribute("share",share);

        return "money_list";
    }

    /**
     * 上下滑动屏幕
     * @param request
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/listMoney")
    @ResponseBody
    public String turnMoneyPage(HttpServletRequest request,
                           @RequestParam(value = ParamConsts.pageNum, defaultValue = "1") Integer pageNum) {

        List<ReAccountDetail> details = coinMoneyService.getMoneyList(request,pageNum);

        Integer total = coinMoneyService.getMoneyListNum(request);

        Integer totalPage = total % ValueConsts.pageSize > 0 ? total / ValueConsts.pageSize+1 :total / ValueConsts.pageSize;

        Map<String,Object> map = new HashMap<>(4);

        map.put("scores",details);
        map.put("pageNum",pageNum);
        map.put("totalPage",totalPage);

        return JsonUtil.buildSuccessData(map);
    }


}
