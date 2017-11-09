package com.iask.red_envelope.web.controller.page;

import com.iask.red_envelope.config.Config;
import com.iask.red_envelope.consts.JsonCodeConsts;
import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.consts.WeixinConsts;
import com.iask.red_envelope.model.param.BindWxSubmitVo;
import com.iask.red_envelope.service.BindWeChatService;
import com.iask.red_envelope.util.CommonMethod;
import com.iask.red_envelope.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * Created by Jan on 16/8/26.
 * 绑定微信
 */
@RequestMapping(value = "/p/bind", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class BindWeChatController {

    @Autowired
    private BindWeChatService bindWeChatService;


    /**
     * 绑定微信说明页
     * @param request
     * @return
     */
    @RequestMapping(value = "/bindDetail")
    public String bindDetail(HttpServletRequest request) {

        Boolean isIos = CommonMethod.isAppleBrower(request) ;
        request.setAttribute("isIos", isIos);

        return "page/bind_wx/bind_detail";

    }


    /**
     * 绑定微信
     * @param request
     * @return
     */
    @RequestMapping(value = "/bindWx")
    public String bindWx(HttpServletRequest request) {

        boolean isWxBrowser = CommonMethod.isWeixinBrower(request);

        String url = null ;

        if (! isWxBrowser) {

            return JsonUtil.buildError(JsonCodeConsts.error_normal, "请在微信中打开");
        }

        String scope = WeixinConsts.scope_snsapi_userinfo;

        String state = UUID.randomUUID().toString().replace("-","");

        try {
            String redirectUrl = new StringBuilder(64)
                                        .append(Config.getBaseUrl())
                                        .append(RequestConsts.BIND_WX_CALLBACK_PATH).toString();

            url = WeixinConsts.WEIXIN_GET_CODE_URL_IN_WEIXIN_BROWSER
                    .replace("APPID",Config.getWeixinLoginCfgInWeixinBrowser().getAppId())
                    .replace("REDIRECT_URI",URLEncoder.encode(redirectUrl,"utf-8"))
                    .replace("SCOPE",scope)
                    .replace("STATE",state);

        }catch (RuntimeException e) {
            throw e;
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        return "redirect:"+url;
    }

    /**
     * 绑定微信 点击提交时
     *
     * 带过来的各参数
     *
     * 存入绑定表中
     * @param request
     * @return
     */
    @RequestMapping(value = "/bindSubmit")
    @ResponseBody
    public String bindSubmit(HttpServletRequest request, BindWxSubmitVo vo) {

        String phone = vo.getPhone() ;

        vo.setPhone(phone);

        phone = CommonMethod.validateAndFixedTelephone(phone) ;

        if (phone == null) {

            return JsonUtil.buildError(JsonCodeConsts.error_normal,"手机号码不合法");
        }

        return bindWeChatService.doSubmitBind(request,vo);

    }

    /**
     * 绑定成功跳转页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/bindSuccess")
    public String bindSuccessPage(HttpServletRequest request,String msg){

        request.setAttribute("msg",msg);

        return "page/bind_wx/bind_success";
    }
}
