package com.iask.red_envelope.service.share;

import com.iask.red_envelope.config.Config;
import com.iask.red_envelope.dao.WxShareRedDetailDAO;
import com.iask.red_envelope.dao.WxShareUserDAO;
import com.iask.red_envelope.model.ReleaseRedVo;
import com.iask.red_envelope.model.WxShareRedDetail;
import com.iask.red_envelope.model.WxShareUser;
import com.iask.red_envelope.util.RandomUtil;
import com.wujie.common.sdk.weixin.pay.AutoRetryWeixinPayClient;
import com.wujie.common.sdk.weixin.pay.enums.TransferCheckType;
import com.wujie.common.sdk.weixin.pay.request.mmpaymkttransfers.TransferRequest;
import com.wujie.common.sdk.weixin.pay.response.mmpaymkttransfers.TransferResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jan on 16/10/27.
 * 发放红包的逻辑
 */
@Service
public class ReleaseRedService {

    @Autowired
    private WxShareUserDAO wxShareUserDAO;
    @Autowired
    private WxShareRedDetailDAO wxShareRedDetailDAO;
    @Autowired(required = false)
    private AutoRetryWeixinPayClient autoRetryWeixinPayClient;

    /**
     * 发放红包
     * @param redVo     数据
     * @return
     */
    public void releaseRed(ReleaseRedVo redVo){

        Long userId = redVo.getUserId();
        WxShareUser user = wxShareUserDAO.selectByPrimaryKey(userId);

        if(!Config.isDebug()) { // 非开发模式

            // 微信企业付款

            TransferRequest transferRequest = new TransferRequest();
            transferRequest.setAmount(redVo.getMoney());
            transferRequest.setCheckName(TransferCheckType.NO_CHECK);
            transferRequest.setDesc("红包");
            transferRequest.setSpbillCreateIp("127.0.0.1");
            transferRequest.setPartnerTradeNo(System.currentTimeMillis() + RandomUtil.getRandomNumberString(10));
            transferRequest.setOpenid(user.getOpenId());

            TransferResponse transferResponse = autoRetryWeixinPayClient.execute(transferRequest);

            WxShareRedDetail detail = wxShareRedDetailDAO.selectByPrimaryKey(redVo.getRedId());

            if ("FAIL".equals(transferResponse.getResultCode())){//发放红包失败
                //把红包的flag改为发送失败
                //目的是为了让扫描数据库的线程再次把他拿出来,塞入任务列表,再次给用户发红包
                detail.setFlag(2);
                wxShareRedDetailDAO.updateByPrimaryKeySelective(detail);

            }else {
                //成功,则吧flag改为发送成功
                detail.setFlag(1);
                wxShareRedDetailDAO.updateByPrimaryKeySelective(detail);
            }

        }

    }

}
