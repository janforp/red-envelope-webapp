package com.iask.red_envelope.service.mission;

import com.iask.red_envelope.consts.JsonCodeConsts;
import com.iask.red_envelope.dao.ReAccountDetailDAO;
import com.iask.red_envelope.dao.ReCodeExchangeDetailDAO;
import com.iask.red_envelope.dao.ReRecommendMissionDAO;
import com.iask.red_envelope.dao.ReUserDAO;
import com.iask.red_envelope.enums.MissionSubtype;
import com.iask.red_envelope.enums.MissionType;
import com.iask.red_envelope.model.*;
import com.iask.red_envelope.util.JsonUtil;
import com.iask.red_envelope.util.RandomUtil;
import com.iask.red_envelope.util.el.ElBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by Jan on 16/11/10.
 * 关注任务
 */
@Service
public class ExchangeCodeService {

    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;
    @Autowired
    private ReRecommendMissionDAO reRecommendMissionDAO;
    @Autowired
    private ReCodeExchangeDetailDAO reCodeExchangeDetailDAO;

    /**
     * 生成兑换码对象
     * @param missionId
     * @param userId
     * @return
     */
    public ReCodeExchangeDetail createCodeByMissionIdUserId(Long missionId, Integer userId){

        ReCodeExchangeDetail detail = reCodeExchangeDetailDAO.selectByPrimaryKey(missionId, userId);

        if(detail == null) {

            ReRecommendMission mission = reRecommendMissionDAO.selectByPrimaryKey(missionId);
            BigDecimal multiply = new BigDecimal("100.00");
            int minMoney = mission.getMinMoney().multiply(multiply).intValue();
            int maxMoney = mission.getMaxMoney().multiply(multiply).intValue();
            int randomMoney = minMoney;
            if(maxMoney > minMoney) {
                randomMoney = RandomUtil.getRandomBetweenMaxAndMin(maxMoney, minMoney);
            }
            String s_money = String.format("%.2f", (double)(randomMoney/100.0));
            BigDecimal money = new BigDecimal(s_money);

            detail = new ReCodeExchangeDetail();
            detail.setMoney(money);
            detail.setMissionId(missionId);
            detail.setUserId(userId);
            detail.setExchangeStatus(0);
            detail.setCreateTime(ElBase.fmtTime(System.currentTimeMillis()));

            boolean flag = true;
            while (flag){
                String code = RandomUtil.getRandomString(6).toLowerCase();
                ReCodeExchangeDetail oldDetail = reCodeExchangeDetailDAO.selectLockByCodeAndMissionId(code, missionId);
                if (oldDetail == null){
                    flag = false;
                    detail.setExchangeCode(code);
                }
            }

            reCodeExchangeDetailDAO.insertSelective(detail);
        }

        return detail;
    }


    /**
     * 兑换操作
     *
     * @param codeId
     * @param code
     * @return
     */
    public String exchange(String codeId, String code) {

        ReRecommendMission mission = reRecommendMissionDAO.selectByCode(codeId);
        if(mission == null) { // 任务不存在
            return JsonUtil.buildError(JsonCodeConsts.error_normal, "无效链接");
        }

        ReCodeExchangeDetail detail = reCodeExchangeDetailDAO.selectLockByCodeAndMissionId(code, mission.getMissionId());
        if(detail == null) { // 兑换码错误
            return JsonUtil.buildError(JsonCodeConsts.error_normal, "兑换码错误");
        }

        Integer userId = detail.getUserId();
        BigDecimal money = detail.getMoney();
        int exchangeStatus = detail.getExchangeStatus();
        if(exchangeStatus == 1) {
            return JsonUtil.buildError(JsonCodeConsts.error_normal, "兑换码已使用");
        }

        // 兑换流程
        ReRecommendMission reRecommendMission = reRecommendMissionDAO.selectLockByMissionId(mission.getMissionId());
        int leftNum = reRecommendMission.getLeftNum();
        if(leftNum <= 0) {
            return JsonUtil.buildError(JsonCodeConsts.error_normal, "已经被兑换完了,下次请早点来");
        }

        // 更新任务明细表
        reRecommendMission.setLeftNum(leftNum - 1);
        reRecommendMissionDAO.updateByPrimaryKeySelective(reRecommendMission);

        // 更新用户表
        ReUser user = reUserDAO.selectLockByUserId(userId);
        user.setUserMoney(user.getUserMoney().add(money));
        reUserDAO.updateByPrimaryKeySelective(user);

        // 更新详情
        String time = ElBase.fmtTime(System.currentTimeMillis());
        detail.setExchangeStatus(1);
        detail.setExchangeTime(time);
        reCodeExchangeDetailDAO.updateByPrimaryKeySelective(detail);

        // 账户详情
        ReAccountDetail accountDetail = new ReAccountDetail();
        accountDetail.setAccountMoney(money);
        accountDetail.setUserId(userId);
        accountDetail.setAppId(user.getAppId());
        accountDetail.setDetailType(1);
        accountDetail.setMissionType(MissionType.attention_mission.val);
        accountDetail.setMissionSubtype(MissionSubtype.other.val);
        accountDetail.setDetailContent("完成关注任务["+mission.getMissionTitle()+"]");
        accountDetail.setDetailTime(time);
        reAccountDetailDAO.insert(accountDetail);

        return JsonUtil.buildSuccess("兑换成功,获得"+money+"元");

    }

}
