package com.iask.red_envelope.service;

import com.alibaba.fastjson.JSON;

import com.iask.red_envelope.consts.BaseConsts;
import com.iask.red_envelope.model.vo.JuheMsgBack;
import com.iask.red_envelope.util.sms.JuheMessage;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    /**
     * 发送登录验证码
     *
     * @param cellphone 目标手机号
     * @param code      验证码
     */
    public int sendLoginCode(String cellphone, String code, String appName) {
        if (cellphone == null || code == null) {
            return 1;
        }
        String msgBackStr = JuheMessage.sendMsg(code, cellphone, BaseConsts.SMS_LOGIN_TPL_ID, appName);
        JuheMsgBack msgBack = JSON.parseObject(msgBackStr, JuheMsgBack.class);
        return msgBack.getError_code();
    }


}
