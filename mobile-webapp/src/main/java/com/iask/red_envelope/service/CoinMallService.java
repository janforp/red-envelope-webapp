package com.iask.red_envelope.service;

import com.iask.red_envelope.consts.JsonCodeConsts;
import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.dao.*;
import com.iask.red_envelope.model.*;
import com.hongbao.api.model.cache.UserInfo;
import com.iask.red_envelope.model.dto.ReUserInfo;
import com.iask.red_envelope.model.dto.WxNotShareSign;
import com.iask.red_envelope.model.vo.ItemVo;
import com.iask.red_envelope.service.sign.SignService;
import com.iask.red_envelope.service.user.UserCacheService;
import com.iask.red_envelope.service.user.UserService;
import com.iask.red_envelope.util.CommonMethod;
import com.iask.red_envelope.util.JsonUtil;
import com.iask.red_envelope.util.el.ElBase;
import com.wujie.common.beanutil.BeanUtils;
import com.wujie.common.sdk.weixin.oauth2.domain.UserInfoDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by Jan on 16/8/22.
 *
 * 金币商城
 */
@Service
public class CoinMallService {

    @Autowired
    private ReUserDAO reUserDAO;
    @Autowired
    private ReScoreDetailDAO reScoreDetailDAO;
    @Autowired
    private ReExchangeDetailDAO reExchangeDetailDAO;
    @Autowired
    private ReWithdrawBindDAO reWithdrawBindDAO;
    @Autowired
    private AddressService addressService ;
    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private SignService signService;
    @Autowired
    private WxNotShareService wxNotShareService;
    @Autowired
    private UserCacheService userCacheService;
    @Autowired
    private ReCoinItemDAO reCoinItemDAO;

    /**
     * 获取金币数
     * @param request
     * @return
     */
    public Integer getScore(HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        return reUserDAO.selectByPrimaryKey(userId).getUserScore() ;
    }

    /**
     * 根据id获取商品
     * @param itemId
     * @return
     */
    public ItemVo getItemById(HttpServletRequest request ,Long itemId){

        ReCoinItem reCoinItem = reCoinItemDAO.selectByPrimaryKey(itemId);

        Boolean isLogin = userService.isLogin(request);

        Integer userScore = null;
        if (isLogin){
            userScore = getScore(request);
        }


        ItemVo vo = null ;
        if (reCoinItem != null) {
            vo = new ItemVo();
            vo.setItemId(reCoinItem.getItemId());
            vo.setItemCoin(reCoinItem.getItemCoin());
            vo.setItemDesc(reCoinItem.getItemDesc());
            vo.setItemImg(reCoinItem.getItemImg());
            vo.setItemTitle(reCoinItem.getItemTitle());
            vo.setType(reCoinItem.getItemType());

            if (userScore != null){

                if (userScore >= reCoinItem.getItemCoin()){
                    vo.setFlag(true);
                }else {
                    vo.setFlag(false);
                }
            }

        }
        return vo;
    }

    /**
     * 金币商城兑换
     * @param request
     * @param score
     * @param goodsNum
     * @return
     */
    public String doExchange(HttpServletRequest request,Integer score,Long goodsNum,String goodsName) {

        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);
        if (userId == null) {
            return JsonUtil.buildError(JsonCodeConsts.error_normal,"请先登录");
        }

        ReUser user = reUserDAO.selectLockByUserId(userId);
        Integer userScore = user.getUserScore() ;
        if (userScore < score) {
            return JsonUtil.buildError(JsonCodeConsts.error_normal,"金币不足");
        }

        ReAddress address = addressService.getAddress(request) ;
        if (StringUtils.isEmpty(address)) {
            return JsonUtil.buildError(JsonCodeConsts.not_have_address ,"无收货地址" );
        }

        //可以兑换
        user.setUserScore(userScore - score);
        reUserDAO.updateByPrimaryKeySelective(user);

        ReScoreDetail scoreDetail = new ReScoreDetail();
        scoreDetail.setUserId(userId);
        scoreDetail.setAppId(user.getAppId());
        scoreDetail.setScore(score);
        scoreDetail.setScoreType(0);
        scoreDetail.setScoreContent("兑换商品");
        scoreDetail.setScoreTime(ElBase.fmtTime(System.currentTimeMillis()));
        reScoreDetailDAO.insert(scoreDetail);

        ReExchangeDetail exchangeDetail = new ReExchangeDetail();
        exchangeDetail.setUserId(userId);
        exchangeDetail.setGoodsNum(goodsNum);
        exchangeDetail.setGoodsName(goodsName);
        exchangeDetail.setExchangeStatus(0);
        exchangeDetail.setExchangeTime(ElBase.fmtTime(System.currentTimeMillis()));
        exchangeDetail.setScore(score);
        reExchangeDetailDAO.insertSelective(exchangeDetail);

        return JsonUtil.buildSuccess("兑换成功");
    }

    /**
     * 在微信中打开金币商城
     * @param request
     * @param response
     * @param codeId
     * @param userInfoDomain
     * @return
     */
    public String wxCoin(HttpServletRequest request, HttpServletResponse response, Integer codeId, UserInfoDomain userInfoDomain) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        String openId = userInfoDomain.getOpenid();
        String nickName = userInfoDomain.getNickname();

        WxNotShareSign share = wxNotShareService.getSign(request);
        Boolean isIos = CommonMethod.isAppleBrower(request);

        request.setAttribute("isWx", true);
        request.setAttribute("isIos", isIos);
        request.setAttribute("codeId", codeId);
        request.setAttribute("share", share);
        request.setAttribute("openId", openId);
        request.setAttribute("nickName", nickName);

        //虚拟商品
        List<ReCoinItem> items = getCoinItemList(1);
        request.setAttribute("items",items);
        //实体商品带过去
        List<ReCoinItem> realities = getCoinItemList(0);
        request.setAttribute("real",realities);

        // 判断是否已经绑定
        ReWithdrawBind bind = reWithdrawBindDAO.selectByOpen(openId);

        if(bind != null) { // 已绑定 自动登录

            Integer userId = bind.getUserId();
            ReUser reUser = reUserDAO.selectByPrimaryKey(userId);

            //登录成功之后的操作:保持会话状态
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(reUser,userInfo);

            userCacheService.updateUser(userInfo);
            loginService.buildLoginResult(request, response, reUser);

            Boolean isSign = signService.isSignToday(userId);
            ReUserInfo user = userService.getUserInfoById(userId);

            request.setAttribute("isLogin", true);
            request.setAttribute("isSign", isSign);
            request.setAttribute("user", user);

            return "coin_mall";

        }

        request.setAttribute("isLogin", false);

        return "coin_mall";

    }

    /**
     * 金币商城虚拟商品(动态的)
     * @return
     */
    public List<ReCoinItem> getCoinItemList(Integer type){

        return reCoinItemDAO.getListByType(type);
    }


}
