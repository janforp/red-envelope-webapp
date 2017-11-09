package com.iask.red_envelope.service.mission;

import com.iask.red_envelope.dao.ReWelfareDAO;
import com.iask.red_envelope.model.ReWelfare;
import com.iask.red_envelope.util.el.ElBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Janita on 2016/12/14.
 * 福利
 */
@Service
public class WelfareService {

    @Autowired
    private ReWelfareDAO reWelfareDAO;

    /**
     * 查询福利详情页面
     * @param welfareId 点击查看的id
     * @return
     */
    public ReWelfare getWelfareById(Long welfareId){

        ReWelfare welfare = reWelfareDAO.selectByPrimaryKey(welfareId);
        welfare.setParticipantsNum(welfare.getParticipantsNum()+1);
        welfare.setUpdateTime(ElBase.fmtTime(System.currentTimeMillis()));
        reWelfareDAO.updateByPrimaryKeySelective(welfare);

        return welfare;
    }

}
