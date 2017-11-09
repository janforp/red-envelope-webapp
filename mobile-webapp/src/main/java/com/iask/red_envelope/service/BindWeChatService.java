package com.iask.red_envelope.service;

import com.iask.red_envelope.consts.JsonCodeConsts;
import com.iask.red_envelope.dao.ReUserDAO;
import com.iask.red_envelope.dao.ReWithdrawBindDAO;
import com.iask.red_envelope.model.ReUser;
import com.iask.red_envelope.model.ReWithdrawBind;
import com.iask.red_envelope.model.dto.WxNotShareSign;
import com.iask.red_envelope.model.param.BindWxSubmitVo;
import com.iask.red_envelope.util.JsonUtil;
import com.wujie.common.sdk.weixin.oauth2.domain.UserInfoDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Jan on 16/8/26.
 * 绑定微信
 */
@Service
public class BindWeChatService {

    @Autowired
    private ReWithdrawBindDAO reWithdrawBindDAO;
    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private LoginService loginService;
    @Autowired
    private WxNotShareService wxNotShareService;

    /**
     * 用户微信授权之后,拿到了用户的信息
     *
     * 此时可以跳转到想要的 页面
     * @param request
     * @param userInfoDomain
     * @return
     */
    public String doBindWx(HttpServletRequest request, UserInfoDomain userInfoDomain) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        request.setAttribute("openId",userInfoDomain.getOpenid());
        request.setAttribute("nickname",userInfoDomain.getNickname());
        request.setAttribute("icon",userInfoDomain.getHeadimgurl());

        WxNotShareSign share = wxNotShareService.getSign(request);

        request.setAttribute("share",share);

        return "page/bind_wx/bind_wx";
    }

    /**
     * 绑定微信提交之后
     * 插入 绑定表中
     * @param request
     * @param vo
     * @return
     */
    public String doSubmitBind(HttpServletRequest request, BindWxSubmitVo vo) {

        String errResult = loginService.verifySmsCode(vo.getPhone(),vo.getSmsCode());
        if (errResult != null) {
            return errResult ;
        }

        ReUser reUser = reUserDAO.selectUserByMobile(vo.getPhone()) ;
        if (reUser == null) {
            return JsonUtil.buildError(JsonCodeConsts.error_normal,"用户未注册");
        }
        ReWithdrawBind bind = reWithdrawBindDAO.selectByPrimaryKey(reUser.getUserId());
        if (bind != null) {
            return JsonUtil.buildError(JsonCodeConsts.error_normal,"该用户已绑定过");
        }
        bind = reWithdrawBindDAO.selectByOpen(vo.getOpenId());
        if (bind != null) {
            return JsonUtil.buildError(JsonCodeConsts.error_normal,"此微信号已经绑定过其他账号");
        }
        bind = new ReWithdrawBind();
        bind.setUserId(reUser.getUserId());
        bind.setNickname(vo.getNickname());
        bind.setOpenId(vo.getOpenId());

        reWithdrawBindDAO.insertSelective(bind);

        //登录成功之后的操作:保持会话状态

        return JsonUtil.buildSuccess("绑定成功");
    }

}
