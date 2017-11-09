package com.iask.red_envelope;

import com.iask.red_envelope.dao.ReUserDAO;
import com.iask.red_envelope.dao.ReUserPortraitDAO;
import com.iask.red_envelope.model.ReUser;
import com.iask.red_envelope.util.CommonMethod;
import com.iask.red_envelope.util.MD5Encryption;
import com.iask.red_envelope.util.RandomUtil;
import com.iask.red_envelope.util.weixin.SHA1;
import jxl.Workbook;
import jxl.write.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Created by Jan on 16/8/30.
 */
public class TestByJan {


    ReUserDAO reUserDAO = new ReUserDAO();

    @Test
    public void test1(){

        String s = "jsapi_ticket=sM4AOVdWfPE4DxkXGEs8VMCPGGVi4C3VM0P37wVUCFvkVAy_90u5h9nbSlYy3-Sl-HhTdfl2fzFy1AOcHKP7qg&noncestr=Wm3WZYTPz0wzccnW&timestamp=1414587457&url=http://mp.weixin.qq.com?params=value";

        s = SHA1.encode(s);

        System.out.println(s);
    }

    @Test
    public void test2(){

        for (int i=0 ; i<10 ;i++) {

            String cellphone = RandomUtil.getRandomNumberString(11);

            ReUser registerUser = new ReUser() ;

            String icon = "http://image.lswuyou.cn/portraits/4.png";

            registerUser.setPortrait(icon);

            long now = System.currentTimeMillis();

            registerUser.setReId("hb_"+now+ RandomUtil.getRandomNumberString(4));
            registerUser.setNickname("手机用户"+cellphone);
            registerUser.setPortrait(icon);
            registerUser.setUserKey(UUID.randomUUID().toString().toLowerCase());
            registerUser.setUserSecret(CommonMethod.generateUserSecret());
            registerUser.setMobile(cellphone);
            registerUser.setPassword(MD5Encryption.encodeMD5(cellphone));
            registerUser.setGender(0);
            registerUser.setUserMoney(new BigDecimal(0));
            registerUser.setUserScore(0);
            registerUser.setSignCount(0);
            registerUser.setBindType(0);
            registerUser.setUserStatus(1);
            registerUser.setUserType(0);
            registerUser.setCreateTime(now);
            registerUser.setUpdateTime(now);

            reUserDAO.insertSelective(registerUser);
        }
    }
}
