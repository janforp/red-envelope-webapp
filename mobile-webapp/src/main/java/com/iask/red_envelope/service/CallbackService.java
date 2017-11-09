package com.iask.red_envelope.service;

import com.iask.red_envelope.consts.AttributeConsts;
import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.service.app_share.AppShareService;
import com.iask.red_envelope.service.share.ShareService;
import com.wujie.common.sdk.support.exception.ApiException;
import com.wujie.common.sdk.weixin.oauth2.WxOauthClient;
import com.wujie.common.sdk.weixin.oauth2.domain.AccessTokenDomain;
import com.wujie.common.sdk.weixin.oauth2.domain.UserInfoDomain;
import com.wujie.common.sdk.weixin.oauth2.request.AccessTokenRequest;
import com.wujie.common.sdk.weixin.oauth2.request.GetUserInfoRequest;
import com.wujie.common.sdk.weixin.oauth2.response.AccessTokenResponse;
import com.wujie.common.sdk.weixin.oauth2.response.GetUserInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Summer on 16/7/12.
 */
@Service
public class CallbackService {

    @Autowired
    private CoinMallService coinMallService;
    @Autowired
    private BindWeChatService bindWeChatService;
    @Autowired
    private ShareService shareService;
    @Autowired
    private AppShareService appShareService;

    /**
     * 回调相同部分
     * @param request
     * @param code
     * @param state
     * @return
     */
    public UserInfoDomain callbackSame(HttpServletRequest request, String code , String state,WxOauthClient wxOauthClient) {

        RequestContext requestContext = new RequestContext(request);

        AccessTokenRequest accessTokenRequest = new AccessTokenRequest();
        accessTokenRequest.setCode(code);
        AccessTokenResponse accessTokenResponse = null;
        try {
            accessTokenResponse = wxOauthClient.execute(accessTokenRequest);
        } catch (ApiException e) {
            request.setAttribute(AttributeConsts.ERROR_MSG, requestContext.getMessage("error.login"));
        }

        // snsapi_userinfo 方式直接获取用户数据
        AccessTokenDomain accessTokenDomain = accessTokenResponse.getToken();

        GetUserInfoRequest getUserInfoRequest = new GetUserInfoRequest();
        getUserInfoRequest.setOpenid(accessTokenDomain.getOpenid());
        getUserInfoRequest.setAccessToken(accessTokenDomain.getAccessToken());

        GetUserInfoResponse getUserInfoResponse = null;
        try {
            getUserInfoResponse = wxOauthClient.execute(getUserInfoRequest);
        } catch (ApiException e) {
            request.setAttribute(AttributeConsts.ERROR_MSG, requestContext.getMessage("error.login"));
        }

        return getUserInfoResponse.getUserInfo();
    }


    /**
     * 绑定微信回调
     * @param request
     * @param code
     * @param state
     * @param wxOauthClient
     * @return
     */
    public String bindWxCallBack(HttpServletRequest request,String code,String state,WxOauthClient wxOauthClient) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        RequestContext requestContext = new RequestContext(request) ;
        if (code == null) {
            // 用户拒绝了授权
            request.setAttribute(AttributeConsts.ERROR_MSG, requestContext.getMessage("error.authorization.rejected"));
            return RequestConsts.RESULT_ERROR_500_PAGE;
        }
        if (state == null) {
            request.setAttribute(AttributeConsts.ERROR_MSG, requestContext.getMessage("error.param"));
            return RequestConsts.RESULT_ERROR_500_PAGE;
        }

        UserInfoDomain userInfoDomain = callbackSame(request,code,state,wxOauthClient);

        if (request.getAttribute(AttributeConsts.ERROR_MSG) != null) {

            return RequestConsts.RESULT_ERROR_500_PAGE;
        }

