package com.iask.red_envelope.service;

import com.alibaba.fastjson.JSONArray;
import com.iask.red_envelope.consts.JsonCodeConsts;
import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.dao.*;
import com.iask.red_envelope.enums.MissionSubtype;
import com.iask.red_envelope.enums.MissionType;
import com.iask.red_envelope.model.*;
import com.iask.red_envelope.model.dto.AppInfo;
import com.iask.red_envelope.model.dto.ReTaskInfo;
import com.iask.red_envelope.model.dto.TaskDetailInfo;
import com.iask.red_envelope.model.dto.TaskInfo;
import com.iask.red_envelope.util.JsonUtil;
import com.iask.red_envelope.util.Result;
import com.iask.red_envelope.util.StringUtil;
import com.iask.red_envelope.util.el.ElBase;
import com.wujie.common.utils.FastJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Summer on 2016/10/17.
 */
@Service
public class ReTaskService {

    @Autowired
    private ReAppKeywordsDAO reAppKeywordsDAO;
    @Autowired
    private ReAppTaskDAO reAppTaskDAO;
    @Autowired
    private ReUserAndriodAppDAO reUserAndriodAppDAO;
    @Autowired
    private ReUserAndriodAppHistoryDAO reUserAndriodAppHistoryDAO;
    @Autowired
    private ReAppDAO reAppDAO;
    @Autowired
    private ReAppMarketDAO reAppMarketDAO;
    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;

    /**
     * 保存用户本地app信息
     *
     * @param userId
     * @param app
     */
    public String saveAppData(int userId, String app) {

        // 删除app实时表
        reUserAndriodAppDAO.deleteByUserId(userId);

        if(!StringUtil.isEmpty(app)) {

            List<AppInfo> appInfoList = JSONArray.parseArray(app, AppInfo.class);

            String now = ElBase.fmtTime(System.currentTimeMillis());

            List<ReUserAndriodApp> list = new ArrayList<>();
            List<ReUserAndriodAppHistory> historyList = new ArrayList<>();

            for (AppInfo info : appInfoList) {

                String appName = info.getAppName();
                String appPackage = info.getAppPackage();

                // 实时表
                ReUserAndriodApp reUserAndriodApp = new ReUserAndriodApp();
                reUserAndriodApp.setUserId(userId);
                reUserAndriodApp.setAppName(appName);
                reUserAndriodApp.setAppPackage(appPackage);
                reUserAndriodApp.setRecordTime(now);
                list.add(reUserAndriodApp);

                // 查询是否已经存在历史表
                ReUserAndriodAppHistory history = reUserAndriodAppHistoryDAO.selectByAppPackage(userId, appPackage);
                if(history == null) {
                    history = new ReUserAndriodAppHistory();
                    history.setUserId(userId);
                    history.setAppName(appName);
                    history.setAppPackage(appPackage);
                    history.setRecordTime(now);
                    historyList.add(history);
                }

            }

            // 插入app实时表
            reUserAndriodAppDAO.insertBatch(list);

            // 插入app历史表
            if(historyList.size() > 0) {
                reUserAndriodAppHistoryDAO.insertBatch(historyList);
            }

        }

        return JsonUtil.buildSuccess(null);

    }


