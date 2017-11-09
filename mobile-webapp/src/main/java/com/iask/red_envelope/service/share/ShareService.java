package com.iask.red_envelope.service.share;

import com.iask.red_envelope.cache.ShareUserInfoCacheService;
import com.iask.red_envelope.config.Config;
import com.iask.red_envelope.consts.JsonCodeConsts;
import com.iask.red_envelope.dao.WxShareMissionDAO;
import com.iask.red_envelope.dao.WxShareRedDetailDAO;
import com.iask.red_envelope.dao.WxShareUserDAO;
import com.iask.red_envelope.dao.WxShareUserRelationDAO;
import com.iask.red_envelope.model.*;
import com.iask.red_envelope.model.cache.ShareUserInfo;
import com.iask.red_envelope.model.dto.WxNotShareSign;
import com.iask.red_envelope.model.ip.IpData;
import com.iask.red_envelope.model.vo.ShareVo;
import com.iask.red_envelope.service.TicketCacheService;
import com.iask.red_envelope.task.share.ReleaseRedTask;
import com.iask.red_envelope.util.IpUtil;
import com.iask.red_envelope.util.JsonUtil;
import com.iask.red_envelope.util.RandomUtil;
import com.iask.red_envelope.util.StringUtil;
import com.iask.red_envelope.util.el.ElBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Jan on 16/10/26.
 * 朋友圈分享任务
 */
@Service
public class ShareService {

    @Autowired
    private WxShareUserDAO wxShareUserDAO;
    @Autowired
    private WxShareMissionDAO wxShareMissionDAO;
    @Autowired
    private ShareUserInfoCacheService shareUserInfoCacheService;
    @Autowired
    private WxShareUserRelationDAO wxShareUserRelationDAO;
    @Autowired
    private WxShareRedDetailDAO wxShareRedDetailDAO;
    @Autowired
    private ReleaseRedTask releaseRedTask;
    @Autowired
    private TicketCacheService ticketCacheService;



    /**
     * 到领取红包页面
     * @param request
     * @param response
     * @param missionId        分享任务id
     * @param openId           用户openId
     * @return
     */
    public String goSharePage(HttpServletRequest request,
                              HttpServletResponse response,
                              Long missionId,
                              String openId,
                              String ip,
                              String code,
                              String state) throws IOException, NoSuchAlgorithmException {

        String appId = Config.getWeixinLoginCfgInWeixinBrowser().getAppId();
        Long now = System.currentTimeMillis()/1000;
        String timestamp = now.toString();

        String nonceStr = RandomUtil.getRandomString(16);

        WxNotShareSign signVo = new WxNotShareSign();

        signVo.setAppId(appId);
        signVo.setNonceStr(nonceStr);
        signVo.setTimestamp(timestamp);

        String url = "http://hb.lswuyou.cn/c/p/callback/share/"+ip+"/"+missionId+"?code="+code+"&state="+state;

        signVo.setUrl(url);

        String sign = ticketCacheService.createSign(signVo);

        signVo.setSignature(sign);

        request.setAttribute("signVo", signVo);



        //1.根据OPENID查用户是否存在
        WxShareUser shareUser = wxShareUserDAO.getUserByOpenId(openId);
        if (shareUser == null) { //新用户

            shareUser = new WxShareUser();
            shareUser.setOpenId(openId);
            shareUser.setUserIp(ip);
            shareUser.setUpdateTime(ElBase.fmtTime(System.currentTimeMillis()));
            shareUser.setCreateTime(ElBase.fmtTime(System.currentTimeMillis()));

            wxShareUserDAO.insertSelective(shareUser);

        }else{ //旧用户,则修改此人的IP,每次静默登录之后,进入领红包页面的时候更改该用户的IP

            shareUser.setUserIp(ip);
            shareUser.setUpdateTime(ElBase.fmtTime(System.currentTimeMillis()));

            wxShareUserDAO.updateByPrimaryKeySelective(shareUser);
        }

        WxShareMission mission = wxShareMissionDAO.selectByPrimaryKey(missionId);

        if (mission.getVerifyIp() == 1){ //需要验证

            //只有此任务需要验证IP的时候,才用缓存,若缓存有其他作用,则需要写到外面
            ShareUserInfo userInfo = new ShareUserInfo();

            userInfo.setIp(ip);
            userInfo.setUserId(shareUser.getUserId());
            IpData data = IpUtil.getRegionData(ip);
            if (data.getIsInvalidIP()==0){  //IP解析成功
                userInfo.setProvince(data.getRegion());
                userInfo.setCity(data.getCity());
            }
            shareUserInfoCacheService.saveShareInfo(shareUser.getUserId(),missionId,userInfo);
        }
        ShareVo vo = getShareByMissionId(missionId);

        //2.再看该用户领取该任务红包的情况,建立一条关系数据
        WxShareUserRelation relation = wxShareUserRelationDAO.selectByPrimaryKey(shareUser.getUserId(),missionId);

        if (StringUtil.isEmpty(relation)){//若为空,则表示该用户是第一次进入此页面

            relation = new WxShareUserRelation();
            relation.setUserId(shareUser.getUserId());
            relation.setMissionId(missionId);
            relation.setPrizeTimes(0);
            relation.setLotteryTimes(0);
            relation.setUpdateTime(ElBase.fmtTime(System.currentTimeMillis()));
            relation.setCreateTime(ElBase.fmtTime(System.currentTimeMillis()));

            wxShareUserRelationDAO.insert(relation);
        }else { //否则,说明该用户不是第一次进入该页面
            //因为第一次进入是直接可以抽奖的,若不是第一次进入,则不应该可以直接抽奖,所以需要带一个状态过去
            //若此用户已经抽过至少一次的时候,则进入该页面提示为:分享之后可以获得抽奖的机会
            int userLotteryTimes = relation.getLotteryTimes();
            if (userLotteryTimes>=1){   //则说明不是第一次进入
                request.setAttribute("firstTime",0);
            }
        }
        /**
         * 分享的链接
         */
        request.setAttribute("vo",vo);
        request.setAttribute("userId",shareUser.getUserId());
        request.setAttribute("missionId",missionId);
        request.setAttribute("openImg",mission.getOpenImg());
        request.setAttribute("failImg",mission.getFailImg());
        request.setAttribute("successImg",mission.getSuccessImg());

        return "/page/share/open_red";
    }

