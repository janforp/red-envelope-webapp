package com.iask.red_envelope.service.sign;

import com.iask.red_envelope.consts.JsonCodeConsts;
import com.iask.red_envelope.consts.MsgConsts;
import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.consts.ValueConsts;
import com.iask.red_envelope.dao.ReScoreDetailDAO;
import com.iask.red_envelope.dao.ReUserDAO;
import com.iask.red_envelope.model.ReScoreDetail;
import com.iask.red_envelope.model.ReUser;
import com.iask.red_envelope.model.vo.SignDateScoreVo;
import com.iask.red_envelope.model.vo.sign.SignPageVo;
import com.iask.red_envelope.service.user.UserService;
import com.iask.red_envelope.util.CommonMethod;
import com.iask.red_envelope.util.JsonUtil;
import com.iask.red_envelope.util.RandomUtil;
import com.iask.red_envelope.util.el.ElBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 16/8/15.
 * 签到页面
 */
@Service
public class SignService {

    @Autowired
    private UserService userService ;
    @Autowired
    private ReUserDAO reUserDAO ;
    @Autowired
    private ReScoreDetailDAO reScoreDetailDAO;

    /**
     * 判断该用户今日是否已经签到
     * @param userId
     * @return  若true:今日已签到
     */
    public Boolean isSignToday (Integer userId){

        if (userId == null) {
            return false ;
        }
        ReUser user = userService.getUserById(userId);
        if (user == null) {
            return false ;
        }
        Long signTime = user.getSignTime();
        // 今日日期
        String today = ElBase.fmtDay(System.currentTimeMillis());
        // 上次签到日期
        String signInDate = ElBase.fmtDay(signTime);

        if (today.equals(signInDate)){
            return true;
        }

        return false;
    }

    /**
     * 签到页面数据
     *
     * @param userId
     * @return
     */
    public SignPageVo getSingVo(Integer userId){

        SignPageVo vo = new SignPageVo();

        ReUser user = reUserDAO.selectByUserIdAndStatus(userId);

        Long lastSignTime = user.getSignTime();
        String lastSignDate = ElBase.fmtDay(lastSignTime);
        long now = System.currentTimeMillis();
        long yesterdayStamp = now - ValueConsts.dayStamp;
        String today = ElBase.fmtDay(now);
        String yesterday = ElBase.fmtDay(yesterdayStamp);
        Integer count = user.getSignCount();

        if(!lastSignDate.equals(today) && !lastSignDate.equals(yesterday)) {
            count = 0;
        }

        vo.setCount(count);
        Integer score = reScoreDetailDAO.getScoreByDay(userId, ElBase.fmtDay(System.currentTimeMillis()));
        if(score == null) {
            score = 0;
        }
        vo.setScoreToday(score);
        vo.setTotalScore(reScoreDetailDAO.getTotalScore(userId));

        return vo;
    }


