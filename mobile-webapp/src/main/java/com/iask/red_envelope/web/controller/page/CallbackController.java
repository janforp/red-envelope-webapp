package com.iask.red_envelope.web.controller.page;

import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.service.CallbackService;
import com.wujie.common.sdk.weixin.oauth2.WxOauthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by craig on 16/2/10.
 */
@RequestMapping(value = "/p/callback", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class CallbackController {

    // 在微信浏览器中网页登录
    @Autowired
    private WxOauthClient wxOauthClientInWeixinBrowser;

    @Autowired
    private CallbackService callbackService;

    /**
     * 在微信浏览器中 绑定微信回调
     * @param request
     * @param code
     * @param state
     * @return
     */
    @RequestMapping(value = "/bindWx")
    public String bindWx(HttpServletRequest request,
                         @RequestParam(value = "code", required = false) String code,
                         @RequestParam(value = "state", required = false) String state) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        return callbackService.bindWxCallBack(request, code, state, wxOauthClientInWeixinBrowser);
    }

    /**
     * 在微信浏览器中 打开金币商城
     * @param request
     * @param code
     * @param state
     * @return
     */
    @RequestMapping(value = "/coin/{codeId}")
    public String bindWx(HttpServletRequest request,
                         HttpServletResponse response,
                         @PathVariable("codeId") Integer codeId,
                         @RequestParam(value = "code", required = false) String code,
                         @RequestParam(value = "state", required = false) String state) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        return callbackService.coinWxCallBack(request, response, codeId, code, state, wxOauthClientInWeixinBrowser);

    }

    /**
     * 链接到领取红包,分享任务页面
     * @param request
     * @param missionId        分享任务的id
     * @return
     */
    @RequestMapping(value = "/share/{ip}/{missionId}")
    public String sharePage(HttpServletRequest request,
                            HttpServletResponse response,
                            @PathVariable("missionId") Long missionId,
                            @PathVariable("ip") String ip,
                            @RequestParam(value = "code", required = false) String code,
                            @RequestParam(value = "state", required = false) String state) throws IOException, NoSuchAlgorithmException {

        return callbackService.shareCallback(request,response,missionId,code,state,ip,wxOauthClientInWeixinBrowser);
    }

    /**
     * 链接到领取红包,分享任务页面
     * @param request
     * @param missionId        分享任务的id
     * @return
     */
    @RequestMapping(value = "/appShare/{missionId}/{userKey}")
    public String appShareClickMission(HttpServletRequest request,
                            @PathVariable("missionId") Long missionId,
                            @PathVariable("userKey") String userKey,
                            @RequestParam(value = "code", required = false) String code,
                            @RequestParam(value = "state", required = false) String state) throws IOException, NoSuchAlgorithmException {

        return callbackService.appShareClickCallback(request,missionId,code,state,userKey,wxOauthClientInWeixinBrowser);
    }
}
