package com.iask.red_envelope.service.article;

import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.dao.*;
import com.iask.red_envelope.enums.MissionSubtype;
import com.iask.red_envelope.enums.MissionType;
import com.iask.red_envelope.model.*;
import com.iask.red_envelope.util.CommonMethod;
import com.iask.red_envelope.util.JsonUtil;
import com.iask.red_envelope.util.el.ElBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 2016/12/8.
 * 转发文章
 */
@Service
public class ArticleService {

    @Autowired
    private ReArticleReadDAO reArticleReadDAO;
    @Autowired
    private ReArticleMissionDAO reArticleMissionDAO;
    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;
    @Autowired
    private ReArticleClickDetailDAO reArticleClickDetailDAO;
    @Autowired
    private ReArticleAdDAO reArticleAdDAO;


    /**
     * 判断此任务是否存在
     * @param articleId
     * @return
     */
    public Boolean isArticleExist(Long articleId){

        ReArticleMission mission = reArticleMissionDAO.selectByPrimaryKey(articleId);

        if (mission != null){
            return true;
        }
        return false;
    }

    /**
     * 发放APP阅读奖励
     * @param request
     * @return
     */
    public BigDecimal getReadMoney(HttpServletRequest request,Long articleId){

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        long now = System.currentTimeMillis();
        String nowTime = ElBase.fmtTime(now);

        if (userId != null){

            ReArticleRead read = reArticleReadDAO.selectByPrimaryKey(articleId,userId);
            if (read == null){

                ReArticleMission mission = reArticleMissionDAO.selectByPrimaryKey(articleId);
                BigDecimal readMoney = mission.getAppReadMoney();
                if (readMoney.compareTo(new BigDecimal("0"))>0){

                    ReUser user = reUserDAO.selectLockByUserId(userId);
                    if (user != null){
                        user.setUpdateTime(now);
                        user.setUserMoney(user.getUserMoney().add(readMoney));
                        reUserDAO.updateByPrimaryKeySelective(user);

                        read = new ReArticleRead();
                        read.setArticleId(articleId);
                        read.setUserId(userId);
                        read.setReadTime(nowTime);
                        reArticleReadDAO.insert(read);

                        ReAccountDetail detail = new ReAccountDetail();
                        detail.setUserId(userId);
                        detail.setAppId(user.getAppId());
                        detail.setAccountMoney(readMoney);
                        detail.setDetailType(1);
                        detail.setMissionType(MissionType.share_mission.val);
                        detail.setMissionSubtype(MissionSubtype.read.val);
                        detail.setDetailContent("阅读["+mission.getArticleTitle()+"]");
                        detail.setDetailTime(nowTime);
                        reAccountDetailDAO.insertSelective(detail);

                        return readMoney;
                    }
                }
            }
        }
        return new BigDecimal("0.00");
    }


    /**
     * 文章Id
     * @param articleId
     * @return
     */
    public ReArticleMission getMissionById(Long articleId){

        return reArticleMissionDAO.selectByPrimaryKey(articleId);
    }


    /**
     * 判断此分享点击任务是否结束
     * @param mission
     * @return
     */
    public boolean isEnd(ReArticleMission mission){

        if (mission == null){
            return true;
        }
        long now = System.currentTimeMillis();
        String endTime = mission.getEndMissionTime();
        long end = ElBase.get13Timestamp(endTime);

        if (mission.getIsEnd() == 0 || mission.getLeftClickTimes() <=0 || (now >= end)){

            return true;
        }
        return false;
    }


