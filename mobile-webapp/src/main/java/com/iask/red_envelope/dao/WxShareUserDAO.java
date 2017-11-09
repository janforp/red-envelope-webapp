package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.WxShareUser;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-10-26
 */
@Repository
public class WxShareUserDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long userId) {
        WxShareUser _key = new WxShareUser();
        _key.setUserId(userId);
        return getSqlSession().delete("wx_share_user.deleteByPrimaryKey", _key);
    }

    public void insert(WxShareUser record) {
        getSqlSession().insert("wx_share_user.insert", record);
    }

    public void insertSelective(WxShareUser record) {
        getSqlSession().insert("wx_share_user.insertSelective", record);
    }

    public void insertBatch(List<WxShareUser> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("wx_share_user.insertBatch", records);
    }

    public WxShareUser selectByPrimaryKey(Long userId) {
        WxShareUser _key = new WxShareUser();
        _key.setUserId(userId);
        return getSqlSession().selectOne("wx_share_user.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(WxShareUser record) {
        return getSqlSession().update("wx_share_user.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(WxShareUser record) {
        return getSqlSession().update("wx_share_user.updateByPrimaryKey", record);
    }

    /**
     * 根据openId查用户
     * @param openId
     * @return
     */
    public WxShareUser getUserByOpenId(String openId){

        return getSqlSession().selectOne("wx_share_user.getUserByOpenId",openId);
    }
}