package com.iask.red_envelope.service;

import com.iask.red_envelope.consts.RandomNicknameConsts;
import com.iask.red_envelope.model.vo.RotateRandomVo;
import com.iask.red_envelope.util.RandomUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jan on 16/10/10.
 * 随机昵称/手机号/中奖
 */
@Service
public class RandomService {

    String[] nicknames = RandomNicknameConsts.NICKNAMES;
    String[] phones = {"131","132","134","135","136","137","138","139","151","152","155","157","158","159"};
    String[] awards = {"1金币","50金币","10金币"};
    String[] bigAward = {"1元现金","100金币"};


    public List<RotateRandomVo> getNRandomDetail(Integer n){

        List<RotateRandomVo> details = new ArrayList<>(n);

        boolean flag = true ;

        for (int i=0;i<n;i++){

            RotateRandomVo detail = new RotateRandomVo();
            //随机为:手机或昵称
            int r = RandomUtil.getRandomBetweenMaxAndMin(2,0);
            String nickname = "";
            if (r == 0){//用汉字

                //从nicknames中随机一个昵称
                nickname = nicknames[RandomUtil.getRandomBetweenMaxAndMin(nicknames.length,0)];

                String regEx = "[\u4e00-\u9fa5]";
                Pattern pat = Pattern.compile(regEx);
                Matcher matcher = pat.matcher(nickname);

                if(matcher.find()) { // 含中文
                    if (nickname.length() >= 6) {
                        nickname = nickname.substring(0, 5)+"...";
                    }
                }else {
                    if (nickname.length() >= 12) {
                        nickname = nickname.substring(0, 10)+"...";
                    }
                }

                detail.setNickname(nickname);

            }else if (r==1){//用电话

                String phoneHead = phones[RandomUtil.getRandomBetweenMaxAndMin(phones.length,0)];
                String phoneTail = RandomUtil.getRandomNumberString(4);
                nickname = phoneHead+"****"+phoneTail;

                detail.setNickname(nickname);

            }



            //时间统一用'一秒前',不能用太久之前的
            detail.setTime("一秒前");

            double randomNumber = Math.random();

            if (randomNumber <0.03 &&  randomNumber >= 0.0 && flag){//大奖,从bigAward中取一个

                int randomBig = RandomUtil.getRandomBetweenMaxAndMin(2,0);

                String award =   bigAward[randomBig];

                detail.setAward(award);

                flag = false ;

            }else{  //小奖,从awards中取一个

                String award = awards[RandomUtil.getRandomBetweenMaxAndMin(awards.length,0)];

                detail.setAward(award);
            }

            details.add(detail);
        }

        return details;
    }
}
