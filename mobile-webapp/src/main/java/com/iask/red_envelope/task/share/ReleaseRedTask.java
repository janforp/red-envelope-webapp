package com.iask.red_envelope.task.share;

import com.iask.red_envelope.model.ReleaseRedVo;
import com.iask.red_envelope.service.share.ReleaseRedService;
import org.craigq.quartz.annotation.TaskCfg;
import org.craigq.quartz.task.AbstractTask;
import org.craigq.quartz.task.support.IQueueTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Jan on 16/10/27.
 *
 */
@Component
@TaskCfg(cron = "0/5 * * * * ?", concurrent = false, runInit = true, threadCount = 15)
@Transactional
public class ReleaseRedTask extends AbstractTask implements IQueueTask<ReleaseRedVo>{

    @Autowired
    private ReleaseRedService releaseRedService ;

    /**
     * 任务列表
     */
    private final static ConcurrentLinkedQueue<ReleaseRedVo> queue = new ConcurrentLinkedQueue<>();

    /**
     * 向列表中塞入任务
     * @param redVo
     */
    public void addMission(ReleaseRedVo redVo){

        boolean isRunning = this.isRunning() ;

        if (! isRunning) {

            throw new RuntimeException(this.getClass().getSimpleName()+ " 已暂停,拒绝请求");
        }
        if (redVo != null) {

            queue.add(redVo);
        }
    }

    @Override
    public void run() {

        while (! queue.isEmpty()) {

            ReleaseRedVo redVo = queue.poll() ;

            releaseRedService.releaseRed(redVo);
        }
    }

    @Override
    public Collection getQueue() {
        return null;
    }

    @Override
    public int getQueueSize() {
        return queue.size();
    }
}
