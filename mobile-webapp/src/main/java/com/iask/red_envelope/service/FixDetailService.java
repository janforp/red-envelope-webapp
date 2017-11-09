package com.iask.red_envelope.service;

import com.iask.red_envelope.consts.ValueConsts;
import com.iask.red_envelope.dao.ReFixedRedDAO;
import com.iask.red_envelope.dao.ReReceiveDetailDAO;
import com.iask.red_envelope.dao.ReUserDAO;
import com.iask.red_envelope.model.ReFixedRed;
import com.iask.red_envelope.model.ReReceiveDetail;
import com.iask.red_envelope.model.ReUser;
import com.iask.red_envelope.model.vo.DetailSeparateVo;
import com.iask.red_envelope.model.vo.FixDetailListVo;
import com.iask.red_envelope.model.vo.GrabFixRedVo;
import com.iask.red_envelope.util.StringUtil;
import com.iask.red_envelope.util.el.ElBase;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jan on 16/8/26.
 * 定时红包领取详情
 */
@Service
public class FixDetailService {

    @Autowired
    private ReReceiveDetailDAO reReceiveDetailDAO;
    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReFixedRedDAO reFixedRedDAO;

    /**
     * 获取红包信息
     * @param redId
     * @return
     */
    public ReFixedRed getFixRedById(Integer redId) {
        return reFixedRedDAO.selectByPrimaryKey(redId);
    }

    /**
     * 分页查询定时红包详情
     * @param redId
     * @param pageNum
     * @return
     */
    public List<FixDetailListVo> getAllDetailByRedId(Integer redId, Integer pageNum){

        String today = ElBase.fmtDay(System.currentTimeMillis());

        int offset = (pageNum - 1) * ValueConsts.pageSize1;
        RowBounds bounds = new RowBounds(offset, ValueConsts.pageSize1);

        List<ReReceiveDetail> details = reReceiveDetailDAO.getAllDetailOrderByTimeAndStatus(redId, today, bounds);

        return turnList(details);
    }

    /**
     * 获取总数
     * @param redId
     * @return
     */
    public Integer getTotalNumByRedId(Integer redId) {
        String today = ElBase.fmtDay(System.currentTimeMillis());
        return reReceiveDetailDAO.getTotalNumByRedId(redId, today);
    }

    /**
     * 获取 金额前三名
     * @return
     */
    public List<FixDetailListVo> getTop3Detail(Integer redId) {

        String today = ElBase.fmtDay(System.currentTimeMillis());
        List<ReReceiveDetail> details = reReceiveDetailDAO.getTop3Detail(redId, today);
        List<FixDetailListVo> top3 = turnList(details);

        if (isOver(redId)){ // 已经结束
            for (int i = 0; i < 3; i++) {
                if (i == 0) {
                    top3.get(i).setMoney("奖励1");
                }else if (i == 1) {
                    top3.get(i).setMoney("奖励0.6");
                }else if (i == 2) {
                    top3.get(i).setMoney("奖励0.3");
                }
            }
        }

        return top3;

    }


    /**
     *
     * 定时红包领取详情 分隔数据:
     *
     * 1000个红包, 4秒抢光!
     *
     * 1000个红包,剩余不足600个!
     */
    public DetailSeparateVo getSeparateVo(Integer redId) throws Exception {

        DetailSeparateVo vo = new DetailSeparateVo();

        ReFixedRed fixRed = getFixRedById(redId);
        int status = 1;
        int remainder = fixRed.getFixedRemainder();
        if (remainder == 0) {

            status = 0;

            // 耗时
            long time = getConsumeTimeOfOneFixRed(redId);
            vo.setTime(time);

        }
        int amount = fixRed.getFixedAmount();

        vo.setAmount(amount);
        vo.setRemaind(remainder);
        vo.setStatus(status);

        return vo;

    }

    /**
     * 获取某个定时红包的 耗时(s)
     * @param redId
     * @return
     */
    public long getConsumeTimeOfOneFixRed(Integer redId) {

        String today = ElBase.fmtDay(System.currentTimeMillis());

        // 第一条记录
        ReReceiveDetail detailFirst = reReceiveDetailDAO.getRedDetailByStatus(redId, today, 0);
        long first = ElBase.get13Timestamp(detailFirst.getDetailTime());

        // 最后一条记录
        ReReceiveDetail detailLast = reReceiveDetailDAO.getRedDetailByStatus(redId, today, 1);
        long last = ElBase.get13Timestamp(detailLast.getDetailTime());

        // 秒
        long consumeTime = (last - first) / 1000;

        if (consumeTime == 0) {
            consumeTime = 1;
        }

        return consumeTime;

    }

    /**
     * 判断此定时红包是否结束
     * @param redId
     * @return
     */
    public boolean isOver(Integer redId) {
        Integer remainder = reFixedRedDAO.remainder(redId);
        if (remainder == 0) {
            return true;
        }
        return false;
    }

    /**
     * 转换
     * @param detail
     * @return
     */
    public FixDetailListVo turnReReceiveDetailToFixDetailListVo(ReReceiveDetail detail) {

        FixDetailListVo vo = new FixDetailListVo();

        Integer userId = detail.getUserId();
        ReUser user = reUserDAO.selectByPrimaryKey(userId);

        String icon = user.getPortrait();
        vo.setIcon(icon);

        String nickname = user.getNickname();
        if (!StringUtil.isEmpty(nickname)) {
            // 判断中文
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
        }else {
            nickname = "";
        }
        vo.setNickname(nickname);

        BigDecimal money = detail.getRedMoney();
        vo.setMoney(money.toString());

        String time = detail.getDetailTime();
        time = time.substring(11,16);
        vo.setTime(time);

        return vo;
    }

    /**
     * 实体类集合转换
     * @param details
     * @return
     */
    public List<FixDetailListVo> turnList(List<ReReceiveDetail> details) {

        List<FixDetailListVo> vos = new ArrayList<>(details.size());

        for (ReReceiveDetail detail : details) {
            FixDetailListVo vo = turnReReceiveDetailToFixDetailListVo(detail) ;
            vos.add(vo);
        }

        return vos;
    }

    /**
     * 是否抢到,以及金额
     * @param userId
     * @param redId
     * @return
     */
    public GrabFixRedVo getGrabVo(Integer userId,Integer redId){

        String today = ElBase.fmtDay(System.currentTimeMillis());

        ReReceiveDetail detail = reReceiveDetailDAO.getDetailByUserIdOfNow(userId,redId,today);

        GrabFixRedVo vo = new GrabFixRedVo();

        if (detail == null) {

            vo.setStatus(0);
        }else {

            vo.setStatus(1);
            vo.setMoney(detail.getRedMoney());
        }

        return vo;
    }

}