    /**
     * 用户在签到页面上8个圈圈中的数据
     * @param userId
     * @return
     */
    public List<SignDateScoreVo> getSignV4Data(Integer userId){

        List<SignDateScoreVo> scores = new ArrayList<>(9);

        ReUser user = userService.getUserById(userId);

        Long lastSignTime = user.getSignTime();
        String lastSignDate = ElBase.fmtDay(lastSignTime);
        long now = System.currentTimeMillis();
        long yesterdayStamp = now - ValueConsts.dayStamp;
        String today = ElBase.fmtDay(now);
        String yesterday = ElBase.fmtDay(yesterdayStamp);
        Integer count = user.getSignCount();

        if(lastSignDate.equals(today)) { // 今天已签到

            if(count == 1) { // 未连续签到
                for (int i = 0; i < 10; i++, yesterdayStamp += ValueConsts.dayStamp){

                    SignDateScoreVo vo = new SignDateScoreVo();

                    String date = ElBase.fmtMonthDay(yesterdayStamp);
                    int score = i;
                    if (i == 1){
                        date = "今天";
                    }else if (i == 2){
                        date = "明天";
                    }else if (i >= 6){
                        score = 5;
                    }
                    vo.setDate(date);
                    vo.setScore(score);

                    scores.add(vo);
                }

            }else if (count >= 6){

                for (int i = 0; i < 10; i++, yesterdayStamp += ValueConsts.dayStamp){

                    SignDateScoreVo vo = new SignDateScoreVo();
                    String date = ElBase.fmtMonthDay(yesterdayStamp);

                    if (i == 1){
                        date = "今天";
                    }else if (i == 2){
                        date = "明天";
                    }
                    vo.setScore(5);
                    vo.setDate(date);

                    scores.add(vo);
                }

            }else {

                for (int i = 0; i < 10; i++, yesterdayStamp += ValueConsts.dayStamp){

                    SignDateScoreVo vo = new SignDateScoreVo();
                    String date = ElBase.fmtMonthDay(yesterdayStamp);

                    if(i == 0) {
                        count--;
                    }else if (i == 1){
                        date = "今天";
                    }else if (i == 2){
                        date = "明天";
                    }
                    int score = count;
                    if (count >= 5){
                        score = 5;
                    }
                    count++;
                    vo.setDate(date);
                    vo.setScore(score);

                    scores.add(vo);
                }
            }
        }else { // 今天未签到

            if (lastSignDate.equals(yesterday)){ // 连续签到

                if (count >= 5){

                    for (int i = 0; i < 10; i++, yesterdayStamp += ValueConsts.dayStamp){

                        SignDateScoreVo vo = new SignDateScoreVo();

                        String date = ElBase.fmtMonthDay(yesterdayStamp);

                        if (i == 1){
                            date = "今天";
                        }else if (i == 2){
                            date = "明天";
                        }
                        vo.setDate(date);
                        vo.setScore(5);

                        scores.add(vo);
                    }

                }else {

                    for (int i = 0; i < 10; i++, yesterdayStamp += ValueConsts.dayStamp){

                        SignDateScoreVo vo = new SignDateScoreVo();
                        String date = ElBase.fmtMonthDay(yesterdayStamp);

                        if (i == 1){
                            date = "今天";
                        }else if (i == 2){
                            date = "明天";
                        }
                        int score = count;
                        if (count >= 5){
                            score = 5;
                        }
                        count++;

                        vo.setDate(date);
                        vo.setScore(score);

                        scores.add(vo);
                    }

                }
            }else { // 非连续签到, 今日若签到 ,则为第一次签到

                for (int i = 0; i < 10; i++, yesterdayStamp += ValueConsts.dayStamp){

                    SignDateScoreVo vo = new SignDateScoreVo();
                    String date = ElBase.fmtMonthDay(yesterdayStamp);
                    int score = i;
                    if (i == 1){
                        date = "今天";
                    }else if (i == 2){
                        date = "明天";
                    }else if (i >= 6){
                        score = 5;
                    }
                    vo.setDate(date);
                    vo.setScore(score);

                    scores.add(vo);
                }
            }
        }
        return scores;
    }

