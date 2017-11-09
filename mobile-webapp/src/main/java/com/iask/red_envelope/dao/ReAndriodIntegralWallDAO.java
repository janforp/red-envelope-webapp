package com.iask.red_envelope.dao;

import com.iask.red_envelope.model.ReAndriodIntegralWall;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-12-05
 */
@Repository
public class ReAndriodIntegralWallDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long wallId) {
        ReAndriodIntegralWall _key = new ReAndriodIntegralWall();
        _key.setWallId(wallId);
        return getSqlSession().delete("re_andriod_integral_wall.deleteByPrimaryKey", _key);
    }

    public void insert(ReAndriodIntegralWall record) {
        getSqlSession().insert("re_andriod_integral_wall.insert", record);
    }

    public void insertSelective(ReAndriodIntegralWall record) {
        getSqlSession().insert("re_andriod_integral_wall.insertSelective", record);
    }

    public void insertBatch(List<ReAndriodIntegralWall> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_andriod_integral_wall.insertBatch", records);
    }

    public ReAndriodIntegralWall selectByPrimaryKey(Long wallId) {
        ReAndriodIntegralWall _key = new ReAndriodIntegralWall();
        _key.setWallId(wallId);
        return getSqlSession().selectOne("re_andriod_integral_wall.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(ReAndriodIntegralWall record) {
        return getSqlSession().update("re_andriod_integral_wall.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ReAndriodIntegralWall record) {
        return getSqlSession().update("re_andriod_integral_wall.updateByPrimaryKey", record);
    }

    /**
     * 查询任务信息
     * @param wallId
     * @return
     */
    public ReAndriodIntegralWall selectLockByWallId(Long wallId) {
        return getSqlSession().selectOne("re_andriod_integral_wall.selectLockByWallId", wallId);
    }


}