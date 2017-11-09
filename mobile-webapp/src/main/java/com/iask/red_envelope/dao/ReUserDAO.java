package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReUser;

import com.iask.red_envelope.model.cache.UserKeySecret;
import com.iask.red_envelope.model.dto.ReUserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-08-15
 */
@Repository
public class ReUserDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer userId) {
        ReUser _key = new ReUser();
        _key.setUserId(userId);
        return getSqlSession().delete("re_user.deleteByPrimaryKey", _key);
    }

    public void insert(ReUser record) {
        getSqlSession().insert("re_user.insert", record);
    }

    public void insertSelective(ReUser record) {
        getSqlSession().insert("re_user.insertSelective", record);
    }

    public void insertBatch(List<ReUser> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_user.insertBatch", records);
    }

    public ReUser selectByPrimaryKey(Integer userId) {
        ReUser _key = new ReUser();
        _key.setUserId(userId);
        return getSqlSession().selectOne("re_user.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReUser record) {
        return getSqlSession().update("re_user.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReUser record) {
        return getSqlSession().update("re_user.updateByPrimaryKey", record);
    }

    /**
     * 通过userKey获取用户秘钥
     * @param userKey
     * @return
     */
    public UserKeySecret selectUserByUserKey(String userKey) {
        return getSqlSession().selectOne("re_user.selectUserByUserKey", userKey);
    }

    /**
     * 查询有效用户
     * @param userId
     * @return
     */
    public ReUser selectByUserIdAndStatus(Integer userId) {
        return getSqlSession().selectOne("re_user.selectByUserIdAndStatus", userId);
    }

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    public ReUserInfo selectUserInfo(Integer userId) {
        return getSqlSession().selectOne("re_user.selectUserInfo", userId);
    }

    /**
     * 查询用户
     * @param userId
     * @return
     */
    public ReUser selectLockByUserId(Integer userId) {
        return getSqlSession().selectOne("re_user.selectLockByUserId", userId);
    }

    /**
     * 通过手机查询用户
     * @param mobile
     * @return
     */
    public ReUser selectUserByMobile(String mobile) {
        return getSqlSession().selectOne("re_user.selectUserByMobile", mobile);
    }

    public ReUser selectByUserKeyAndLock(String userKey){
        return getSqlSession().selectOne("re_user.selectByUserKeyAndLock",userKey);
    }

}