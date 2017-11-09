package com.iask.red_envelope.web.controller.page;

import com.iask.red_envelope.consts.ParamConsts;
import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.consts.ValueConsts;
import com.iask.red_envelope.model.ReExchangeDetail;
import com.iask.red_envelope.model.ReScoreDetail;
import com.iask.red_envelope.model.dto.WxNotShareSign;
import com.iask.red_envelope.model.vo.ExchangeDetailVo;
import com.iask.red_envelope.service.CoinMoneyService;
import com.iask.red_envelope.service.ExchangeRecordsService;
import com.iask.red_envelope.service.WxNotShareService;
import com.iask.red_envelope.util.JsonUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 16/10/10.
 * 兑换记录
 */
@RequestMapping(value = "/p/exchange", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class ExchangeRecordsController {

    @Autowired
    private ExchangeRecordsService exchangeRecordsService ;
    @Autowired
    private WxNotShareService wxNotShareService ;
    @Autowired
    private CoinMoneyService coinMoneyService;

    /**
     * 默认是第一页
     * @param request
     * @return
     */
    @RequestMapping(value = "/exchangeList")
    public String goExchangeListPage(HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        List<ReExchangeDetail> details = exchangeRecordsService.getExchangeList(request,1);

        Integer score = coinMoneyService.getMyCoin(request);

        request.setAttribute("scores",details);
        request.setAttribute("score",score);
        request.setAttribute("pageNum",1);

        Integer total = exchangeRecordsService.getExchangeTotalNum(request);

        Integer totalPage = total % ValueConsts.pageSize > 0 ? total / ValueConsts.pageSize+1 :total / ValueConsts.pageSize;

        request.setAttribute("totalPage",totalPage);

        WxNotShareSign share = wxNotShareService.getSign(request);

        request.setAttribute("share",share);

        return "page/exchange/exchange_list";
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

        List<ReExchangeDetail> details = exchangeRecordsService.getExchangeList(request,pageNum);

        Integer total = exchangeRecordsService.getExchangeTotalNum(request);

        Integer totalPage = total % ValueConsts.pageSize > 0 ? total / ValueConsts.pageSize+1 :total / ValueConsts.pageSize;

        Map<String,Object> map = new HashMap<>(4);

        map.put("scores",details);
        map.put("pageNum",pageNum);
        map.put("totalPage",totalPage);

        return JsonUtil.buildSuccessData(map);
    }

    /**
     * 兑换记录点击查看详情
     * @param request
     * @param id        兑换记录id(表re_coin_item)
     * @return
     */
    @RequestMapping(value = "/detail/{id}")
    public String exchangeDetail(HttpServletRequest request,
                                 @PathVariable("id") Long id){


        ExchangeDetailVo vo = exchangeRecordsService.getDetailById(id);

        request.setAttribute("vo",vo);

        return "page/exchange/exchange_detail";
    }



}
