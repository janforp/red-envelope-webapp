package com.iask.red_envelope.task.recommend;

import com.iask.red_envelope.dao.ReRecommendTaskDAO;
import com.iask.red_envelope.model.ReRecommendTask;
import com.iask.red_envelope.service.RecommendMissionService;
import org.craigq.quartz.annotation.TaskCfg;
import org.craigq.quartz.task.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@TaskCfg(cron = "0 0/5 * * * ?", concurrent = false, threadCount = 1, runInit = false)
@Transactional
public class ReleaseRecommendTask extends AbstractTask {

    @Autowired
    private RecommendMissionService recommendMissionService;
    @Autowired
    private ReRecommendTaskDAO reRecommendTaskDAO;

    @Override
    public void run() {

        long now = System.currentTimeMillis();

        // 查询超时任务
        List<ReRecommendTask> list = reRecommendTaskDAO.selectOvertimeTask(now);
        for (ReRecommendTask task : list) {
            recommendMissionService.releaseTask(task);
        }

    }

}
