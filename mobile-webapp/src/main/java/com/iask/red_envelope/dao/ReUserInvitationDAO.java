package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReUserInvitation;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-09-21
 */
@Repository
public class ReUserInvitationDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long id) {
        ReUserInvitation _key = new ReUserInvitation();
        _key.setId(id);
        return getSqlSession().delete("re_user_invitation.deleteByPrimaryKey", _key);
    }

    public void insert(ReUserInvitation record) {
        getSqlSession().insert("re_user_invitation.insert", record);
    }

    public void insertSelective(ReUserInvitation record) {
        getSqlSession().insert("re_user_invitation.insertSelective", record);
    }

    public void insertBatch(List<ReUserInvitation> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_user_invitation.insertBatch", records);
    }

    public ReUserInvitation selectByPrimaryKey(Long id) {
        ReUserInvitation _key = new ReUserInvitation();
        _key.setId(id);
        return getSqlSession().selectOne("re_user_invitation.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReUserInvitation record) {
        return getSqlSession().update("re_user_invitation.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReUserInvitation record) {
        return getSqlSession().update("re_user_invitation.updateByPrimaryKey", record);
    }
    /**
     * 分页查询详情
     * @param userId
     * @param bounds
     * @return
     */
    public List<ReUserInvitation> getInvitationList(Integer userId, RowBounds bounds) {

        return getSqlSession().selectList("re_user_invitation.getInvitationList" , userId , bounds);
    }

    /**
     * 获取用户的详情记录数量
     * @param userId
     * @return
     */
    public Integer getInvitationNumByUserId(Integer userId) {

        return getSqlSession().selectOne("re_user_invitation.getInvitationNumByUserId" , userId);
    }
}