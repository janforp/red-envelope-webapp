package com.iask.red_envelope.task;

import com.iask.red_envelope.dao.ReAppKeywordsDAO;
import com.iask.red_envelope.dao.ReAppTaskDAO;
import com.iask.red_envelope.model.ReAppKeywords;
import com.iask.red_envelope.model.ReAppTask;
import org.craigq.quartz.annotation.TaskCfg;
import org.craigq.quartz.task.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 每5分钟，扫描一次超时任务, 执行释放操作
 */
@Component
@TaskCfg(cron = "0 0/5 * * * ?", concurrent = false, threadCount = 1, runInit = false)
public class HbUserAppTask extends AbstractTask {

    @Autowired
    private ReAppTaskDAO reAppTaskDAO;
    @Autowired
    private ReAppKeywordsDAO reAppKeywordsDAO;

    @Override
    public void run() {

        long now = System.currentTimeMillis();
        long createTime = now - 3600000;

        // 查询超时任务
        List<ReAppTask> list = reAppTaskDAO.selectOvertimeTask(createTime);
        for (ReAppTask task : list) {

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

    }

}
