package com.iask.red_envelope.service;

import com.iask.red_envelope.dao.ReUserFeedbackDAO;
import com.iask.red_envelope.model.ReUserFeedback;
import com.iask.red_envelope.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Summer on 16/9/20.
 */
@Service
public class FeedbackService {

    @Autowired
    private ReUserFeedbackDAO reUserFeedbackDAO;

    /**
     * 保存
     * @param feedback
     * @return
     */
    public String save(ReUserFeedback feedback) {

        reUserFeedbackDAO.insertSelective(feedback);

        return JsonUtil.buildSuccess(null);

    }

}
