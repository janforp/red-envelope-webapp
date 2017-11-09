package com.iask.red_envelope.service.user;

import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.consts.SessionConsts;
import com.iask.red_envelope.dao.ReUserDAO;
import com.iask.red_envelope.model.ReUser;
import com.iask.red_envelope.model.cache.UserKeySecret;
import com.iask.red_envelope.model.dto.ReUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by Jan on 16/8/15.
 *
 *
 */
@Service
public class UserService {

    @Autowired
    private ReUserDAO reUserDAO;
    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    public ReUserInfo getUserInfoById(Integer userId) {
        return reUserDAO.selectUserInfo(userId);
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    public ReUser getUserById(Integer userId) {
        return reUserDAO.selectByPrimaryKey(userId);
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    public ReUser getLockByUserId(Integer userId) {
        return reUserDAO.selectLockByUserId(userId);
    }

    /**
     * 判断此用户是否登录
     * @param request
     * @return
     */
    public boolean isLogin(HttpServletRequest request){

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        return userId == null ? false :true ;
    }


}
