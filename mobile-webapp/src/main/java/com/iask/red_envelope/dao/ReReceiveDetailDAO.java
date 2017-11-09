package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReReceiveDetail;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-08-26
 */
@Repository
public class ReReceiveDetailDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long detailId) {
        ReReceiveDetail _key = new ReReceiveDetail();
        _key.setDetailId(detailId);
        return getSqlSession().delete("re_receive_detail.deleteByPrimaryKey", _key);
    }

    public void insert(ReReceiveDetail record) {
        getSqlSession().insert("re_receive_detail.insert", record);
    }

    public void insertSelective(ReReceiveDetail record) {
        getSqlSession().insert("re_receive_detail.insertSelective", record);
    }

    public void insertBatch(List<ReReceiveDetail> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_receive_detail.insertBatch", records);
    }

    public ReReceiveDetail selectByPrimaryKey(Long detailId) {
        ReReceiveDetail _key = new ReReceiveDetail();
        _key.setDetailId(detailId);
        return getSqlSession().selectOne("re_receive_detail.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReReceiveDetail record) {
        return getSqlSession().update("re_receive_detail.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReReceiveDetail record) {
        return getSqlSession().update("re_receive_detail.updateByPrimaryKey", record);
    }

    /**
     * 领取详情列表
     *
     * 该红包必须是已经开始抢了(不管是否抢光,都能看手气)
     * @param redId
     * @return
     */
    public List<ReReceiveDetail> getAllDetailOrderByTimeAndStatus(Integer redId, String today, RowBounds bounds) {
        Map<String,Object> param = new HashMap<>(2);
        param.put("redId", redId);
        param.put("today", today);
        return getSqlSession().selectList("re_receive_detail.getAllDetailOrderByTimeAndStatus", param, bounds);
    }

    /**
     * 总数
     * @param redId
     * @param today
     * @return
     */
    public Integer getTotalNumByRedId(Integer redId, String today) {
        Map<String,Object> param = new HashMap<>(2);
        param.put("redId", redId);
        param.put("today", today);
        return getSqlSession().selectOne("re_receive_detail.getTotalNumByRedId", param);
    }

    /**
     * 获取前三名
     * @param today
     * @return
     */
    public List<ReReceiveDetail> getTop3Detail(Integer redId, String today) {
        Map<String,Object> param = new HashMap<>(2);
        param.put("redId", redId);
        param.put("today", today);
        return getSqlSession().selectList("re_receive_detail.getTop3Detail", param);
    }

    /**
     * 查询领取记录
     * @param redId
     * @param today
     * @param status 0:则获取第一个被抢到的红包   1:则获取最后一个被抢到的红包
     * @return
     */
    public ReReceiveDetail getRedDetailByStatus(Integer redId, String today, int status) {
        Map<String, Object> param = new HashMap<>(3);
        param.put("redId", redId);
        param.put("today", today);
        param.put("status", status);
        return getSqlSession().selectOne("re_receive_detail.getRedDetailByStatus", param);
    }

    /**
     * 由兑换码红包id及用户id
     * 查询该用户是否重复领了
     * 一个用户只能在一个兑换码红包中领取一次
     * @param codeId
     * @param userId
     * @return
     */
    public ReReceiveDetail selectByCodeIdAndUserId(Integer codeId, Integer userId) {
        Map<String,Object> param = new HashMap<>(2);
        param.put("codeId", codeId);
        param.put("userId", userId);
        return getSqlSession().selectOne("re_receive_detail.selectByCodeIdAndUserId", param);
    }

    /**
     * 查看此人今天是否抢过此红包
     * @param userId
     * @param redId
     * @param today
     * @return
     */
    public ReReceiveDetail getDetailByUserIdOfNow(Integer userId,Integer redId,String today){

        Map<String,Object> param = new HashMap<>(3);
        param.put("redId", redId);
        param.put("userId", userId);
        param.put("today", today);
        return getSqlSession().selectOne("re_receive_detail.getDetailByUserIdOfNow", param);
    }
}