    /**
     * 获取 限时任务 数据列表
     * @param request
     * @return
     */
    public List<ReTaskInfo> getTimedList(HttpServletRequest request) {

        String time = ElBase.fmtTime(System.currentTimeMillis());

        List<String> packageList = new ArrayList<>();

        List<ReTaskInfo> list = new ArrayList<>();

        // 用户id
        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        // 设备产商
        String manufacturer = (String) request.getAttribute(RequestConsts.ATTR_MANUFACTURER);
        // 设备系统
        String os = (String) request.getAttribute(RequestConsts.ATTR_OS);
        // 设备id
        String deviceId = (String) request.getAttribute(RequestConsts.ATTR_DEVICE_ID);

        // 查询是否有正在进行中的任务
        ReAppTask reAppTask = reAppTaskDAO.selectByUserIdAndDeviceId(userId, deviceId);
        if(reAppTask != null) { // 有进行中的
            TaskInfo taskInfo = reAppKeywordsDAO.selectByKeywordId(reAppTask.getKeywordId());
            ReTaskInfo info = new ReTaskInfo();
            info.setKeywordId(taskInfo.getKeywordId());
            info.setAppIcon(taskInfo.getAppIcon());
            info.setKeyword(taskInfo.getKeyword());
            info.setAppMarket(taskInfo.getAppMarket());
            info.setAppPackage(taskInfo.getAppPackage());
            info.setAppLabel(taskInfo.getTaskLabel());
            info.setLeftNum(taskInfo.getLeftNum());
            info.setDesc("0");
            info.setMarketPackage(taskInfo.getMarketPackage());
            info.setMarketUrl(taskInfo.getMarketUrl());
            list.add(info);

            packageList.add(taskInfo.getAppPackage());

        }
        // 查出所有的可以做的任务(1.已经发放且还有剩余,2.该用户手机上有的app排除,3.一个app只查出一条word)
        List<TaskInfo> taskList = reAppKeywordsDAO.selectAllByLeftAndTime(time, userId);
        for (TaskInfo taskInfo : taskList) {
            long appId = taskInfo.getAppId();
            //判断此用户,是否已经做过或在做此appId的任务,若有,则过滤掉
            ReAppTask task = reAppTaskDAO.selectByUserIdAndAppId(userId, appId);
            if(task == null) { // 未下过此app
                String appMarket = taskInfo.getAppMarket();

                String appPackage = taskInfo.getAppPackage();

                if(!packageList.contains(appPackage)) {

                    if(appMarket.contains("小米")) {
                        //若是小米市场的任务,则只能由小米手机做。若不是小米手机,则不显示小米市场任务
                        if("xiaomi".equals(os)) {
                            ReTaskInfo info = setReTaskInfo(taskInfo);
                            list.add(info);
                            packageList.add(appPackage);
                        }

                    }else if(appMarket.contains("魅族")){
                        //若是魅族市场的任务,则只能由魅族手机做。若不是魅族手机,则不显示魅族市场任务
                        if("meizu".equals(os)) {
                            ReTaskInfo info = setReTaskInfo(taskInfo);
                            list.add(info);
                            packageList.add(appPackage);
                        }

                    }else {
                        //其他市场的任务,所有安卓机器都可以做
                        ReTaskInfo info = setReTaskInfo(taskInfo);
                        list.add(info);
                        packageList.add(appPackage);
                    }

                }

            }

        }

        return list;

    }

    /**
     * 设置 ReTaskInfo
     * @param taskInfo
     * @return
     */
    private ReTaskInfo setReTaskInfo(TaskInfo taskInfo) {

        ReTaskInfo info = new ReTaskInfo();
        info.setKeywordId(taskInfo.getKeywordId());
        info.setAppIcon(taskInfo.getAppIcon());
        info.setAppPackage(taskInfo.getAppPackage());
        info.setKeyword(taskInfo.getKeyword());
        info.setAppMarket(taskInfo.getAppMarket());
        info.setAppLabel(taskInfo.getTaskLabel());
        info.setLeftNum(taskInfo.getLeftNum());
        info.setDesc(taskInfo.getMoney()+"");
        info.setMarketPackage(taskInfo.getMarketPackage());
        info.setMarketUrl(taskInfo.getMarketUrl());

        return info;

    }

    /**
     * 任务是否超时
     * @param request
     * @param keywordId
     * @return
     */
    public String isOvertime(HttpServletRequest request,Long keywordId){

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        ReAppTask ingTask = reAppTaskDAO.selectIngTaskByUserIdAndKeywordIdLock(userId,keywordId);

        if (ingTask.getTaskStatus() != 0){
            return JsonUtil.buildError(JsonCodeConsts.error_normal,"任务已经超时");
        }

        Long createTime = ingTask.getCreateTime();
        Long overtime = createTime + 60*60*1000;
        Long now = System.currentTimeMillis();
        if (now >= overtime){
            //判断为超时,则,任务状态设为'放弃'
            ingTask.setTaskStatus(2);
            ingTask.setUpdateTime(System.currentTimeMillis());
            reAppTaskDAO.updateByPrimaryKeySelective(ingTask);

            //关键词剩余次数+1
            Long ingId = ingTask.getKeywordId();
            //旧任务
            ReAppKeywords wordIng = reAppKeywordsDAO.selectLockByKeywordId(ingId);
            Integer leftNum = wordIng.getLeftNum();
            wordIng.setLeftNum(leftNum+1);
            reAppKeywordsDAO.updateByPrimaryKeySelective(wordIng);

            return JsonUtil.buildError(JsonCodeConsts.error_normal,"任务已经超时");
        }

        return JsonUtil.buildSuccess("");

    }