    /**
     * 转发文章到朋友圈，好友点击链接后静默登录之后回调
     * 在微朋友圈打开的时候就要考虑广告是否显示的问题了
     * @param request
     * @param articleId
     * @param openId
     * @param userKey
     * @return
     */
    public String goArticlePage(HttpServletRequest request, Long articleId, String openId, String userKey) {

        ReUser user = reUserDAO.selectByUserKeyAndLock(userKey);

        ReArticleMission article = reArticleMissionDAO.selectByPrimaryKeyAndLock(articleId);

        ReArticleClickDetail detail = reArticleClickDetailDAO.selectByPrimaryKey(articleId, openId);

        request.setAttribute("isPraised", false);
        request.setAttribute("isApp", false);

        //若改任务结束了,则不执行后面的给钱逻辑，直接打广告
        if (! isEnd(article) && detail == null){//任务进行中的且此人的好友第一次点击,可以算一次，该任务的剩余点击次数-1
                detail = new ReArticleClickDetail();
                detail.setArticleId(articleId);
                detail.setUserId(user.getUserId());
                detail.setOpenId(openId);
                detail.setClickTime(ElBase.fmtTime(System.currentTimeMillis()));
                //把此人的好友记录下来
                reArticleClickDetailDAO.insertSelective(detail);

                BigDecimal money = article.getWxClickMoney();
                user.setUserMoney(user.getUserMoney().add(money));
                //给此用户加钱
                reUserDAO.updateByPrimaryKeySelective(user);

                //钱的详情
                ReAccountDetail accountDetail = new ReAccountDetail();

                accountDetail.setUserId(user.getUserId());
                accountDetail.setAppId(user.getAppId());
                accountDetail.setAccountMoney(money);
                accountDetail.setDetailType(1);
                accountDetail.setMissionType(MissionType.share_mission.val);
                accountDetail.setMissionSubtype(MissionSubtype.click.val);
                accountDetail.setDetailContent("分享任务[" + article.getArticleTitle() + "]被好友阅读");
                accountDetail.setDetailTime(ElBase.fmtTime(System.currentTimeMillis()));

                reAccountDetailDAO.insertSelective(accountDetail);

                //该任务的总次数-1
                article.setLeftClickTimes(article.getLeftClickTimes() - 1);
                article.setReadTimes(article.getReadTimes() + 1);
                reArticleMissionDAO.updateByPrimaryKeySelective(article);
        }else if (detail != null){

            Integer isPraised = detail.getIsPraised();
            if (isPraised == 1){
                request.setAttribute("isPraised",true);
            }
        }

        Integer articleType = article.getArticleType();

        if (articleType == 0){//我们自己的文章

            Integer isDirectlyGoAd = article.getIsDirectlyGoAd();
            //若是在非APP，且直接到广告页面时，才直接到广告
            if (isDirectlyGoAd == 1){

                return "redirect:"+article.getArticleUrl();
            }

            request.setAttribute("article",article);
            List<ReArticleAd> ads = getShowAdsByArticleId(articleId);
            if (ads != null && ads.size()>0){
                request.setAttribute("ads",ads);
            }
            request.setAttribute("openId",openId);

            return "/page/article/wx_article";

        }else {//客户的链接

            return  "redirect:"+article.getArticleUrl();
        }

    }

    /**
     * 找出此文章对应的广告
     * 且是要显示的
     * @param articleId
     * @return
     */
    public List<ReArticleAd> getShowAdsByArticleId(Long articleId){

        List<ReArticleAd> allAd = reArticleAdDAO.getAdsByArticleId(articleId);

        List<ReArticleAd> showAd = new ArrayList<>();
        for (ReArticleAd ad : allAd){

            if (ad.getIsDisplay() == 1){

                showAd.add(ad);
            }
        }
        return showAd;
    }


    /**
     * 点赞
     * @param articleId
     * @param openId
     * @return
     */
    public String praiseArticle(Long articleId,String openId){

        ReArticleMission article = reArticleMissionDAO.selectByPrimaryKey(articleId);
        if (article != null){
            ReArticleClickDetail detail = reArticleClickDetailDAO.selectByPrimaryKey(articleId,openId);
            if (detail != null){

                Integer isPraised = detail.getIsPraised();
                if (isPraised == 0){

                    detail.setIsPraised(1);
                    detail.setPraiseTime(ElBase.fmtTime(System.currentTimeMillis()));
                    reArticleClickDetailDAO.updateByPrimaryKeySelective(detail);

                    article.setPraiseTimes(article.getPraiseTimes() +1);
                    article.setUpdateTime(ElBase.fmtTime(System.currentTimeMillis()));
                    reArticleMissionDAO.updateByPrimaryKeySelective(article);
                }
            }
        }
        return JsonUtil.buildSuccess("点赞成功");
    }

}
