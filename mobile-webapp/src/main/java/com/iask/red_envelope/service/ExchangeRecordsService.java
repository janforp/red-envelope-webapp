package com.iask.red_envelope.service;

import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.consts.ValueConsts;
import com.iask.red_envelope.dao.ReCoinItemDAO;
import com.iask.red_envelope.dao.ReExchangeDetailDAO;
import com.iask.red_envelope.model.ReCoinItem;
import com.iask.red_envelope.model.ReExchangeDetail;
import com.iask.red_envelope.model.ReScoreDetail;
import com.iask.red_envelope.model.vo.ExchangeDetailVo;
import com.iask.red_envelope.util.StringUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jan on 16/10/10.
 * 兑换记录
 */
@Service
public class ExchangeRecordsService {

    @Autowired
    private ReExchangeDetailDAO reExchangeDetailDAO;
    @Autowired
    private ReCoinItemDAO reCoinItemDAO;

    /**
     * 获取个人的金币记录
     * @param
     * @return
     */
    public List<ReExchangeDetail> getExchangeList(HttpServletRequest request, Integer pageNum) {

        int offset = (pageNum -1 ) * ValueConsts.pageSize;
        RowBounds bounds = new RowBounds(offset,ValueConsts.pageSize);

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID) ;

        List<ReExchangeDetail> details = reExchangeDetailDAO.getExchangeList(userId,bounds) ;

        return details;
    }


    /**
     * 个人金币记录数
     * @param request
     * @return
     */
    public Integer getExchangeTotalNum(HttpServletRequest request) {

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

        return reExchangeDetailDAO.getTotalNum(userId);
    }


    /**
     * 获取兑换详情
     * @param id
     * @return
     */
    public ExchangeDetailVo getDetailById(Long id){

        ReExchangeDetail detail = reExchangeDetailDAO.selectByPrimaryKey(id);
        ExchangeDetailVo vo = null ;
        if (detail != null) {
            vo = turnVo(detail);
        }

        return vo;
    }

    /**
     * 实体类转换
     * @param detail
     * @return
     */
    public ExchangeDetailVo turnVo(ReExchangeDetail detail){

        ExchangeDetailVo vo = null ;
        if (detail != null){
            vo = new ExchangeDetailVo();
            Long goodNum = detail.getGoodsNum();
            ReCoinItem item = reCoinItemDAO.selectByPrimaryKey(goodNum);
            if (item != null){

                vo.setDetailId(detail.getId());
                vo.setGoodNum(detail.getGoodsNum());
                vo.setType(item.getItemType());

                vo.setIcon(item.getItemImg());
                String title = item.getItemTitle() ;
                if (StringUtil.isEmpty(title)){
                    title = "";
                }
                vo.setTitle(title);

                String sendTime = detail.getSendTime();
                if (StringUtil.isEmpty(sendTime)){
                    sendTime = "";
                }
                vo.setSendTime(sendTime);

                Integer score = item.getItemCoin();
                if (StringUtil.isEmpty(score)){
                    score = 0;
                }
                vo.setScore(score);

                String cardId = detail.getCardId();
                if (StringUtil.isEmpty(cardId)){
                    cardId = "";
                }
                vo.setCardId(cardId);

                String cardPw = detail.getCardPassword();
                if (StringUtil.isEmpty(cardPw)){
                    cardPw = "";
                }
                vo.setCardPassword(cardPw);

                String expNum = detail.getExpressNumber();
                if (StringUtil.isEmpty(expNum)){
                    expNum = "";
                }
                vo.setExpressNumber(expNum);
                vo.setStatus(detail.getExchangeStatus());

                String invalidTime = detail.getInvalidTime();
                if (StringUtil.isEmpty(invalidTime)){
                    invalidTime="";
                }
                vo.setInvalidTime(invalidTime);
            }
        }
        return vo;
    }
}
