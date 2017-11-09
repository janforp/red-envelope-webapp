package com.iask.red_envelope.cache;

import com.iask.red_envelope.consts.CacheConsts;
import com.iask.red_envelope.dao.WxShareUserDAO;
import com.iask.red_envelope.model.WxShareUser;
import com.iask.red_envelope.model.cache.ShareUserInfo;
import com.iask.red_envelope.model.ip.IpData;
import com.iask.red_envelope.util.IpUtil;
import com.iask.red_envelope.util.RequestUtil;
import com.wujie.common.redis.StringKeyRedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Jan on 16/10/27.
 * 分享用户信息
 */
@Service
public class ShareUserInfoCacheService {
    @Autowired
    private StringKeyRedisTemplate cacheRedisTemplate_shareUser ;
    @Autowired
    private WxShareUserDAO wxShareUserDAO;
    /**
     * 获取信息
     * @param request       请求
     * @param shareUserId   用户ID
     * @param missionId     任务ID
     * @return
     */
    public ShareUserInfo getShareInfo(HttpServletRequest request, Long shareUserId,Long missionId) throws IOException {

        String key =  CacheConsts.CACHE_SHARE_USER_ID+shareUserId+CacheConsts.CACHE_SHARE_MISSION_ID+missionId;

        ShareUserInfo userInfo = cacheRedisTemplate_shareUser.getObj(key);

        if (userInfo == null) { //没取到

            userInfo = new ShareUserInfo();

            String ip = wxShareUserDAO.selectByPrimaryKey(shareUserId).getUserIp();

            userInfo.setUserId(shareUserId);
            userInfo.setIp(ip);
            IpData data = IpUtil.getRegionData(ip);
            if (data.getIsInvalidIP()==0){

                userInfo.setProvince(data.getRegion());
                userInfo.setCity(data.getCity());
            }
            //存入
            saveShareInfo(shareUserId,missionId,userInfo);
        }

        return userInfo;

    }
    /**
     * 缓存用户信息
     * @param shareUserId   用户ID
     * @param missionId     任务ID
     */
    public void  saveShareInfo(Long shareUserId,Long missionId,ShareUserInfo userInfo) {

        String key = CacheConsts.CACHE_SHARE_USER_ID+shareUserId+CacheConsts.CACHE_SHARE_MISSION_ID+missionId;

        cacheRedisTemplate_shareUser.setex(key , userInfo , CacheConsts.SECONDS_OF_7000);
    }



}
