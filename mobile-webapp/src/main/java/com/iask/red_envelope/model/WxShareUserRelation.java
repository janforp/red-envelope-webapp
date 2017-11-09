package com.iask.red_envelope.model;

/**
 * Created by com.iask.red_envelope.MybatisCodeGenerate on 2016-10-26
 */
public class WxShareUserRelation implements java.io.Serializable {

    // Fields

    // 用户id
    private Long userId;
    // 任务id
    private Long missionId;
    // 中奖次数
    private Integer prizeTimes;
    // 抽奖次数
    private Integer lotteryTimes;
    // 更新时间,如:2016-08-18 12:53:30
    private String updateTime;
    // 创建时间,如:2016-08-18 12:53:30
    private String createTime;

    // Constructors

    /**
     * default constructor
     */
    public WxShareUserRelation() {
    }

    /**
     * full constructor
     */
    public WxShareUserRelation(Long userId, Long missionId, Integer prizeTimes, Integer lotteryTimes, String updateTime, String createTime) {
        this.userId = userId;
        this.missionId = missionId;
        this.prizeTimes = prizeTimes;
        this.lotteryTimes = lotteryTimes;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    // Property accessors

    /**
     * 用户id
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 任务id
     */
    public Long getMissionId() {
        return this.missionId;
    }

    /**
     * 任务id
     */
    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    /**
     * 中奖次数
     */
    public Integer getPrizeTimes() {
        return this.prizeTimes;
    }

    /**
     * 中奖次数
     */
    public void setPrizeTimes(Integer prizeTimes) {
        this.prizeTimes = prizeTimes;
    }

    /**
     * 抽奖次数
     */
    public Integer getLotteryTimes() {
        return this.lotteryTimes;
    }

    /**
     * 抽奖次数
     */
    public void setLotteryTimes(Integer lotteryTimes) {
        this.lotteryTimes = lotteryTimes;
    }

    /**
     * 更新时间,如:2016-08-18 12:53:30
     */
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 更新时间,如:2016-08-18 12:53:30
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 创建时间,如:2016-08-18 12:53:30
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * 创建时间,如:2016-08-18 12:53:30
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}