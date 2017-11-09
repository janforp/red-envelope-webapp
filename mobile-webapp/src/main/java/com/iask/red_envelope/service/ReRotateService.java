package com.iask.red_envelope.service;

import com.iask.red_envelope.consts.JsonCodeConsts;
import com.iask.red_envelope.dao.ReRotateDetailDAO;
import com.iask.red_envelope.dao.ReScoreDetailDAO;
import com.iask.red_envelope.dao.ReUserDAO;
import com.iask.red_envelope.model.ReRotateDetail;
import com.iask.red_envelope.model.ReScoreDetail;
import com.iask.red_envelope.model.ReUser;
import com.iask.red_envelope.util.JsonUtil;
import com.iask.red_envelope.util.el.ElBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Summer on 16/9/8.
 */
@Service
public class ReRotateService {

    @Autowired
    public ReRotateDetailDAO reRotateDetailDAO;

    @Autowired
    public ReUserDAO reUserDAO;

    @Autowired
    public ReScoreDetailDAO reScoreDetailDAO;

    /**
     * 今天已抽奖次数
     * @param userId
     * @return
     */
    public int rotateTimes(Integer userId) {
        return reRotateDetailDAO.selectRotateTimesByUserId(userId, ElBase.fmtDay(System.currentTimeMillis()));
    }

    /**
     * 抽奖
     * @param userId
     * @return
     */
    public String doRotate(Integer userId) {

        // 今天已抽奖次数
        int times = rotateTimes(userId);

        if(times >= 3) { // 金币抽奖

            // 查询用户信息
            ReUser user = reUserDAO.selectLockByUserId(userId);
            int score = user.getUserScore();
            if(score < 5) {
                return JsonUtil.buildError(JsonCodeConsts.error_normal, "金币不足");
            }

            // 金币明细
            ReScoreDetail detail = new ReScoreDetail();
            detail.setUserId(userId);
            detail.setAppId(user.getAppId());
            detail.setScore(5);
            detail.setScoreType(0);
            detail.setScoreContent("幸运大转盘抽奖");
            detail.setScoreTime(ElBase.fmtTime(System.currentTimeMillis()));
            reScoreDetailDAO.insert(detail);

            // 更新用户信息
            ReUser reUser = new ReUser();
            reUser.setUserId(userId);
            reUser.setUserScore(score - 5);
            reUser.setUpdateTime(System.currentTimeMillis());
            reUserDAO.updateByPrimaryKeySelective(reUser);

        }


        Map<String, Object> dataMap = new HashMap<>(2);

        int num = drawNum();
        String text = "谢谢参与";

        if(num == 2) {
            text = "1金币";
        }else if(num == 4) {
            text = "10金币";
        }

        dataMap.put("num", num);
        dataMap.put("text", text);


        // 记录抽奖详情
        ReRotateDetail detail = new ReRotateDetail();
        detail.setUserId(userId);
        detail.setRotateContent(text);
        detail.setRotateTime(ElBase.fmtTime(System.currentTimeMillis()));
        reRotateDetailDAO.insert(detail);

        if(num != 0) { //中奖

            int awardScore = 0;

            if(num == 2) {
                awardScore = 1;
            }else if(num == 4) {
                awardScore = 10;
            }

            // 查询用户信息
            ReUser user = reUserDAO.selectLockByUserId(userId);

            // 金币明细
            ReScoreDetail scoreDetail = new ReScoreDetail();
            scoreDetail.setUserId(userId);
            scoreDetail.setAppId(user.getAppId());
            scoreDetail.setScore(awardScore);
            scoreDetail.setScoreType(1);
            scoreDetail.setScoreContent("幸运大转盘抽奖");
            scoreDetail.setScoreTime(ElBase.fmtTime(System.currentTimeMillis()));
            reScoreDetailDAO.insert(scoreDetail);

            // 更新用户信息
            ReUser reUser = new ReUser();
            reUser.setUserId(userId);
            reUser.setUserScore(user.getUserScore() + awardScore);
            reUser.setUpdateTime(System.currentTimeMillis());
            reUserDAO.updateByPrimaryKeySelective(reUser);

        }

        return JsonUtil.buildSuccessData(dataMap);

    }


    /**
     * 抽中奖品序号
     * @return
     */
    public int drawNum() {

        int num = 0;

        double randomNumber = Math.random();

        if(randomNumber >= 0.0 && randomNumber < 0.10) {
            num = 4;
        }else if(randomNumber >= 0.10 && randomNumber < 0.40) {
            num = 2;
        }

        return num;

    }




}
