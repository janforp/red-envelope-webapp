package com.iask.red_envelope.util.support;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.iask.red_envelope.consts.BaseConsts;
import com.iask.red_envelope.consts.CacheConsts;
import com.wujie.common.redis.StringKeyRedisTemplate;
import com.wujie.common.sdk.weixin.official_account.DefaultWeixinClient;
import com.wujie.common.sdk.weixin.official_account.domain.token.TokenDomain;
import com.wujie.common.sdk.weixin.official_account.external.AccessTokenKeeper;
import com.wujie.common.sdk.weixin.official_account.request.token.TokenRequest;
import com.wujie.common.sdk.weixin.official_account.response.token.TokenResponse;
import org.craigq.common.logger.LogMgr;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by wuqiang on 16-2-29.
 *
 * @author wuqiang
 */
public class WeixinOfficialAccountAccessTokenKeeper implements AccessTokenKeeper {
    // 提前多少毫秒失效
    private static final long expireAdvanceTimeMills = 120000L;
    private final StringKeyRedisTemplate cacheRedisTemplate;
    // appid - DefaultWeixinClient  即：授权这个AccessTokenKeeper管理哪些appid的accessToken
    private final Map<String, DefaultWeixinClient> appidDefaultWeixinClientMap;
    private final CacheHashMap<String, WeixinOfficialAccountAccessTokenVo> cacheMap;

    public WeixinOfficialAccountAccessTokenKeeper(StringKeyRedisTemplate cacheRedisTemplate, Map<String, String> appIdAppSecretMap) {
        this.cacheRedisTemplate = cacheRedisTemplate;
        if (appIdAppSecretMap == null || appIdAppSecretMap.isEmpty()) {
            throw new IllegalArgumentException("appidAppSecretMap must not be null or empty.");
        }
        this.cacheMap = new CacheHashMap<>(appIdAppSecretMap.size() * 2);
        this.appidDefaultWeixinClientMap = new HashMap<>(appIdAppSecretMap.size());
        Set<String> appidSet = appIdAppSecretMap.keySet();
        for (String appid : appidSet) {
            String appSecret = appIdAppSecretMap.get(appid);
            DefaultWeixinClient weixinClient = new DefaultWeixinClient(appid, appSecret);
            appidDefaultWeixinClientMap.put(appid, weixinClient);
            this.getWeixinOfficialAccountAccessTokenVoCache(appid);// 把redis缓存中的token尝试初始化到本地缓存Map
        }
    }

    public WeixinOfficialAccountAccessTokenVo getWeixinOfficialAccountAccessTokenVoCache(String appid) {
        WeixinOfficialAccountAccessTokenVo vo = cacheMap.get(appid); // 先从本地缓存的Map中查找
        if (vo != null) {
            if (vo.getExpireTime() == null || vo.getExpireTime() <= (System.currentTimeMillis() + expireAdvanceTimeMills)) {
                // 提前2分钟失效
                cacheMap.put(appid, null);
                vo = null;
            } else {
                // 本地缓存map中就有，且没过期，直接返回
                return vo;
            }
        }
        String key = CacheConsts.weixinOfficialAccountAccessToken + appid;
        String json = null;
        try {
            json = cacheRedisTemplate.get(key);
        } catch (Exception e) {
            LogMgr.getLogger().error("WeixinOfficialAccountAccessTokenKeeper.getWeixinOfficialAccountAccessTokenVoCache", e);
        }
        if (json != null) {
            // 缓存未过期
            try {
                JSONObject jsonObject = JSON.parseObject(json);
                String appidFromJson = jsonObject.getString("appid");
                if (appidFromJson != null && appidFromJson.equals(appid)) {
                    String accessToken = jsonObject.getString("accessToken");
                    // 过期的具体时间点
                    Long expireTime = jsonObject.getLong("expireTime");
                    vo = new WeixinOfficialAccountAccessTokenVo(appidFromJson, accessToken, expireTime);
                    if (vo.getExpireTime() == null || vo.getExpireTime() <= (System.currentTimeMillis() + expireAdvanceTimeMills)) {
                        // 提前2分钟失效
                        vo = null;
                    } else {
                        // 更新到本地缓存Map
                        this.cacheMap.put(appid, vo);
                    }
                }
            } catch (Exception e) {
                LogMgr.getLogger().error("WeixinOfficialAccountAccessTokenKeeper.getWeixinOfficialAccountAccessTokenVoCache", e);
            }
        }
        if (vo == null) {
            vo = this.initAndUpdateToCache(this.appidDefaultWeixinClientMap.get(appid));
        }
        return vo;
    }

    private WeixinOfficialAccountAccessTokenVo initAndUpdateToCache(DefaultWeixinClient weixinClient) {
        if (weixinClient == null) {
            return null;
        }
        WeixinOfficialAccountAccessTokenVo vo = null;
        try {
            String appid = weixinClient.getAppId();
            String key = CacheConsts.weixinOfficialAccountAccessToken + appid;
            TokenRequest request = new TokenRequest(weixinClient.getAppId(), weixinClient.getAppSecret());
            TokenResponse response = weixinClient.execute(request);
            TokenDomain tokenDomain = response.getToken();
            if (tokenDomain != null) {
                Long expireTime = System.currentTimeMillis() + (tokenDomain.getExpires_in() * 1000);
                vo = new WeixinOfficialAccountAccessTokenVo(appid, tokenDomain.getAccess_token(), expireTime);
                this.cacheMap.put(appid, vo);
                long cacheSeconds = tokenDomain.getExpires_in() - (expireAdvanceTimeMills / 1000);// 提前2分钟过期
                cacheRedisTemplate.setex(key, JSON.toJSONString(vo, SerializerFeature.DisableCircularReferenceDetect).getBytes(BaseConsts.CHARSET), cacheSeconds);
            }
        } catch (Exception e) {
            LogMgr.getLogger().error("WeixinOfficialAccountAccessTokenKeeper.initAndUpdateToCache", e);
        }
        return vo;
    }

    @Override
    public String getLatestAccessToken(String appid) {
        WeixinOfficialAccountAccessTokenVo vo = this.getWeixinOfficialAccountAccessTokenVoCache(appid);
        return vo != null ? vo.getAccessToken() : null;
    }

    @Override
    public String refreshAccessToken(String appid) {
        WeixinOfficialAccountAccessTokenVo vo = this.initAndUpdateToCache(this.appidDefaultWeixinClientMap.get(appid));
        return vo != null ? vo.getAccessToken() : null;
    }
}
