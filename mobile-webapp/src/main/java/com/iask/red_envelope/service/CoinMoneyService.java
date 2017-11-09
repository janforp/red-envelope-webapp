package com.iask.red_envelope.service;

import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.consts.ValueConsts;
import com.iask.red_envelope.dao.ReAccountDetailDAO;
import com.iask.red_envelope.dao.ReScoreDetailDAO;
import com.iask.red_envelope.dao.ReUserDAO;
import com.iask.red_envelope.model.ReAccountDetail;
import com.iask.red_envelope.model.ReScoreDetail;
import com.iask.red_envelope.util.StringUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jan on 16/9/12.
 * 金币及余额
 */
@Service
public class CoinMoneyService {

    @Autowired
    private ReScoreDetailDAO reScoreDetailDAO ;
    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;

    /**
     * 获取个人的金币记录
     * @param
     * @return
     */
    public List<ReScoreDetail> getScoreList(HttpServletRequest request,Integer pageNum) {

        int offset = (pageNum -1 ) * ValueConsts.pageSize;
        RowBounds bounds = new RowBounds(offset,ValueConsts.pageSize);

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID) ;

        List<ReScoreDetail> details = reScoreDetailDAO.getScoreList(userId,bounds) ;

        for (ReScoreDetail detail : details) {

            if (detail.getScoreContent() == null || detail.getScoreContent().length() == 0) {

                detail.setScoreContent("金币");
            }
        }

        return details;
    }

    /**
     * 获取个人的金币数
     * @param request
     * @return
     */
    public Integer getMyCoin(HttpServletRequest request) {

        return reUserDAO.selectByPrimaryKey((Integer) request.getAttribute(RequestConsts.ATTR_USER_ID)).getUserScore();
    }

    /**
     * 个人金币记录数
     * @param request
     * @return
     */
    public Integer getScoreTotalNum(HttpServletRequest request) {

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        return reScoreDetailDAO.getTotalNum(userId);
    }

    /**
     * 获取个人的余额记录
     * @param
     * @return
     */
    public List<ReAccountDetail> getMoneyList(HttpServletRequest request, Integer pageNum) {

        int offset = (pageNum -1 ) * ValueConsts.pageSize;

        RowBounds bounds = new RowBounds(offset,ValueConsts.pageSize);

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID) ;

        List<ReAccountDetail> details = reAccountDetailDAO.getMoneyList(userId,bounds) ;

        for (ReAccountDetail detail : details) {

            String content = detail.getDetailContent();

            if (content == null || content.length() == 0) {

                detail.setDetailContent("余额");
            }

            if (!StringUtil.isEmpty(content)) {
                // 判断中文
                String regEx = "[\u4e00-\u9fa5]";
                Pattern pat = Pattern.compile(regEx);
                Matcher matcher = pat.matcher(content);
                if(matcher.find()) { // 含中文
                    if (content.length() >= 8) {
                        content = content.substring(0, 6)+"...";
                    }
                }
            }
            detail.setDetailContent(content);


        }

        return reAccountDetailDAO.getMoneyList(userId,bounds);
    }

    /**
     * 个人金额
     * @param request
     * @return
     */
    public BigDecimal getMyMoney(HttpServletRequest request) {

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        return reUserDAO.selectByPrimaryKey(userId).getUserMoney();
    }
    /**
     * 个人金金额记录数
     * @param request
     * @return
     */
    public Integer getMoneyListNum(HttpServletRequest request) {

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        return reAccountDetailDAO.getTotalNum(userId);
    }

}