    /**
     * 根据任务ID查找数据
     * @param missionId
     * @return
     */
    public ShareVo getShareByMissionId(Long missionId){

        WxShareMission mission = wxShareMissionDAO.selectByPrimaryKey(missionId);

        ShareVo vo = new ShareVo();

        vo.setMerchantName(mission.getMerchantName());
        vo.setShareUrl(mission.getShareUrl());
        vo.setShareTitle(mission.getShareTitle());
        vo.setShareImg(mission.getShareImg());
        vo.setShareDesc(mission.getShareDesc());
        vo.setShareType(mission.getShareType());
        vo.setShareDataurl(mission.getShareDataurl());

        return vo;
    }

    /**
     * 用户分享到朋友圈成功回调方法:领取红包
     * @param request
     * @param missionId
     * @param userId
     * @return
     */
    public String  getRedMoney(HttpServletRequest request,Long missionId,Long userId) throws IOException {

        //1.先查询任务的规则,如:中奖次数,抽奖次数
        WxShareMission mission = wxShareMissionDAO.selectByPrimaryKey(missionId);
        //2.查看任务是否结束
        int isEnd = mission.getIsEnd();
        if (isEnd == 0){
            return JsonUtil.buildError(JsonCodeConsts.share_mission_is_end, "活动已经结束");
        }
        //每个用户允许抽奖次数
        Integer lotteryTimes = mission.getLotteryTimes();
        //允许中奖次数
        Integer prizeTimes = mission.getPrizeTimes();
        //是否需要IP验证
        Integer verifyIp = mission.getVerifyIp();
        //2.查看此用户是否在指定区域
        if (verifyIp == 1){ //需要验证

            //只有此任务需要验证IP的时候,才用缓存,若缓存有其他作用,则需要写到外面
            ShareUserInfo userInfo = shareUserInfoCacheService.getShareInfo(request,userId,missionId);

            String verifyCity = mission.getMissionCity();
            if (!StringUtil.isEmpty(verifyCity)){   //市不空,则以市为准

                String userCity = userInfo.getCity();
                if (StringUtil.isEmpty(userCity)|| !userCity.contains(verifyCity)){//若为空,则此人的IP无法解析{"code":1,"data":"invaild ip."}

                    return JsonUtil.buildError(JsonCodeConsts.error_normal,"您所在区域无法参加此活动");
                }

            }else{//若市为空,则比较省
                String verifyProvince = mission.getMissionProvince();
                String userProvince = userInfo.getProvince();
                if (StringUtil.isEmpty(userProvince) || !userProvince.contains(verifyProvince)){//若为空,则此人的IP无法解析{"code":1,"data":"invaild ip."}

                    return JsonUtil.buildError(JsonCodeConsts.error_normal,"您所在区域无法参加此活动");
                }
            }
        }
        //IP验证通过
        //2.再看该用户领取该任务红包的情况
        WxShareUserRelation relation = wxShareUserRelationDAO.selectByPrimaryKey(userId,missionId);
        //用户抽奖次数
        Integer userLotteryTimes = relation.getLotteryTimes();

        if (StringUtil.isEmpty(userLotteryTimes)){
            userLotteryTimes=0;
        }
        if (userLotteryTimes >= lotteryTimes){//用户抽奖次数用完了

            return JsonUtil.buildError(JsonCodeConsts.error_normal,"抽奖次数用完");
        }
        Integer userPrizeTimes = relation.getPrizeTimes();

        if (StringUtil.isEmpty(userPrizeTimes)){

            userPrizeTimes = 0;
        }
        if (userPrizeTimes >= prizeTimes){//用户中奖次数用完了

            return JsonUtil.buildError(JsonCodeConsts.error_normal,"您已经领取过此红包");
        }
        //用户还能抽奖,也能中奖
        //3.按设计好的中奖概率摇奖
        Integer rate = mission.getPrizeRate();
        Boolean isPrized = isPrized(rate);

        //更改他的抽奖次数
        relation.setLotteryTimes(userLotteryTimes+1);
        relation.setUpdateTime(ElBase.fmtTime(System.currentTimeMillis()));

        //但是若是第一次抽奖,则不应该中奖
        if (userLotteryTimes == 0){
            isPrized = false;
        }

        if (isPrized == false) {//没有中奖

            wxShareUserRelationDAO.updateByPrimaryKey(relation);

            return JsonUtil.buildError(JsonCodeConsts.error_normal,"没有中奖,再接再厉");
        }else { //中奖了

            //4.再判断此活动是否还有红包剩余,随机找能不能找到
            WxShareRedDetail redDetail = wxShareRedDetailDAO.randomGetRedAndLockByMissionId(missionId);

            if (StringUtil.isEmpty(redDetail)){ //说明红包已经发完
                //红包发完了,则用户本次抽奖不算,抽奖次数减回
                relation.setLotteryTimes(relation.getLotteryTimes() - 1);

                return JsonUtil.buildError(JsonCodeConsts.error_normal,"活动已经结束");
            }
            //5.红包有剩余,则给他发红包
            //则更改他的中奖次数
            relation.setPrizeTimes(userPrizeTimes+1);
            wxShareUserRelationDAO.updateByPrimaryKey(relation);

            //更改红包状态
            redDetail.setUserId(userId);
            redDetail.setRedStatus(1);
            redDetail.setFlag(0);//表示未真正发送
            redDetail.setReceiveTime(ElBase.fmtTime(System.currentTimeMillis()));
            wxShareRedDetailDAO.updateByPrimaryKey(redDetail);

            //重新启动线程给用户发红包:redDetail
            ReleaseRedVo redVo = new ReleaseRedVo();

            redVo.setRedId(redDetail.getRedId());
            redVo.setMoney(redDetail.getMoney());
            redVo.setUserId(userId);
            redVo.setMissionId(missionId);
            releaseRedTask.addMission(redVo);


            return JsonUtil.buildSuccess(""+redDetail.getMoney()/100.0);
        }
    }

    /**
     * 判断此任务是否存在
     * @param missionId
     * @return
     */
    public Boolean isMissionExist(Long missionId){

        WxShareMission mission = wxShareMissionDAO.selectByPrimaryKey(missionId);

        if (mission != null){
            return true;
        }

        return false;
    }

    /**
     * 摇奖
     * @param rate  中奖概率(0-100)
     * @return
     */
    public boolean isPrized(Integer rate){

        boolean isPrized = false;

        Double ratio = rate/100.0;

        double randomNumber = Math.random();

        if (randomNumber >= 0 && randomNumber <= ratio) { // 抽中

            isPrized = true ;

        }
        return isPrized;
    }
}
