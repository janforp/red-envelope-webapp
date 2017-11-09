package com.iask.red_envelope.web.controller.page;

import com.iask.red_envelope.config.Config;
import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.consts.WeixinConsts;
import com.iask.red_envelope.model.ReCoinItem;
import com.iask.red_envelope.model.dto.ReUserInfo;
import com.iask.red_envelope.model.vo.ItemVo;
import com.iask.red_envelope.service.CoinMallService;
import com.iask.red_envelope.service.sign.SignService;
import com.iask.red_envelope.service.user.UserService;
import com.iask.red_envelope.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

/**
 * Created by Jan on 16/8/19.
 *
 * 金币商城
 */
@RequestMapping(value = "/p/coin", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class CoinMallController {

    @Autowired
    private UserService userService;
    @Autowired
    private SignService signService;
    @Autowired
    private CoinMallService coinMallService;

    /**
     * 跳转到  金币商城 页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/coinMall/{codeId}")
    public String bindWx(HttpServletRequest request,
                         @PathVariable("codeId") Integer codeId) {

        boolean isWxBrowser = CommonMethod.isWeixinBrower(request);

        if (!isWxBrowser) { // 不在微信中

            // 是否登录
            Boolean isLogin = userService.isLogin(request);
            Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

            // 是否签到
            Boolean isSign = false;
            if (isLogin == true) {
                isSign = signService.isSignToday(userId);
            }

            //虚拟商品带过去
            List<ReCoinItem> items = coinMallService.getCoinItemList(1);
            request.setAttribute("items",items);
            //实体商品带过去
            List<ReCoinItem> realities = coinMallService.getCoinItemList(0);
            request.setAttribute("real",realities);

            Boolean isIos = CommonMethod.isAppleBrower(request);
            ReUserInfo user = userService.getUserInfoById(userId);

            request.setAttribute("isWx", false);
            request.setAttribute("isIos", isIos);
            request.setAttribute("isLogin", isLogin);
            request.setAttribute("isSign", isSign);
            request.setAttribute("user", user);
            request.setAttribute("codeId", codeId);

            return "coin_mall";

        }

        String url = null;
        String scope = WeixinConsts.scope_snsapi_userinfo;
        String state = UUID.randomUUID().toString().replace("-","");
        try {
            String redirectUrl = new StringBuilder(64)
                    .append(Config.getBaseUrl())
                    .append(RequestConsts.COIN_WX_CALLBACK_PATH)
                    .append(codeId).toString();

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
     * 虚拟/实物商品 详情
     * @return
     */
    @RequestMapping(value = "/item/{itemId}")
    public String gotoItemPage(HttpServletRequest request,
                               @PathVariable("itemId") Long itemId){

        boolean isLogin = userService.isLogin(request);
        ItemVo item =  coinMallService.getItemById(request,itemId);
        Boolean isWeiXin = CommonMethod.isWeixinBrower(request);
        Boolean isIos = CommonMethod.isAppleBrower(request);
        Boolean exchange = item.isFlag();

        request.setAttribute("isLogin", isLogin);
        request.setAttribute("item", item);
        request.setAttribute("isWx", isWeiXin);
        request.setAttribute("isIos", isIos);
        request.setAttribute("exchange",exchange);

        return "coin/item";
    }


    /**
     * 金币商城兑换
     * @param request
     * @param score 金币
     * @param goodsNum 所兑换的商品
     * @param goodsName 名字
     * @return
     */
    @RequestMapping(value = "/exchange")
    @ResponseBody
    public String exchange(HttpServletRequest request,
                           @RequestParam(value = "score") Integer score,
                           @RequestParam(value = "goodsNum")Long goodsNum,
                           @RequestParam(value = "goodsName")String goodsName) {

        return coinMallService.doExchange(request,score,goodsNum,goodsName);
    }



}
