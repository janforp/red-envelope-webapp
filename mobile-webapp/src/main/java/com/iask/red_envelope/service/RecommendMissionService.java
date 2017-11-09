package com.iask.red_envelope.service;

import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.dao.ReRecommendMissionDAO;
import com.iask.red_envelope.dao.ReRecommendMissionStepDAO;
import com.iask.red_envelope.dao.ReRecommendTaskDAO;
import com.iask.red_envelope.model.ReCodeExchangeDetail;
import com.iask.red_envelope.model.ReRecommendMission;
import com.iask.red_envelope.model.ReRecommendMissionStep;
import com.iask.red_envelope.model.ReRecommendTask;
import com.iask.red_envelope.model.vo.mission.ExchangeCodeMissionDetail;
import com.iask.red_envelope.model.vo.mission.RegisterMissionVo;
import com.iask.red_envelope.model.vo.mission.VerifyMissionDetail;
import com.iask.red_envelope.model.vo.mission.RecommendStep;
import com.iask.red_envelope.service.mission.ExchangeCodeService;
import com.iask.red_envelope.util.JsonUtil;
import com.iask.red_envelope.util.StringUtil;
import com.iask.red_envelope.util.el.ElBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Summer on 2016/10/31.
 */
@Service
public class RecommendMissionService {

    @Autowired
    private ExchangeCodeService exchangeCodeService;
    @Autowired
    private ReRecommendTaskDAO reRecommendTaskDAO;
    @Autowired
    private ReRecommendMissionDAO reRecommendMissionDAO;
    @Autowired
    private ReRecommendMissionStepDAO reRecommendMissionStepDAO;

    /**
     * 获取任务的类型:判断是什么类型的任务:任务分类,0:需审核任务, 1:兑换码红包
     * @param missionId
     * @return
     */
    public Integer getMissionType(Long missionId){
        ReRecommendMission mission = reRecommendMissionDAO.selectByPrimaryKey(missionId);
        Integer missionType = null;
        if (mission != null) {
            missionType = mission.getMissionType();
        }
        return missionType;
    }


    /**
     * 任务超时,释放任务
     *
     * @param task
     */
    public void releaseTask(ReRecommendTask task) {

        long now = System.currentTimeMillis();

        // 重置状态
        ReRecommendTask info = new ReRecommendTask();
        info.setTaskId(task.getTaskId());
        info.setTaskStatus(4);
        info.setUpdateTime(now);
        reRecommendTaskDAO.updateByPrimaryKeySelective(info);

        // 查询任务
        long id = task.getMissionId();
        ReRecommendMission mission = reRecommendMissionDAO.selectLockByMissionId(id);
        int leftNum = mission.getLeftNum() + 1;

        // 更新任务
        ReRecommendMission reRecommendMission = new ReRecommendMission();
        reRecommendMission.setMissionId(id);
        reRecommendMission.setLeftNum(leftNum);
        reRecommendMissionDAO.updateByPrimaryKeySelective(reRecommendMission);

    }


    /**
     * 需要审核任务详情
     *
     * @param missionId
     * @param userId
     * @return
     */
    public VerifyMissionDetail getVerifyMissionDetail(HttpServletRequest request, Long missionId, Integer userId) {

        VerifyMissionDetail detail = new VerifyMissionDetail();
        ReRecommendMission mission = reRecommendMissionDAO.selectByPrimaryKey(missionId);
        if(mission != null) {
            detail.setMissionId(missionId);
            detail.setMissionType(mission.getMissionType());
            BigDecimal max = mission.getMaxMoney();
            BigDecimal min = mission.getMinMoney();
            if(max.compareTo(min) == 0) { // 金额固定
                detail.setMoneyStatus(0);
            }else { // 金额不定
                detail.setMoneyStatus(1);
            }
            detail.setMissionId(mission.getMissionId());
            detail.setMissionIcon(mission.getMissionIcon());
            detail.setMissionTitle(mission.getMissionTitle());
            detail.setEndTime(mission.getEndTime());
            detail.setMinMoney(mission.getMinMoney());
            detail.setMissionType(mission.getMissionType());
            List<String> list = new ArrayList<>();
            String[] labels = mission.getMissionLabel().split(",");
            for (String label : labels) {
                list.add(label);
            }
            list.add("还剩"+mission.getLeftNum()+"份");
            detail.setMissionLabel(list);
            detail.setMissionDesc(mission.getMissionDesc());
            detail.setIsLimitTime(mission.getIsLimitTime());
            detail.setIsVerify(mission.getIsVerify());
            // 审核要求
            detail.setVerifyRequire(mission.getVerifyRequire());
            // 备注要求
            detail.setVerifyText(mission.getVerifyText());
            // 截图要求
            detail.setVerifyImg(mission.getVerifyImg());
            // 截图案例
            List<String> imgList = new ArrayList<>();
            String missionImgs = mission.getMissionImgs();
            if(!StringUtil.isEmpty(missionImgs)) {
                String[] imgs = missionImgs.split(";");
                for (String img : imgs) {
                    imgList.add(img);
                }
            }
            detail.setMissionImgs(imgList);
            detail.setImgNum(imgList.size());
            //若没有登录,则默认为未领取状态,用户点击领取任务按钮的时候再调用登录,之后刷新页面
            if(userId != null) { // 查询是否已领
                ReRecommendTask task = reRecommendTaskDAO.selectByUserIdAndMissionId(userId, missionId);
                if(task != null) {
                    int status = task.getTaskStatus();
                    detail.setTaskStatus(status);
                    if(status == 0 || status == 1) {//若是进行中,或 审核中,则需要显示剩余时间
                        long limitTime = task.getReleaseTime() - System.currentTimeMillis();
                        if (limitTime <= 0){
                            limitTime = 0;
                        }
                        detail.setLeftTime(limitTime);
                    }
                    detail.setTaskId(task.getTaskId());
                }
            }
        }
        return detail;
    }