    /**
     * 获取任务详情页面 的数据
     * @param request
     * @param keywordId
     * @return
     */
    public TaskDetailInfo getTaskDetailByKeywordId(HttpServletRequest request,Long keywordId){

        ReAppKeywords word = reAppKeywordsDAO.selectByPrimaryKey(keywordId);

        ReApp app = reAppDAO.selectByPrimaryKey(word.getAppId());

        TaskDetailInfo detailInfo = new TaskDetailInfo();
        detailInfo.setKeywordId(keywordId);

        String icon = app.getAppIcon();
        if (StringUtil.isEmpty(icon)){
            icon="";
        }
        detailInfo.setAppIcon(icon);

        String  size = app.getAppSize();
        if (StringUtil.isEmpty(size)){
            size = "";
        }
        detailInfo.setSize(size);

        BigDecimal money = word.getMoney();
        if (StringUtil.isEmpty(money)){
            money = new BigDecimal("0.00");
        }
        detailInfo.setMoney(money);

        String appPackage = app.getAppPackage();
        if (StringUtil.isEmpty(appPackage)){
            appPackage = "";
        }
        detailInfo.setAppPackage(appPackage);

        String keyword = word.getKeyword();
        if (StringUtil.isEmpty(keyword)){
            keyword="";
        }
        detailInfo.setKeyword(keyword);

        Integer marketId = app.getMarketId();
        if (marketId != null){

            ReAppMarket market = reAppMarketDAO.selectByPrimaryKey(marketId);

            String appMarket = market.getMarketName();
            if (StringUtil.isEmpty(appMarket)){
                appMarket="";
            }
            detailInfo.setAppMarket(appMarket);

            String marketPackage = market.getMarketPackage();
            if (StringUtil.isEmpty(marketPackage)){
                marketPackage="";
            }
            detailInfo.setMarketPackage(marketPackage);

            String marketUrl = market.getMarketUrl();
            if (StringUtil.isEmpty(marketUrl)){
                marketUrl = "";
            }
            detailInfo.setMarketUrl(marketUrl);
        }

        //倒计时(此前已经判断是否超时)
        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        String deviceId = (String) request.getAttribute(RequestConsts.ATTR_DEVICE_ID);

        ReAppTask reAppTask = reAppTaskDAO.selectByUserIdAndDeviceId(userId, deviceId);
        Long createTime = reAppTask.getCreateTime();
        Long overtime = createTime + 60*60*1000;
        Long now = System.currentTimeMillis();
        Long leftTime = overtime - now ;
        detailInfo.setLeftTime(leftTime);



        return detailInfo;
    }

    /**
     * 执行 抢到此任务的逻辑
     * @param request
     * @param keywordId 任务id
     * @return
     */
    public String getMission(HttpServletRequest request,Long keywordId){

        //把此keywordId的剩余数量-1,此数据要锁定起来
        ReAppKeywords word = reAppKeywordsDAO.selectLockByKeywordId(keywordId);
        Integer leftNum = word.getLeftNum();
        if (leftNum <=0){
            return JsonUtil.buildError(JsonCodeConsts.error_normal,"任务已被抢光");
        }

        if (leftNum > 0){
            leftNum = leftNum - 1;
        }
        word.setLeftNum(leftNum);

        reAppKeywordsDAO.updateByPrimaryKeySelective(word);

        //添加一条reAppTask任务,状态是进行中
        ReAppTask task = new ReAppTask();

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        task.setUserId(userId);

        String deviceId = (String) request.getAttribute(RequestConsts.ATTR_DEVICE_ID);
        task.setDeviceId(deviceId);

        task.setKeywordId(keywordId);

        task.setAppId(word.getAppId());
        //第一步为点击复制按钮
        task.setTaskStep(1);

        task.setTaskStatus(0);

        task.setCreateTime(System.currentTimeMillis());
        task.setUpdateTime(System.currentTimeMillis());

        reAppTaskDAO.insert(task);

        return JsonUtil.buildSuccess("抢任务成功");

    }