    /**
     * 已经登录,则分情况,获取7个圈圈中的数据
     * @param isSign    是否签到
     * @param userId
     * @param yesterdayStamp
     * @return
     */
    public List<SignDateScoreVo> getLoginSignData(boolean isSign, Integer userId, Long yesterdayStamp){

        List<SignDateScoreVo> scores = new ArrayList<>(7);

        ReUser user = userService.getUserById(userId);

        Integer count = user.getSignCount();
        Long lastSignTime = user.getSignTime();

        String lastSignDate = ElBase.fmtDay(lastSignTime);
        String yesterday = ElBase.fmtDay(yesterdayStamp);



        if(isSign) { // 今天已签到

            if(count == 1) { // 未连续签到

                for (int i = 0; i < 7; i++, yesterdayStamp += ValueConsts.dayStamp){

                    SignDateScoreVo vo = new SignDateScoreVo();

                    String date = ElBase.fmtMonthDay(yesterdayStamp);
                    int score = i;

                    if (i == 1){
                        date = "今天";
                    }else if (i == 2){
                        date = "明天";
                    }else if (i == 6){
                        score = 5;
                    }

                    vo.setDate(date);
                    vo.setScore(score);

                    scores.add(vo);
                }

            }else if (count >= 6){

                for (int i = 0; i < 7; i++, yesterdayStamp += ValueConsts.dayStamp){

                    SignDateScoreVo vo = new SignDateScoreVo();

                    String date = ElBase.fmtMonthDay(yesterdayStamp);

                    if (i == 1){
                        date = "今天";
                    }else if (i == 2){
                        date = "明天";
                    }

                    vo.setDate(date);
                    vo.setScore(5);

                    scores.add(vo);

                }

            }else {

                for (int i = 0; i < 7; i++, yesterdayStamp += ValueConsts.dayStamp){

                    SignDateScoreVo vo = new SignDateScoreVo();

                    String date = ElBase.fmtMonthDay(yesterdayStamp);

                    if(i == 0) {
                        count--;
                    }else if (i == 1){
                        date = "今天";
                    }else if (i == 2){
                        date = "明天";
                    }

                    int score = count;
                    if (count >= 5){
                        score = 5;
                    }
                    count++;

                    vo.setDate(date);
                    vo.setScore(score);

                    scores.add(vo);
                }

            }


        }else { // 今天未签到

            if (lastSignDate.equals(yesterday)){ // 连续签到

                if (count >= 5){

                    for (int i = 0; i < 7; i++, yesterdayStamp += ValueConsts.dayStamp){

                        SignDateScoreVo vo = new SignDateScoreVo();

                        String date = ElBase.fmtMonthDay(yesterdayStamp);

                        if (i == 1){
                            date = "今天";
                        }else if (i == 2){
                            date = "明天";
                        }

                        vo.setDate(date);
                        vo.setScore(5);

                        scores.add(vo);

                    }

                }else {

                    for (int i = 0; i < 7; i++, yesterdayStamp += ValueConsts.dayStamp){

                        SignDateScoreVo vo = new SignDateScoreVo();

                        String date = ElBase.fmtMonthDay(yesterdayStamp);

                        if (i == 1){
                            date = "今天";
                        }else if (i == 2){
                            date = "明天";
                        }

                        int score = count;
                        if (count >= 5){
                            score = 5;
                        }
                        count++;

                        vo.setDate(date);
                        vo.setScore(score);

                        scores.add(vo);
                    }

                }

            }else { // 非连续签到, 今日若签到 ,则为第一次签到

                for (int i = 0; i < 7; i++, yesterdayStamp += ValueConsts.dayStamp){

                    SignDateScoreVo vo = new SignDateScoreVo();

                    String date = ElBase.fmtMonthDay(yesterdayStamp);
                    int score = i;

                    if (i == 1){
                        date = "今天";
                    }else if (i == 2){
                        date = "明天";
                    }else if (i == 6){
                        score = 5;
                    }


                    vo.setDate(date);
                    vo.setScore(score);

                    scores.add(vo);
                }
            }

        }

        return scores;

    }

    /**
     * 若没有登录,7个圈圈中的数据如下
     * @param yesterdayStamp
     * @return
     */
    public List<SignDateScoreVo> getNotLoginVo(Long yesterdayStamp){

        List<SignDateScoreVo> scores = new ArrayList<>(7);

        for (int i = 0; i < 7;i ++,yesterdayStamp = yesterdayStamp + ValueConsts.dayStamp){

            SignDateScoreVo vo = new SignDateScoreVo();

            String date = ElBase.fmtMonthDay(yesterdayStamp);
            int score = i;
            if (i == 1) {
                date = "今天";
            }else if (i == 2) {
                date = "明天";
            }else if (i >= 5) {
                score = 5;
            }

            vo.setDate(date);
            vo.setScore(score);

            scores.add(vo);
        }

        return scores;

    }