    /**
     * 兑换码任务详情
     *
     * @param missionId
     * @param userId
     * @return
     */
    public ExchangeCodeMissionDetail getExchangeMissionDetail(Long missionId, Integer userId){

        ExchangeCodeMissionDetail detail = new ExchangeCodeMissionDetail();

        ReRecommendMission mission = reRecommendMissionDAO.selectByPrimaryKey(missionId);
        if(mission != null) {
            detail.setMissionId(missionId);
            detail.setMissionType(mission.getMissionType());
            BigDecimal max = mission.getMaxMoney();
            BigDecimal min = mission.getMinMoney();
            if(max.compareTo(min) == 0) { // 金额固定
                detail.setMoneyStatus(0);
            }else { // 金额不定
                detail.setMoneyStatus(1);
            }
            detail.setMissionId(mission.getMissionId());
            detail.setMissionIcon(mission.getMissionIcon());
            detail.setMissionTitle(mission.getMissionTitle());
            detail.setEndTime(mission.getEndTime());
            detail.setMinMoney(mission.getMinMoney());
            detail.setMissionType(mission.getMissionType());
            List<String> list = new ArrayList<>();
            String[] labels = mission.getMissionLabel().split(",");
            for (String label : labels) {
                list.add(label);
            }
            list.add("还剩"+mission.getLeftNum()+"份");
            detail.setMissionLabel(list);
            detail.setExchangeCode(mission.getExchangeCode());
            detail.setMissionDesc(mission.getMissionDesc());

            if (userId != null){ // 生成此人userId及此任务missionId唯一的兑换码
                ReCodeExchangeDetail codeExchangeDetail = exchangeCodeService.createCodeByMissionIdUserId(missionId, userId);
                detail.setExchangeCode(codeExchangeDetail.getExchangeCode());
            }

        }
        return detail;
    }

    /**
     * 注册类型的高额任务
     * @param missionId     任务ID
     * @param userId        用户ID
     * @return
     */
    public RegisterMissionVo getRegisterMissionDetail(Long missionId,Integer userId){

        RegisterMissionVo detail = new RegisterMissionVo();
        ReRecommendMission mission = reRecommendMissionDAO.selectByPrimaryKey(missionId);
        if(mission != null) {
            detail.setMissionId(missionId);
            detail.setMissionType(mission.getMissionType());
            BigDecimal max = mission.getMaxMoney();
            BigDecimal min = mission.getMinMoney();
            if(max.compareTo(min) == 0) { // 金额固定
                detail.setMoneyStatus(0);
            }else { // 金额不定
                detail.setMoneyStatus(1);
            }
            detail.setMissionId(mission.getMissionId());
            detail.setMissionIcon(mission.getMissionIcon());
            detail.setMissionTitle(mission.getMissionTitle());
            detail.setEndTime(mission.getEndTime());
            detail.setMinMoney(mission.getMinMoney());
            detail.setMissionType(mission.getMissionType());
            List<String> list = new ArrayList<>();
            String[] labels = mission.getMissionLabel().split(",");
            for (String label : labels) {
                list.add(label);
            }
            list.add("还剩"+mission.getLeftNum()+"份");
            detail.setMissionLabel(list);
            detail.setMissionDesc(mission.getMissionDesc());
            detail.setIsLimitTime(mission.getIsLimitTime());
            detail.setIsVerify(mission.getIsVerify());
            // 截图案例
            List<String> imgList = new ArrayList<>();
            String missionImgs = mission.getMissionImgs();
            if(!StringUtil.isEmpty(missionImgs)) {
                String[] imgs = missionImgs.split(";");
                for (String img : imgs) {
                    imgList.add(img);
                }
            }
            detail.setMissionImgs(imgList);
            detail.setImgNum(imgList.size());
        }
        return detail;
    }


