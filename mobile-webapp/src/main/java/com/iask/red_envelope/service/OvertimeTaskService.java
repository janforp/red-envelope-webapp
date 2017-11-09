package com.iask.red_envelope.service;

import com.iask.red_envelope.dao.ReAndriodIntegralWallDAO;
import com.iask.red_envelope.dao.ReAndriodUserTaskDAO;
import com.iask.red_envelope.dao.ReAppKeywordsDAO;
import com.iask.red_envelope.dao.ReAppTaskDAO;
import com.iask.red_envelope.model.ReAndriodIntegralWall;
import com.iask.red_envelope.model.ReAndriodUserTask;
import com.iask.red_envelope.model.ReAppKeywords;
import com.iask.red_envelope.model.ReAppTask;
import com.iask.red_envelope.util.el.ElBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Summer on 2016/12/5.
 */
@Service
public class OvertimeTaskService {

    @Autowired
    private ReAppTaskDAO reAppTaskDAO;
    @Autowired
    private ReAppKeywordsDAO reAppKeywordsDAO;
    @Autowired
    private ReAndriodIntegralWallDAO reAndriodIntegralWallDAO;
    @Autowired
    private ReAndriodUserTaskDAO reAndriodUserTaskDAO;

    /**
     * 释放超时任务 ASO
     * @param task
     */
    public void releaseASO(ReAppTask task) {

        long now = System.currentTimeMillis();

        // 重置状态
        ReAppTask info = new ReAppTask();
        info.setTaskId(task.getTaskId());
        info.setTaskStatus(2);
        info.setUpdateTime(now);
        reAppTaskDAO.updateByPrimaryKeySelective(info);

        // 查询任务
        long id = task.getKeywordId();
        ReAppKeywords reAppKeywords = reAppKeywordsDAO.selectLockByKeywordId(id);
        int leftNum = reAppKeywords.getLeftNum() + 1;

        // 更新任务
        ReAppKeywords word = new ReAppKeywords();
        word.setKeywordId(id);
        word.setLeftNum(leftNum);
        reAppKeywordsDAO.updateByPrimaryKeySelective(word);
    }


    /**
     * 释放超时任务 积分墙
     * @param task
     */
    public void releaseWall(ReAndriodUserTask task) {

        String nowTime = ElBase.fmtTime(System.currentTimeMillis());

        // 重置状态
        task.setTaskStatus(2);
        task.setUpdateTime(nowTime);
        reAndriodUserTaskDAO.updateByPrimaryKeySelective(task);

        // 查询任务
        ReAndriodIntegralWall reAndriodIntegralWall = reAndriodIntegralWallDAO.selectLockByWallId(task.getWallId());
        int limitNum = reAndriodIntegralWall.getIsLimitNum();
        if(limitNum == 1) { // 限量
            reAndriodIntegralWall.setLeftNum(reAndriodIntegralWall.getLeftNum() + 1);
            reAndriodIntegralWall.setUpdateTime(nowTime);
            reAndriodIntegralWallDAO.updateByPrimaryKeySelective(reAndriodIntegralWall);
        }
    }



}
