package com.iask.red_envelope.web.controller.page.auth;

import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.consts.ValueConsts;
import com.iask.red_envelope.model.ReWithdrawDetail;
import com.iask.red_envelope.model.vo.WithdrawRecordVo;
import com.iask.red_envelope.service.WithdrawService;
import com.iask.red_envelope.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Summer on 2016/9/23.
 */
@RequestMapping(value = "/p/a/withdraw", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class WithdrawController {

    @Autowired
    private WithdrawService withdrawService ;


    @RequestMapping(value = "/list")
    public String list(HttpServletRequest request) throws Exception {

        Integer withdrawRecordNum = withdrawService.getWithdrawListNumByUserId(request);
        List<WithdrawRecordVo> details = withdrawService.getWithdrawList(request,1);

        Integer totalPage = 0;
        Integer pageNum = 0;
        if (withdrawRecordNum > 0) {
            totalPage = withdrawRecordNum % ValueConsts.pageSize > 0 ? withdrawRecordNum / ValueConsts.pageSize +1 : withdrawRecordNum / ValueConsts.pageSize ;
            pageNum = 1;
        }
        if (details == null) {
            withdrawRecordNum = 0;
        }

        request.setAttribute("pageNum",pageNum);
        request.setAttribute("totalPage",totalPage);
        request.setAttribute("num",withdrawRecordNum);
        request.setAttribute("details",details);
        request.setAttribute("total",withdrawRecordNum);

        //逻辑:取出此用户的提现记录
        return "/page/mine/withdraw_list";
    }

    /**
     * 翻页
     * @param request
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/turnWithdraw")
    @ResponseBody
    public String turnWithdrawList(HttpServletRequest request,
                                   @RequestParam(value = "pageNum")Integer pageNum) throws Exception {
        Integer withdrawRecordNum = withdrawService.getWithdrawListNumByUserId(request);

        List<WithdrawRecordVo> details = withdrawService.getWithdrawList(request,pageNum);

        Map<String,Object> map = new HashMap<>(3);

        map.put("total",withdrawRecordNum);
        map.put("details",details);
        map.put("pageNum",pageNum);

        return JsonUtil.buildSuccessData(map);

    }


}
