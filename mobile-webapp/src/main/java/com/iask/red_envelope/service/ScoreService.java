package com.iask.red_envelope.service;

import com.iask.red_envelope.dao.ReScoreDetailDAO;
import com.iask.red_envelope.util.el.ElBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jan on 16/11/16.
 * 金币
 */
@Service
public class ScoreService {

    @Autowired
    private ReScoreDetailDAO reScoreDetailDAO;

    /**
     * 今天获得金币
     * @param userId
     * @return
     */
    public Integer getScoreToday(Integer userId){

        String today = ElBase.fmtTime(System.currentTimeMillis());
        return reScoreDetailDAO.getScoreByDay(userId,today);
    }
}
