package com.iask.red_envelope.web.controller.page;

import com.iask.red_envelope.consts.ParamConsts;
import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.consts.ValueConsts;
import com.iask.red_envelope.model.vo.InvitationListVo;
import com.iask.red_envelope.service.CommissionService;
import com.iask.red_envelope.service.InvitationService;
import com.iask.red_envelope.util.CommonMethod;
import com.iask.red_envelope.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 16/9/21.
 * 邀请好友
 */
@RequestMapping(value = "/p/invitation", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class InvitationController {

    @Autowired
    private InvitationService invitationService ;
    @Autowired
    private CommissionService commissionService;

    /**
     * 邀请有礼页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/invitationCommissionMenu")
    public String gotoInvitationMenuPage(HttpServletRequest request) {

        BigDecimal commission = commissionService.getTotalCommissionByUserId(request);
        String invitationCode = invitationService.getInvitationCode(request);
        boolean isIos = CommonMethod.isAppleBrower(request);

        request.setAttribute("invitationCode" ,invitationCode);
        request.setAttribute("commission" , commission);

        if(isIos) {
            return "page/commission/commission_invitation_ios";
        }

        return "page/commission/commission_invitation_andriod";

    }

    /**
     * ios邀请步骤页面
     * @return
     */
    @RequestMapping(value = "/step")
    public String step(){
        return "page/commission/ios_step";
    }

    /**
     * 邀请攻略页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/strategy")
    public String goInvitationStrategyPage(HttpServletRequest request){
        return "page/commission/invitation_strategy";
    }

    /**
     * 邀请记录,
     * 默认第一页
     */
    @RequestMapping(value = "/invitationList")
    public String goInvitationDetailList(HttpServletRequest request){

        List<InvitationListVo> vos = invitationService.getInvitationList(request,1);

        Integer total = invitationService.getInvitationNum(request);

        Integer totalPage = total % ValueConsts.pageSize > 0 ? total / ValueConsts.pageSize + 1 : total / ValueConsts.pageSize;

        Integer pageNum = 1 ;

        if (total == 0) {

            pageNum = 0 ;
            totalPage = 0 ;
        }

        request.setAttribute("details" , vos);
        request.setAttribute("totalPage" , totalPage);
        request.setAttribute("pageNum" , pageNum);
        request.setAttribute("total" , total);

        boolean isIos = CommonMethod.isAppleBrower(request);
        if(isIos) {
            return "page/commission/invitation_list_ios";
        }

        return "page/commission/invitation_list_andriod";
    }

    /**
     * 上下滑动
     * 加载新的记录
     * @param request
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/turnInvitation")
    @ResponseBody
    public String turnInvitationListPage(HttpServletRequest request ,
                                         @RequestParam(value = ParamConsts.pageNum, defaultValue = "1") Integer pageNum) {

        List<InvitationListVo> vos = invitationService.getInvitationList(request,pageNum);

        Map<String,Object> map = new HashMap<>(4);

        map.put("details",vos);
        map.put("pageNum",pageNum);

        return JsonUtil.buildSuccessData(map);

    }

    /**
     * 全民红包 下载页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/qrCode")
    public String gotoInvitationDownloadPage(HttpServletRequest request) {
        boolean isIos = CommonMethod.isAppleBrower(request);
        request.setAttribute("ios",isIos);
        return "page/commission/appDownload";
    }


}
