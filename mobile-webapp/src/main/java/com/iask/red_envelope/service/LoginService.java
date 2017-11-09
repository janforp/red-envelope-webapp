package com.iask.red_envelope.service;

import com.iask.red_envelope.config.Config;
import com.iask.red_envelope.consts.JsonCodeConsts;
import com.iask.red_envelope.consts.MsgConsts;
import com.iask.red_envelope.consts.SessionConsts;
import com.iask.red_envelope.dao.ReUserDAO;
import com.iask.red_envelope.dao.ReVerifyCodeDAO;
import com.iask.red_envelope.dao.ReWithdrawBindDAO;
import com.iask.red_envelope.enums.HbUserStatus;
import com.iask.red_envelope.enums.VerifyCodeStatus;
import com.iask.red_envelope.model.ReUser;
import com.iask.red_envelope.model.ReVerifyCode;
import com.iask.red_envelope.model.ReWithdrawBind;
import com.hongbao.api.model.cache.UserInfo;
import com.iask.red_envelope.model.param.LoginParamVo;
import com.iask.red_envelope.service.user.SessionCacheService;
import com.iask.red_envelope.service.user.UserCacheService;
import com.iask.red_envelope.util.CommonMethod;
import com.iask.red_envelope.util.JsonUtil;
import com.iask.red_envelope.util.RandomUtil;
import com.iask.red_envelope.util.StringUtil;
import com.wujie.common.beanutil.BeanUtils;
import org.craigq.common.logger.ILogger;
import org.craigq.common.logger.LogMgr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 16/8/23.
 * 网页端 登录
 */
@Service
public class LoginService {

    @Autowired
    private ReVerifyCodeDAO reVerifyCodeDAO ;
    @Autowired
    private ReWithdrawBindDAO reWithdrawBindDAO;
    @Autowired
    private SmsService smsService ;
    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private UserCacheService userCacheService;
    @Autowired
    private SessionCacheService sessionCacheService;


    /**
     * 发送验证码
     * @param phone
     * @return
     */
    public String sendLoginCode(String phone){

        long startTime = System.currentTimeMillis() - Config.getSecurity().getSmsVerifyCodeExpiredTime() ;
        String latestSendCode = reVerifyCodeDAO.selectLatestCodeByCellphoneAndNotVerifyWithinTime (phone,startTime) ;

        String code = null;
        if (latestSendCode != null){
            code = latestSendCode ;
        }else {
            code = RandomUtil.getRandomNumberString(4);
        }

        ReVerifyCode reVerifyCode = new ReVerifyCode();

        reVerifyCode.setCode(code);
        reVerifyCode.setCodeVerifyStatus(VerifyCodeStatus.not_verify.val);
        reVerifyCode.setGenerateTime(System.currentTimeMillis());
        reVerifyCode.setCellphone(phone);

        boolean smsSendSuccess = false ;
        boolean isRealSendSms = Config.getSecurity().isRealSms() ;

        if (isRealSendSms){

            int result = smsService.sendLoginCode(phone, code , "");
            if (result == 0) {
                smsSendSuccess = true ;
            }
        }else {
            smsSendSuccess = true ;
        }

        if (smsSendSuccess) {
            reVerifyCodeDAO.insertSelective(reVerifyCode);
            if (isRealSendSms) {
                return JsonUtil.buildSuccess("短信验证码发送成功");
            }else {
                String msgWithCode = CommonMethod.formatArg(MsgConsts.SUCCESS_TEST_VERIFY_SMS_SEND, code) ;
                ILogger logger = LogMgr.getLogger();
                if (logger.isInfoEnabled()){
                    logger.info(msgWithCode);
                }

                return JsonUtil.buildSuccess(msgWithCode) ;
            }
        }else {
            return JsonUtil.buildError(JsonCodeConsts.error_normal,"短信验证码发送成功");
        }
    }



