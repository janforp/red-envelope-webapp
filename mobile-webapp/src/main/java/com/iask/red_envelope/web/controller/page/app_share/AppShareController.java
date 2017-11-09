package com.iask.red_envelope.web.controller.page.app_share;

import com.iask.red_envelope.config.Config;
import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.consts.WeixinConsts;
import com.iask.red_envelope.service.app_share.AppShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * Created by Jan on 16/11/14.
 * app上的分享点击任务
 */
@RequestMapping(value = "/p/appShare", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class AppShareController {

    @Autowired
    private AppShareService appShareService;

    /**
     * 朋友圈点击的时候会调用此接口
     * @param request
     * @param missionId
     * @param userKey
     * @return
     */
    @RequestMapping(value = "/click/{missionId}/{userKey}")
    public String  shareClick(HttpServletRequest request,
                             @PathVariable("missionId")Long missionId,
                             @PathVariable("userKey")String userKey){
        //先判断此任务是否存在
        Boolean isExist = appShareService.isMissionExist(missionId);
        if (!isExist){
            return "/error/404";
        }
        String url;
        String scope = WeixinConsts.scope_snsapi_base;
        String state = UUID.randomUUID().toString().replace("-","");
        try {
            String redirectUrl = new StringBuilder(128)
                    .append(Config.getBaseUrl())
                    .append(RequestConsts.APP_SHARE_CLICK_MISSION_CALLBACK_PATH)
                    .append(+missionId)
                    .append("/"+userKey).toString();

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
}
