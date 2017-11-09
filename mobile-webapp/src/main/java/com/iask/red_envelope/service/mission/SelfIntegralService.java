package com.iask.red_envelope.service.mission;

import com.iask.red_envelope.dao.ReAndriodIntegralWallDAO;
import com.iask.red_envelope.model.ReAndriodIntegralWall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jan on 2016/12/5.
 * 自己的积分墙
 */
@Service
public class SelfIntegralService {

    @Autowired
    private ReAndriodIntegralWallDAO reAndriodIntegralWallDAO;

    /**
     * 根据id查询积分墙任务
     * @param wallId
     * @return
     */
    public ReAndriodIntegralWall getWallById(Long wallId){

        return reAndriodIntegralWallDAO.selectByPrimaryKey(wallId);
    }

}