        return bindWeChatService.doBindWx(request,userInfoDomain);

    }


    /**
     * 微信回调 金币商城
     * @param request
     * @param codeId
     * @param code
     * @param state
     * @param wxOauthClient
     * @return
     */
    public String coinWxCallBack(HttpServletRequest request, HttpServletResponse response, Integer codeId, String code, String state, WxOauthClient wxOauthClient) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        RequestContext requestContext = new RequestContext(request) ;
        if (code == null) {
            // 用户拒绝了授权
            request.setAttribute(AttributeConsts.ERROR_MSG, requestContext.getMessage("error.authorization.rejected"));
            return RequestConsts.RESULT_ERROR_500_PAGE;
        }
        if (state == null) {
            request.setAttribute(AttributeConsts.ERROR_MSG, requestContext.getMessage("error.param"));
            return RequestConsts.RESULT_ERROR_500_PAGE;
        }

        AccessTokenRequest accessTokenRequest = new AccessTokenRequest();
        accessTokenRequest.setCode(code);
        AccessTokenResponse accessTokenResponse = null;
        try {
            accessTokenResponse = wxOauthClient.execute(accessTokenRequest);
        } catch (ApiException e) {
            request.setAttribute(AttributeConsts.ERROR_MSG, requestContext.getMessage("error.login"));
            return RequestConsts.RESULT_ERROR_500_PAGE;
        }

        // snsapi_userinfo 方式直接获取用户数据
        AccessTokenDomain accessTokenDomain = accessTokenResponse.getToken();

        GetUserInfoRequest getUserInfoRequest = new GetUserInfoRequest();
        getUserInfoRequest.setOpenid(accessTokenDomain.getOpenid());
        getUserInfoRequest.setAccessToken(accessTokenDomain.getAccessToken());

        GetUserInfoResponse getUserInfoResponse = null;
        try {
            getUserInfoResponse = wxOauthClient.execute(getUserInfoRequest);
        } catch (ApiException e) {
            request.setAttribute(AttributeConsts.ERROR_MSG, requestContext.getMessage("error.login"));
            return RequestConsts.RESULT_ERROR_500_PAGE;
        }

        UserInfoDomain userInfoDomain = getUserInfoResponse.getUserInfo();

        return coinMallService.wxCoin(request, response, codeId, userInfoDomain);

    }


    /**
     * 分享任务回调
     * @param request
     * @param response
     * @param missionId
     * @param code
     * @param state
     * @param wxOauthClient
     * @return
     */
    public String shareCallback(HttpServletRequest request, HttpServletResponse response, Long missionId, String code, String state,String ip, WxOauthClient wxOauthClient) throws IOException, NoSuchAlgorithmException {


        RequestContext requestContext = new RequestContext(request) ;
        if (code == null) {
            // 用户拒绝了授权
            request.setAttribute(AttributeConsts.ERROR_MSG, requestContext.getMessage("error.authorization.rejected"));
            return RequestConsts.RESULT_ERROR_500_PAGE;
        }
        if (state == null) {
            request.setAttribute(AttributeConsts.ERROR_MSG, requestContext.getMessage("error.param"));
            return RequestConsts.RESULT_ERROR_500_PAGE;
        }

        AccessTokenRequest accessTokenRequest = new AccessTokenRequest();
        accessTokenRequest.setCode(code);
        AccessTokenResponse accessTokenResponse = null;
        try {
            accessTokenResponse = wxOauthClient.execute(accessTokenRequest);
        } catch (ApiException e) {

            return "redirect:/c/p/share/sharePage/"+missionId;
        }

        String openId = accessTokenResponse.getToken().getOpenid();

        return shareService.goSharePage(request,response, missionId, openId, ip, code, state);

    }

    /**
     * 分享任务
     * @param request
     * @param missionId         app分享任务ID
     * @param code              微信code
     * @param state             微信status
     * @param userKey           用户唯一标识符
     * @param wxOauthClient     微信回调对象
     * @return
     */
    public String appShareClickCallback(HttpServletRequest request,Long missionId, String code, String state,String userKey, WxOauthClient wxOauthClient){


        RequestContext requestContext = new RequestContext(request) ;
        if (code == null) {
            // 用户拒绝了授权
            request.setAttribute(AttributeConsts.ERROR_MSG, requestContext.getMessage("error.authorization.rejected"));
            return RequestConsts.RESULT_ERROR_500_PAGE;
        }
        if (state == null) {
            request.setAttribute(AttributeConsts.ERROR_MSG, requestContext.getMessage("error.param"));
            return RequestConsts.RESULT_ERROR_500_PAGE;
        }

        AccessTokenRequest accessTokenRequest = new AccessTokenRequest();
        accessTokenRequest.setCode(code);
        AccessTokenResponse accessTokenResponse = null;
        try {
            accessTokenResponse = wxOauthClient.execute(accessTokenRequest);
        } catch (ApiException e) {
            request.setAttribute(AttributeConsts.ERROR_MSG, requestContext.getMessage("error.login"));
            return RequestConsts.RESULT_ERROR_500_PAGE;
        }

        String openId = accessTokenResponse.getToken().getOpenid();

        return appShareService.goAppSharePage(request,missionId, openId, userKey);
    }


}
