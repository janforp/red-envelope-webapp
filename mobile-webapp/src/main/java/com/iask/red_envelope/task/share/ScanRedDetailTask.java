package com.iask.red_envelope.task.share;

import com.iask.red_envelope.dao.WxShareRedDetailDAO;
import com.iask.red_envelope.model.ReleaseRedVo;
import com.iask.red_envelope.model.WxShareRedDetail;
import org.craigq.quartz.annotation.TaskCfg;
import org.craigq.quartz.task.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 16/10/27.
 * 定时扫描红包发送失败的数据
 * 塞入任务池,再次发送红包
 */
@Component
@TaskCfg(cron = "0 0 * * * ?", concurrent = false, runInit = true, threadCount = 1)
@Transactional
public class ScanRedDetailTask extends AbstractTask {

    @Autowired
    private ReleaseRedTask releaseRedTask;
    @Autowired
    private WxShareRedDetailDAO wxShareRedDetailDAO ;




    @Override
    public void run() {

        List<ReleaseRedVo> redVos = getRedDetail();

        for (ReleaseRedVo redVo : redVos) {

            releaseRedTask.addMission(redVo);
        }
    }

    /**
     * 查发送失败的数据
     * @return
     */
    public List<ReleaseRedVo> getRedDetail(){

        List<WxShareRedDetail> details = wxShareRedDetailDAO.getReleaseFailRedDetail();

        List<ReleaseRedVo> vos = new ArrayList<>(details.size());

        for (WxShareRedDetail detail : details) {

            ReleaseRedVo redVo = new ReleaseRedVo();
            redVo.setRedId(detail.getRedId());
            redVo.setMoney(detail.getMoney());
            redVo.setUserId(detail.getUserId());
            redVo.setMissionId(detail.getMissionId());
            vos.add(redVo);

            //记录塞入任务池的时候,需要把flag改为未发送
            detail.setFlag(0);
            wxShareRedDetailDAO.updateByPrimaryKeySelective(detail);
        }

        return vos;

    }
}
