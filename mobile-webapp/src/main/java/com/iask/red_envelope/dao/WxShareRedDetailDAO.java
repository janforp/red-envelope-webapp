package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.WxShareRedDetail;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-10-27
 */
@Repository
public class WxShareRedDetailDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long redId) {
        WxShareRedDetail _key = new WxShareRedDetail();
        _key.setRedId(redId);
        return getSqlSession().delete("wx_share_red_detail.deleteByPrimaryKey", _key);
    }

    public void insert(WxShareRedDetail record) {
        getSqlSession().insert("wx_share_red_detail.insert", record);
    }

    public void insertSelective(WxShareRedDetail record) {
        getSqlSession().insert("wx_share_red_detail.insertSelective", record);
    }

    public void insertBatch(List<WxShareRedDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("wx_share_red_detail.insertBatch", records);
    }

    public WxShareRedDetail selectByPrimaryKey(Long redId) {
        WxShareRedDetail _key = new WxShareRedDetail();
        _key.setRedId(redId);
        return getSqlSession().selectOne("wx_share_red_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(WxShareRedDetail record) {
        return getSqlSession().update("wx_share_red_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(WxShareRedDetail record) {
        return getSqlSession().update("wx_share_red_detail.updateByPrimaryKey", record);
    }

    /**
     * 用户中奖之后随机获取一个红包
     * @param missionId
     * @return
     */
    public WxShareRedDetail randomGetRedAndLockByMissionId(Long missionId){

        return getSqlSession().selectOne("wx_share_red_detail.randomGetRedAndLockByMissionId",missionId);
    }

    /**
     * 扫描发送红包失败的数据,再次发送
     * @return
     */
    public List<WxShareRedDetail> getReleaseFailRedDetail(){

        return getSqlSession().selectList("wx_share_red_detail.getReleaseFailRedDetail");
    }
}