    /**
     * 查询任务步骤
     *
     * @param missionId
     * @return
     */
    public List<RecommendStep> getSteps(Long missionId, boolean isIos){

        List<RecommendStep> stepsVo = new ArrayList<>();

        int stepStatus = 0;
        if(!isIos) {
            stepStatus = 1;
        }
        List<ReRecommendMissionStep> stepList = reRecommendMissionStepDAO.selectByMissionId(missionId, stepStatus);
        for (ReRecommendMissionStep step : stepList) {

            RecommendStep step1 = new RecommendStep();
            step1.setMissionId(step.getMissionId());
            step1.setStepNum(step.getStepNum());
            step1.setStepContent(step.getStepContent());

            String imgs = step.getStepImgs();
            if (StringUtil.isEmpty(imgs)){
                step1.setStepImgs(null);
            }else{
                List<String> stepImgUrls = Arrays.asList(imgs.split(";"));
                step1.setStepImgs(stepImgUrls);
            }
            String btn = step.getStepBtn();
            if (!StringUtil.isEmpty(btn)){
                btn = btn.replace("<span class=\"close deleteBtn\">×</span>","");
            }
            step1.setStepBtn(btn);

            stepsVo.add(step1);
        }

        return stepsVo;
    }


    /**
     * 抢任务
     * @param userId
     * @param missionId
     * @return
     */
    public String getMission(Integer userId, Long missionId) {


        boolean isOver = isOver(missionId);
        if (isOver){
            return JsonUtil.buildError(9, "任务已结束");
        }

        // 判断是否能抢
        ReRecommendTask reRecommendTask = reRecommendTaskDAO.selectByUserIdAndMissionIdAndStatus(userId, missionId);
        if(reRecommendTask == null) { // 能抢

            ReRecommendMission reRecommendMission = reRecommendMissionDAO.selectLockByMissionId(missionId);

            // 任务剩余份数
            int leftNum = reRecommendMission.getLeftNum();
            if(leftNum <= 0) {
                return JsonUtil.buildError(9, "任务已经被抢完");
            }

            // 更新任务
            reRecommendMission.setLeftNum(leftNum - 1);
            reRecommendMissionDAO.updateByPrimaryKeySelective(reRecommendMission);

            long time = System.currentTimeMillis();
            int isLimitTime = reRecommendMission.getIsLimitTime();


            // 记录任务
            ReRecommendTask task = new ReRecommendTask();
            task.setUserId(userId);
            task.setMissionId(missionId);
            task.setTaskStatus(0);
            task.setUpdateTime(time);
            task.setCreateTime(time);
            if(isLimitTime == 0) { // 限时
                long limitTime = reRecommendMission.getLimitMinute() * 60 * 1000;
                task.setReleaseTime(time + limitTime);
            }
            reRecommendTaskDAO.insertSelective(task);

            return JsonUtil.buildSuccess("抢成功");

        }
        return JsonUtil.buildError(9, "您已经抢过该任务");
    }


    /**
     * 任务超时的
     * @param request
     * @param missionId
     * @return
     */
    public String invalid(HttpServletRequest request,Long missionId){

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        //1:该用户的该任务状态:4
        ReRecommendTask reRecommendTask = reRecommendTaskDAO.getOvertimeMissionByUserIdMissionId(userId, missionId);
        if (reRecommendTask != null){

            reRecommendTask.setTaskStatus(4);
            reRecommendTask.setUpdateTime(System.currentTimeMillis());
            reRecommendTaskDAO.updateByPrimaryKeySelective(reRecommendTask);
        }
        //2:任务的剩余数量+1
        ReRecommendMission mission = reRecommendMissionDAO.selectByPrimaryKey(missionId);
        if (mission != null){
            mission.setLeftNum(mission.getLeftNum()+1);
            reRecommendMissionDAO.updateByPrimaryKeySelective(mission);
        }
        return JsonUtil.buildSuccess("");

    }

    /**
     * 判断任务是否已过结束时间或剩余次数为0
     * @param missionId
     * @return
     */
    public boolean isOver(Long missionId){

        ReRecommendMission mission = reRecommendMissionDAO.selectByPrimaryKey(missionId);

        if (mission.getLeftNum() <= 0){
            return true;
        }
        String endTime = mission.getEndTime();
        Long end = ElBase.get13Timestamp(endTime+" 23:59:59");
        Long now = System.currentTimeMillis();
        if (now > end){
            return true;
        }
        return false;
    }
}