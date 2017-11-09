package com.iask.red_envelope.service;

import com.iask.red_envelope.consts.ValueConsts;
import com.iask.red_envelope.dao.ReBannerDAO;
import com.iask.red_envelope.dao.ReMissionDAO;
import com.iask.red_envelope.dao.ReMissionSortDAO;
import com.iask.red_envelope.model.ReBanner;
import com.iask.red_envelope.model.ReMission;
import com.iask.red_envelope.model.ReMissionSort;
import com.iask.red_envelope.model.vo.MissionDetailVo;
import com.iask.red_envelope.model.vo.MissionListVo;
import com.iask.red_envelope.util.el.ElBase;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wujie5 on 16/8/9.
 */
@Service
public class AppMissionService {

    @Autowired
    private ReBannerDAO reBannerDAO;
    @Autowired
    private ReMissionSortDAO reMissionSortDAO;
    @Autowired
    private ReMissionDAO reMissionDAO;

    /**
     * 获取 banner 列表
     * @param request
     * @return
     */
    public List<ReBanner> getBannerList(HttpServletRequest request){

        return reBannerDAO.getAllBanner();
    }

    /**
     * 获取 任务分类 列表
     * @param request
     * @return
     */
    public List<ReMissionSort> getSortList(HttpServletRequest request){
        return reMissionSortDAO.getAllSort();
    }

    /**
     * 首页 默认显示的是 热门任务的 列表
     * @param request
     * @return
     */
    public List<MissionListVo> getHotMissionList(HttpServletRequest request) {

        List<ReMission> missions = reMissionDAO.getHotMission();

        List<MissionListVo> vos = new ArrayList<>(missions.size());

        for (ReMission mission : missions) {
            MissionListVo vo = turnReMissionToMissionListVo(mission);
            vos.add(vo);
        }
        return vos;
    }

    /**
     * 获取任务分类 列表
     * @param sortId
     * @return
     */
    public List<MissionListVo> getMissionListBySortId (Integer sortId,Integer pageTo) {


        RowBounds bounds = new RowBounds((pageTo - 1)*ValueConsts.pageSize,ValueConsts.pageSize);

        List<ReMission> missions = reMissionDAO.getMissionBySortId(sortId,bounds);

        List<MissionListVo> vos = new ArrayList<>(missions.size());

        for (ReMission mission : missions) {
            MissionListVo vo = turnReMissionToMissionListVo(mission);
            vos.add(vo);
        }
        return vos;
    }

    /**
     * 分页用 总纪录数
     * @param sortId
     * @return
     */
    public Integer getMissionListBySortIdNum (Integer sortId){

        return reMissionDAO.getMissionListBySortIdNum(sortId);
    }


    /**
     * 实体类转换
     * @param mission
     * @return
     */
    public MissionListVo turnReMissionToMissionListVo (ReMission mission){

        MissionListVo vo = new MissionListVo();

        vo.setMissionId(mission.getMissionId());
        vo.setMissionName(mission.getMissionName());
        vo.setMissionImg(mission.getMissionImg());
        vo.setParticipantsNum(mission.getParticipantsNum());
        vo.setEndTime(mission.getEndTime());
        vo.setMissionReward(mission.getMissionReward());

        Integer endTime = mission.getEndTime();
        if (endTime != null) {
            String showEndTime = ElBase.fmtDay((long)endTime*1000L);

            vo.setShowEndTime(showEndTime);
        }
        return vo;
    }

    /**
     *  详情
     * @param missionId
     * @return
     */
    public MissionDetailVo getMissionById(Integer missionId) {

        ReMission mission = reMissionDAO.selectByPrimaryKey(missionId);

        return turnRemissionToMissionDetailVo (mission);
    }

    /**
     * 参与人数 加 1
     * @param missionId
     */
    public void addParticipantsNum (Integer missionId) {

        ReMission mission = reMissionDAO.selectByPrimaryKey(missionId);

        Integer participantsNum = mission.getParticipantsNum();
        if (participantsNum == null) {
            participantsNum = 0;
        }
        mission.setParticipantsNum(participantsNum + 1);

        reMissionDAO.updateByPrimaryKeySelective(mission);
    }


    /**
     * 实体类
     * @param mission
     * @return
     */
    public MissionDetailVo turnRemissionToMissionDetailVo(ReMission mission) {

        MissionDetailVo vo = new MissionDetailVo();


        vo.setMissionId(mission.getMissionId());
        String missionName = mission.getMissionName();
        if (StringUtils.isEmpty(missionName)) {
            missionName = "";
        }
        vo.setMissionName(missionName);
        String merchantName = mission.getMerchantName();
        if (StringUtils.isEmpty(merchantName)) {
            merchantName = "";
        }
        vo.setMerchantName(merchantName);
        String missionImg = mission.getMissionImg();
        if (StringUtils.isEmpty(missionImg)) {
            missionImg = "";
        }
        vo.setMissionImg(missionImg);
        String missionReward = mission.getMissionReward();
        if (StringUtils.isEmpty(missionReward)) {
            missionReward = "";
        }
        vo.setMissionReward(missionReward);
        Integer gainRewardNum = mission.getGainRewardNum();
        if (StringUtils.isEmpty(gainRewardNum)) {
            gainRewardNum = null;
        }
        vo.setGainRewardNum(gainRewardNum);
        String missionAdImg = mission.getMissionAdImg();
        if (StringUtils.isEmpty(missionAdImg)) {
            missionAdImg = "";
        }
        vo.setMissionAdImg(missionAdImg);
        String missionUrl = mission.getMissionUrl();
        if (StringUtils.isEmpty(missionUrl)) {
            missionUrl = "";
        }
        vo.setMissionUrl(missionUrl);
        Integer participantsNum = mission.getParticipantsNum();
        if (StringUtils.isEmpty(participantsNum)){
            participantsNum = null;
        }
        vo.setParticipantsNum(participantsNum);
        Integer startTime = mission.getStartTime();
        if (startTime != null){
            String showStartTime = ElBase.fmtDay((long)startTime*1000L);
            vo.setShowStartTime(showStartTime);
        }
        Integer endTime = mission.getEndTime();
        if (endTime != null){
            String showEndTime = ElBase.fmtDay((long)endTime*1000L);
            vo.setShowEndTime(showEndTime);
        }
        String missionExtraReward = mission.getMissionExtraReward();
        if (StringUtils.isEmpty(missionExtraReward)){
            missionExtraReward = "";
        }
        vo.setMissionExtraReward(missionExtraReward);
        String missionStep = mission.getMissionStep();
        if (StringUtils.isEmpty(missionStep)) {
            missionStep = "";
        }
        vo.setMissionStep(missionStep);
        String missionRule = mission.getMissionRule();
        if (StringUtils.isEmpty(missionRule)) {
            missionRule = "";
        }
        vo.setMissionRule(missionRule);
        String merchantDetail = mission.getMerchantDetail();
        if (StringUtils.isEmpty(merchantDetail)){
            merchantDetail = "";
        }
        vo.setMerchantDetail(merchantDetail);
        Integer missionStatus = mission.getMissionStatus();
        if (missionStatus == 0){
            vo.setShowMissionStatus("已结束");
        }
        if (missionStatus == 1) {
            vo.setShowMissionStatus("进行中");
        }
        vo.setMissionStatus(missionStatus);


        return vo;
    }





}
