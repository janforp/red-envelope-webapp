package com.iask.red_envelope.web.controller.page.article;

import com.iask.red_envelope.config.Config;
import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.consts.WeixinConsts;
import com.iask.red_envelope.model.ReArticleMission;
import com.iask.red_envelope.service.article.ArticleService;
import com.iask.red_envelope.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * Created by Jan on 2016/12/8.
 * 转发文章
 */
@Controller
@RequestMapping(value = "/p/article", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 点击文章，进入文章详情页面
     * @param request
     * @param articleId     文章ID
     * @param userKey       分享用户的ID
     * @return
     */
    @RequestMapping(value = "/detail/{articleId}/{userKey}")
    public  String clickShareOutArticle(HttpServletRequest request,
                                        @PathVariable(value = "articleId")Long articleId,
                                        @PathVariable(value = "userKey")String userKey){

        Boolean isExist = articleService.isArticleExist(articleId);
        if (!isExist){
            return "/error/404";
        }

        ReArticleMission article = articleService.getMissionById(articleId);

        Boolean isWxBrowser = CommonMethod.isWeixinBrower(request);
        Boolean isApp = CommonMethod.isHongBaoAPPRequest(request);
        Integer articleType = article.getArticleType();

        request.setAttribute("isApp", isApp);

        if (isWxBrowser){//若是在微信浏览器，则是该用户分享之后的链接，该请求是朋友圈内点击的，需要静默登录，发放点击奖励

            String url;
            String scope = WeixinConsts.scope_snsapi_base;
            String state = UUID.randomUUID().toString().replace("-","");
            try {
                String redirectUrl = new StringBuilder(200)
                        .append(Config.getBaseUrl())
                        .append(RequestConsts.ARTICLE_SHARE_CALLBACK_PATH)
                        .append(+articleId)
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

        }else if (isApp){//若在APP，且没有领取过该文章的阅读奖励，则发放阅读奖励，否则不发

            BigDecimal money = articleService.getReadMoney(request,articleId);
            request.setAttribute("money",money);
            if (articleType == 0){//我们自己的文章

                request.setAttribute("article",article);
                return "/page/article/app_article";
            }else {//客户的链接
                return "redirect:"+article.getArticleUrl();
            }
        }else{//在普通浏览器,不论是自己的文章还是用户的链接都直接中专页

            request.setAttribute("article",article);
            return "/page/article/transit-page";
        }
    }

    /**
     * 点赞文章
     * @param request
     * @param articleId
     * @param openId
     * @return
     */
    @RequestMapping(value = "/praise")
    @ResponseBody
    public String praiseArticle(HttpServletRequest request,
                                @RequestParam(value = "articleId")Long articleId,
                                @RequestParam(value = "openId")String openId){

        return articleService.praiseArticle(articleId,openId);
    }
}