    /**
     * 签到,连续n次签到,n<5,每次送n积分,
     * n>=5,每次送5积分,
     * 若n % 30 == 0,则额外送100积分
     *
     * @param userId
     * @return
     */
    public String sign (Integer userId) {

        ReUser user = userService.getLockByUserId(userId);

        Long lastSignTime = user.getSignTime() ;
        String lastSignInDate = ElBase.fmtDay(lastSignTime);

        Long now = System.currentTimeMillis() ;
        String today = ElBase.fmtDay(now);

        if (today.equals(lastSignInDate)){ // 今日已经签到
            return JsonUtil.buildError(JsonCodeConsts.error_normal, MsgConsts.ALREAD_SIGN_TODAY);
        }

        ReUser reUser = new ReUser();
        reUser.setUserId(userId);
        reUser.setSignTime(now);
        reUser.setUpdateTime(now);

        ReScoreDetail detail = new ReScoreDetail();
        detail.setUserId(userId);
        detail.setAppId(user.getAppId());
        detail.setScoreType( 1 );
        detail.setScoreTime( ElBase.fmtTime(now) );
        detail.setScoreContent( "签到获取金币" );
        //目前金币
        int score = user.getUserScore();
        //连续签到次数
        int count = user.getSignCount();

        String yesterday = ElBase.fmtDay(now - ValueConsts.dayStamp);

        int getScore ,reward = 0;

        if (yesterday.equals(lastSignInDate)) { // 连续签到

            if (count >= 5) { // 连续签到5次以上

                getScore = 5 ;

                reUser.setUserScore(score + getScore);
                reUser.setSignCount(count + 1);

                detail.setScore(getScore);
                reScoreDetailDAO.insert(detail);

                if ((count+1) % 30 == 0){

                    reward = RandomUtil.getRandomBetweenMaxAndMin(100,10) ;

                    reUser.setUserScore(reUser.getUserScore() + reward);

                    ReScoreDetail rewardScore = new ReScoreDetail();
                    rewardScore.setUserId(userId);
                    rewardScore.setAppId(user.getAppId());
                    rewardScore.setScoreType(1);
                    rewardScore.setScoreTime(ElBase.fmtTime(now));
                    rewardScore.setScoreContent("连续签到30次奖励");
                    rewardScore.setScore(reward);

                    reScoreDetailDAO.insert(rewardScore);

                }
                reUserDAO.updateByPrimaryKeySelective(reUser);

                if ((count+1) % 30 != 0) {
                    return JsonUtil.buildSuccess("签到成功,连续签到"+ ( count + 1 ) + "次获得"+ getScore +"金币");
                }else {
                    return JsonUtil.buildSuccess("签到成功,连续签到"+ ( count + 1 ) + "次获得"+ getScore +"积分,\n连续签到30次,额外奖励"+reward+"金币");
                }


            }else { // 连续签到(还未达到连续5次)

                getScore = count + 1;

                reUser.setUserScore(score + getScore);
                reUser.setSignCount(count + 1);
                reUserDAO.updateByPrimaryKeySelective(reUser);

                detail.setScore(getScore);
                reScoreDetailDAO.insert(detail);

                return JsonUtil.buildSuccess("签到成功,获得"+getScore+"金币");
            }

        }else { // 非连续签到, 则此次签到为 第一次 签到

            reUser.setSignCount(1);
            reUser.setUserScore(score + 1);
            reUserDAO.updateByPrimaryKeySelective(reUser);

            detail.setScore(1);
            reScoreDetailDAO.insert(detail);

            return JsonUtil.buildSuccess("签到成功,获得1金币");

        }

    }
}
