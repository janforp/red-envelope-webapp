package com.iask.red_envelope.service.app_share;

import com.iask.red_envelope.config.Config;
import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.dao.*;
import com.iask.red_envelope.enums.MissionSubtype;
import com.iask.red_envelope.enums.MissionType;
import com.iask.red_envelope.model.*;
import com.iask.red_envelope.model.vo.app_click_share_mission.ClickShareDetailVo;
import com.iask.red_envelope.service.TicketCacheService;
import com.iask.red_envelope.util.StringUtil;
import com.iask.red_envelope.util.el.ElBase;
import com.wujie.common.sdk.weixin.official_account.DefaultWeixinClient;
import com.wujie.common.sdk.weixin.official_account.request.shorturl.ShortUrlRequest;
import com.wujie.common.sdk.weixin.official_account.response.shorturl.ShortUrlResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by Jan on 16/11/14.
 * app上的分享点击任务
 */
@Service
public class AppShareService {

    @Autowired
    private ReShareMissionDAO reShareMissionDAO;
    @Autowired
    private ReShareMissionDetailDAO reShareMissionDetailDAO;
    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;
    @Autowired
    private TicketCacheService ticketCacheService;

    /**
     * 判断此任务是否存在
     * @param missionId
     * @return
     */
    public Boolean isMissionExist(Long missionId){

        ReShareMission mission = reShareMissionDAO.selectByPrimaryKey(missionId);

        if (mission != null){
            return true;
        }
        return false;
    }

    /**
     * 判断此分享点击任务是否结束
     * @param mission
     * @return
     */
    public boolean isEnd(ReShareMission mission){

        if (mission == null || mission.getIsEnd() == 0 || mission.getLeftClickTimes() <=0){
            return true;
        }
        return false;
    }

    /**
     * app分享任务回调记录数据之后,用户静默登录之后跳转的网址
     * @param request
     * @param missionId
     * @param openId
     * @param userKey
     * @return
     */
    public String goAppSharePage(HttpServletRequest request, Long missionId, String openId, String userKey){

        ReShareMission mission = reShareMissionDAO.selectByPrimaryKeyAndLock(missionId);
        ReUser user = reUserDAO.selectByUserKeyAndLock(userKey);

        if (isEnd(mission)){//若改任务结束了,则不执行后面的给钱楼机,直接回调到url
            return "redirect:"+mission.getCallbackUrl();
        }else {//任务进行中的
            //1.查询此openid有没有点击过此missionId
            ReShareMissionDetail detail = reShareMissionDetailDAO.selectByPrimaryKey(missionId,openId);
            if (detail != null) {//此人的好友已经点击过了
                return "redirect:"+mission.getCallbackUrl();
            }else {//此人的好友第一次点击,可以算一次，该任务的剩余点击次数-1
                detail = new ReShareMissionDetail();
                detail.setMissionId(missionId);
                detail.setUserId(user.getUserId());
                detail.setOpenId(openId);
                detail.setClickTime(ElBase.fmtTime(System.currentTimeMillis()));
                //把此人的好友记录下来
                reShareMissionDetailDAO.insertSelective(detail);

                BigDecimal money = mission.getMoney();
                user.setUserMoney(user.getUserMoney().add(money));
                //给此用户加钱
                reUserDAO.updateByPrimaryKeySelective(user);

                //钱的详情
                ReAccountDetail accountDetail = new ReAccountDetail();

                accountDetail.setUserId(user.getUserId());
                accountDetail.setAppId(user.getAppId());
                accountDetail.setAccountMoney(mission.getMoney());
                accountDetail.setDetailType(1);
                accountDetail.setMissionType(MissionType.share_mission.val);
                accountDetail.setMissionSubtype(MissionSubtype.other.val);
                accountDetail.setDetailContent("分享任务["+mission.getMissionTitle()+"]被好友阅读");
                accountDetail.setDetailTime(ElBase.fmtTime(System.currentTimeMillis()));

                reAccountDetailDAO.insertSelective(accountDetail);

                //该任务的总次数-1
                mission.setLeftClickTimes(mission.getLeftClickTimes()-1);
                reShareMissionDAO.updateByPrimaryKeySelective(mission);

                return "redirect:"+mission.getCallbackUrl();
            }
        }
    }


    /**
     * app列表上点击进入分享点击任务详情页面
     * 此时应该生成该用户的短链接
     * IP+PORT/c/p/appShare/click/missionId/userKey
     * 这个地址就是他的好友点击的链接
     * @param request
     * @param missionId
     * @return
     */
    public ClickShareDetailVo getDetail(HttpServletRequest request, Long missionId) throws IOException {
        ClickShareDetailVo vo = null;
        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        if (!StringUtil.isEmpty(userId)){
            ReUser user = reUserDAO.selectByUserIdAndStatus(userId);
            ReShareMission mission = reShareMissionDAO.selectByPrimaryKey(missionId);
            if (mission != null){
                vo = new ClickShareDetailVo();

                vo.setMissionId(missionId);
                vo.setUserKey(user.getUserKey());
                vo.setMoney(mission.getMoney());
                vo.setTotalMoney(mission.getMoney().multiply(new BigDecimal(mission.getTotalClickTimes())));
                vo.setMissionTitle(mission.getMissionTitle());
                vo.setMissionIcon(mission.getMissionIcon());
                vo.setCallbackUrl(mission.getCallbackUrl());
                vo.setMissionDesc(mission.getMissionDesc());
                vo.setMissionText(mission.getMissionText());
                vo.setMissionImg(mission.getMissionImg());
                vo.setExampleImg(mission.getExampleImg());
                int totalClickTimes = reShareMissionDetailDAO.selectClickTimesByUserIdAndMissionId(userId,missionId);
                vo.setLeftClickTimes(mission.getLeftClickTimes());
                vo.setPartInNum(reShareMissionDetailDAO.selectPartInNum(missionId));

                String longUrl = Config.getBaseUrl()+"/c/p/appShare/click/"+missionId+"/"+user.getUserKey();
                String shortUrl = getShortUrl(longUrl);
                //此链接应该生成短链接
                vo.setLongUrl(longUrl);
                vo.setShortUrl(shortUrl);

                vo.setTotalClickTimes(totalClickTimes);
            }
        }
        return vo;
    }

    /**
     * 获取短链接
     * @param longUrl
     * @return
     */
    public String getShortUrl(String longUrl){

        String appId = Config.getWeixinLoginCfgInWeixinBrowser().getAppId();
        String appSecret = Config.getWeixinLoginCfgInWeixinBrowser().getAppSecret();

        String token = ticketCacheService.getAccessToken();

        DefaultWeixinClient client = new DefaultWeixinClient(appId, appSecret);
        client.setAccessToken(token);

        ShortUrlRequest request = new ShortUrlRequest();
        request.setLong_url(longUrl);
        ShortUrlResponse response = client.execute(request);

        return response.getShort_url();
    }
}