    /**
     * 放弃之前的任务,接受新id为keywordId的新任务
     * @param request
     * @param keywordId 新任务的ID
     * @return
     */
    public String getAnotherMission(HttpServletRequest request,Long keywordId){

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        String deviceId = (String) request.getAttribute(RequestConsts.ATTR_DEVICE_ID);

        // 查询是否有正在进行中的任务
        ReAppTask oldTask = reAppTaskDAO.selectByUserIdAndDeviceId(userId, deviceId);

        //接受新任务 返回: {"code":0,"msg":"抢任务成功"} ,{"code":9,"msg":"任务已被抢光"}
        String acceptMission = getMission(request,keywordId);

        Result result =  FastJsonUtil.parseObject(acceptMission,Result.class);

        if (result.getCode() == 0){//抢任务成功的情况下,才放弃之前的任务

            //先查此人进行中的任务

            //把旧任务状态设为'放弃'
            oldTask.setTaskStatus(2);
            oldTask.setUpdateTime(System.currentTimeMillis());
            reAppTaskDAO.updateByPrimaryKeySelective(oldTask);

            //把旧任务的剩余次数+1
            Long ingId = oldTask.getKeywordId();
            //旧任务
            ReAppKeywords wordIng = reAppKeywordsDAO.selectLockByKeywordId(ingId);
            Integer leftNum = wordIng.getLeftNum();
            wordIng.setLeftNum(leftNum+1);
            reAppKeywordsDAO.updateByPrimaryKeySelective(wordIng);

            return JsonUtil.buildSuccess("抢任务成功");

        }else {

            return acceptMission;
        }
    }

    /**
     * 页面倒计时结束,任务自动放弃
     * @param request
     * @param keywordId
     * @return
     */
    public String overtimeToGiveUp(HttpServletRequest request,Long keywordId){

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        //自动放弃该任务
        ReAppTask info = reAppTaskDAO.selectIngTaskByUserIdAndKeywordIdLock(userId,keywordId);
        info.setTaskStatus(2);
        info.setUpdateTime(System.currentTimeMillis());
        reAppTaskDAO.updateByPrimaryKeySelective(info);

        // 对应的关键词剩余此数+1
        ReAppKeywords word = reAppKeywordsDAO.selectLockByKeywordId(keywordId);
        int leftNum = word.getLeftNum() + 1;
        // 更新任务
        word.setLeftNum(leftNum);
        reAppKeywordsDAO.updateByPrimaryKeySelective(word);

        return JsonUtil.buildSuccess("任务超时");
    }

    /**
     * synchronized
     *
     * 领取奖励
     * @param request
     * @param keywordId
     * @return
     */
    public synchronized String getMoney(HttpServletRequest request,Long keywordId){


        //任务状态改为完成
        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        ReAppTask task = reAppTaskDAO.selectIngTaskByUserIdAndKeywordIdLock(userId,keywordId);

        if (task != null){
            //先判断是否已经领取过奖励
            Integer status = task.getTaskStatus();
            if (status == 1){

                return JsonUtil.buildError(JsonCodeConsts.error_normal,"已经领取过奖励");

            }else if (status == 2) {

                return JsonUtil.buildError(JsonCodeConsts.error_normal,"您之前已经放弃了该任务或任务已经超时");

            }else {

                task.setTaskStatus(1);
                task.setUpdateTime(System.currentTimeMillis());
                reAppTaskDAO.updateByPrimaryKeySelective(task);
                //用户余额加奖励
                ReAppKeywords word = reAppKeywordsDAO.selectByPrimaryKey(keywordId);
                BigDecimal money = word.getMoney();

                ReUser user = reUserDAO.selectLockByUserId(userId);
                user.setUserMoney(user.getUserMoney().add(money));
                user.setUpdateTime(System.currentTimeMillis());
                reUserDAO.updateByPrimaryKeySelective(user);

                //账户明细加跳数据
                ReAccountDetail detail = new ReAccountDetail();

                Long appId = word.getAppId();
                ReApp app = reAppDAO.selectByPrimaryKey(appId);
                String appName = app.getAppName();
                detail.setUserId(userId);
                detail.setAppId(user.getAppId());
                detail.setAccountMoney(money);
                detail.setDetailType(1);
                detail.setMissionType(MissionType.demo_mission.val);
                detail.setMissionSubtype(MissionSubtype.other.val);
                detail.setDetailContent("完成试玩任务["+appName+"]");
                String now = ElBase.fmtTime(System.currentTimeMillis());
                detail.setDetailTime(now);
                reAccountDetailDAO.insert(detail);

                return JsonUtil.buildSuccess(money+"");
            }
        }
        return null;
    }



}
