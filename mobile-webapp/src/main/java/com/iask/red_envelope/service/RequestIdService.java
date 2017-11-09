package com.iask.red_envelope.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.iask.red_envelope.config.Config;
import com.iask.red_envelope.consts.BaseConsts;
import com.iask.red_envelope.consts.CacheConsts;
import com.iask.red_envelope.dao.ReCustomerDAO;
import com.iask.red_envelope.model.ReCustomer;
import com.iask.red_envelope.model.vo.CustomerCacheVo;
import com.iask.red_envelope.util.MD5Encryption;
import com.wujie.common.beanutil.BeanUtils;
import com.wujie.common.redis.StringKeyRedisTemplate;
import org.craigq.common.logger.LogMgr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * Created by Summer on 16/6/28.
 */
@Service
public class RequestIdService {

    @Autowired
    private StringKeyRedisTemplate requestIdCacheRedisTemplate;

    @Autowired
    private ReCustomerDAO reCustomerDAO;

    private byte[] toJSONStringBytes(Object object) throws UnsupportedEncodingException {
        return JSON.toJSONString(object, SerializerFeature.DisableCircularReferenceDetect).getBytes(BaseConsts.CHARSET);
    }

    private byte[] toBytes(String string) throws UnsupportedEncodingException {
        return string.getBytes(BaseConsts.CHARSET);
    }

    public CustomerCacheVo getCustomerByUserKey(String userKey) {
        if (userKey == null || userKey.length() == 0) {
            return null;
        }
        String key = CacheConsts.CustomerCacheVo + userKey;
        CustomerCacheVo vo = null;
        String json = null;
        try {
            json = requestIdCacheRedisTemplate.get(key);
        } catch (Exception e) {
            LogMgr.getLogger().error("RequestIdService.getCustomerByUserKey", e);
        }
        if (json != null) {
            // 缓存未过期
            try {
                vo = JSON.parseObject(json, CustomerCacheVo.class);
            } catch (Exception e) {
                LogMgr.getLogger().error("RequestIdService.getCustomerByUserKey", e);
            }
        }
        if (vo == null) {
            ReCustomer reCustomer = reCustomerDAO.selectByPrimaryKey(Integer.valueOf(userKey));
            if (reCustomer != null) {
                vo = new CustomerCacheVo();
                BeanUtils.copyProperties(reCustomer, vo);
                try {
                    requestIdCacheRedisTemplate.setex(key, toJSONStringBytes(vo), CacheConsts.SECONDS_OF_TEN_MINUTE);
                } catch (Exception e) {
                    LogMgr.getLogger().error("RequestIdService.getCustomerByUserKey", e);
                }
            }
        }
        return vo;
    }

    /**
     * 从缓存查询一个客户下的一个reqId是否存在
     *
     * @param userId
     * @param reqId
     * @return
     */
    public boolean isReqIdExists(Integer userId, String reqId) {
        if (userId == null || reqId == null || reqId.length() == 0) {
            return false;
        }
        String key = new StringBuilder(40).append(CacheConsts.userId_reqId).append(userId).append("_").append(reqId).toString();
        String strValue = null;
        try {
            strValue = requestIdCacheRedisTemplate.get(key);
        } catch (Exception e) {
            LogMgr.getLogger().error("RequestIdService.isReqIdExists", e);
        }
        return strValue != null && strValue.length() > 0;
    }

    /**
     * 设置一个客户下的一个reqId到缓存
     *
     * @param userId
     * @param reqId
     */
    public void setReqIdCache(Integer userId, String reqId) {
        String key = new StringBuilder(40).append(CacheConsts.userId_reqId).append(userId).append("_").append(reqId).toString();
        try {
            long cacheSeconds = 60; // 默认缓存60秒，否则按照securityTimeDeviation配置
            // 请求中的时间戳与服务器时间允许的最大误差（单位秒），小于等于0：表示不验证时间戳
            long timeDeviation = Config.getSecurityTimeDeviation();// reqId的缓存时间就设置为允许的时间误差的长度
            if (timeDeviation > 0) {
                cacheSeconds = timeDeviation;
            }
            requestIdCacheRedisTemplate.setex(key, "", cacheSeconds);
        } catch (Exception e) {
            LogMgr.getLogger().error("RequestIdService.setReqIdCache", e);
        }
    }

}
