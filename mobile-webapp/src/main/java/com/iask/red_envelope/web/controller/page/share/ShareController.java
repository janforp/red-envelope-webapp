package com.iask.red_envelope.web.controller.page.share;

import com.iask.red_envelope.config.Config;
import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.consts.WeixinConsts;
import com.iask.red_envelope.service.share.ShareService;
import com.iask.red_envelope.util.CommonMethod;
import com.iask.red_envelope.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * Created by Jan on 16/10/26.
 * 朋友圈分享任务
 */
@RequestMapping(value = "/p/share", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class ShareController {

    @Autowired
    private ShareService shareService ;

    /**
     * 跳转到领取红包页面之前,需要微信静默登录
     * @param request
     * @param missionId        分享任务id
     * @return
     */
    @RequestMapping(value = "/sharePage/{missionId}")
    public String gotoSharePage(HttpServletRequest request,
                                @PathVariable("missionId")Long missionId){

        Boolean isWeixinBrowser = CommonMethod.isWeixinBrower(request);

        if (! isWeixinBrowser) {

            return "/error/404";
        }

        //先判断此任务是否存在
        Boolean isExist = shareService.isMissionExist(missionId);

        if (!isExist){

            return "/error/404";
        }

        String IP = RequestUtil.getClientIp(request);

        String url;
        String scope = WeixinConsts.scope_snsapi_base;
        String state = UUID.randomUUID().toString().replace("-","");
        try {
            String redirectUrl = new StringBuilder(128)
                    .append(Config.getBaseUrl())
                    .append(RequestConsts.SHARE_MISSION_CALLBACK_PATH)
                    .append(IP)
                    .append("/"+missionId).toString();

            url = WeixinConsts.WEIXIN_GET_CODE_URL_IN_WEIXIN_BROWSER
                    .replace("APPID",Config.getWeixinLoginCfgInWeixinBrowser().getAppId())
                    .replace("REDIRECT_URI", URLEncoder.encode(redirectUrl,"utf-8"))
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
     * 用户分享到朋友圈成功回调方法:领取红包
     * @param request
     * @param missionId
     * @param userId
     * @return
     */
    @RequestMapping("/getMoney/{missionId}/{userId}")
    @ResponseBody
    public String getRed(HttpServletRequest request,
                         @PathVariable("missionId")Long missionId,
                         @PathVariable("userId")Long userId) throws IOException {

        return shareService.getRedMoney(request,missionId,userId);
    }
}