    /**
     * 盘点手机 验证码是否正确
     * @param phone
     * @param verifyCode
     * @return
     */
    public String verifySmsCode(String phone, String verifyCode){

        long startTime = System.currentTimeMillis() - Config.getSecurity().getSmsVerifyCodeExpiredTime() ;

        List<ReVerifyCode> verifyCodeList = reVerifyCodeDAO.selectByCellphoneAndCodeNotVerifyWithinTime(phone,startTime);

        if (verifyCodeList == null  || verifyCodeList.size() == 0) {
            return JsonUtil.buildError(JsonCodeConsts.error_normal,"错误的短信验证码");
        }

        Long matchedCodeId = null ;
        for (ReVerifyCode vCode : verifyCodeList) {
            if (verifyCode.equals(vCode.getCode())) {
                matchedCodeId = vCode.getId() ;
                break;
            }
        }

        List<Long> verifyFailIds = new ArrayList<>(verifyCodeList.size());
        if (matchedCodeId == null) {
            for (ReVerifyCode vCode : verifyCodeList){
                verifyFailIds.add(vCode.getId());
            }
        }else {

            for (ReVerifyCode vCode : verifyCodeList) {
                if (matchedCodeId.longValue() != vCode.getId().longValue()) {
                    verifyFailIds.add(vCode.getId());
                }
            }
        }

        if (verifyFailIds.size() > 0) {
            if (verifyFailIds.size() > 1){

                reVerifyCodeDAO.updateCodeVerifyStatusByIds(verifyFailIds,VerifyCodeStatus.verify_fail.val);
            }else {
                reVerifyCodeDAO.updateCodeVerifyStatusById(verifyFailIds.get(0),VerifyCodeStatus.verify_fail.val);
            }
        }
        if (matchedCodeId != null){
            reVerifyCodeDAO.updateCodeVerifyStatusById(matchedCodeId,VerifyCodeStatus.verify_success.val);
            return null;
        }
        return JsonUtil.buildError(JsonCodeConsts.error_normal,"短信验证码错误");
    }

    /**
     * 判断 此 手机号码 是否 注册
     * @param request
     * @param phone
     * @return
     */
    public boolean isRegister(HttpServletRequest request,String phone) {

        phone = CommonMethod.validateAndFixedTelephone(phone) ;

        if (phone == null){

            return false ;
        }

        ReUser user = reUserDAO.selectUserByMobile(phone);

        if (user == null) {

            return false ;
        }
        return true;
    }

    /**
     * 金币商城 登录
     * @param request
     * @param vo
     * @return
     */
    public String doLogin(HttpServletRequest request,HttpServletResponse response , LoginParamVo vo) {

        if (!vo.getPhone().equals("13738053603")){  //测试帐号

            String errResult = verifySmsCode(vo.getPhone(),vo.getSmsCode());
            if (errResult != null) {
                return errResult ;
            }
        }

        ReUser reUser = reUserDAO.selectUserByMobile(vo.getPhone()) ;

        if (! reUser.getUserStatus().equals(HbUserStatus.normal.val)) {

            return JsonUtil.buildError(JsonCodeConsts.error_normal , "该账号已被封");
        }

        if(!StringUtil.isEmpty(vo.getOpenId())) { // 绑定微信
            ReWithdrawBind bind = new ReWithdrawBind();
            bind.setUserId(reUser.getUserId());
            bind.setOpenId(vo.getOpenId());
            bind.setNickname(vo.getNickName());
            reWithdrawBindDAO.insert(bind);
        }

        //登录成功之后的操作:保持会话状态
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(reUser,userInfo);

        userCacheService.updateUser(userInfo);

        buildLoginResult(request, response, reUser);

        return JsonUtil.buildSuccess("登录成功");
    }

    /**
     * 把sessionId放入缓存
     * @param request
     * @param response
     * @param reUser
     */
    public void buildLoginResult(HttpServletRequest request, HttpServletResponse response, ReUser reUser) {

        if (reUser != null) {
            Map<String,Serializable> sessionMap = null ;

            String cacheSessionId = sessionCacheService.getRequestedCacheSessionId(request) ;

            if (cacheSessionId == null) {

                cacheSessionId = sessionCacheService.initialize(request,response);

                sessionMap = new HashMap<>(1);
                sessionMap.put(SessionConsts.USER_KEY ,reUser.getUserKey());

            }else {
                sessionMap = sessionCacheService.getCachedSession(cacheSessionId) ;

                if (sessionMap == null) {
                    cacheSessionId = sessionCacheService.initialize(request,response);
                    sessionMap = new HashMap<>(1);
                    sessionMap.put(SessionConsts.USER_KEY,reUser.getUserKey());
                }else {
                    sessionMap.remove(SessionConsts.USER_KEY);
                    sessionMap.put(SessionConsts.USER_KEY,reUser.getUserKey());
                }
            }
            sessionCacheService.updateCachedSession(cacheSessionId,sessionMap);
        }
    }
}
