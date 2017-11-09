package com.iask.red_envelope.model.vo.app_click_share_mission;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Jan on 16/11/15.
 * 分享点击任务详情页面展示对象
 */
public class ClickShareDetailVo implements Serializable {

    private Long missionId;
    private String userKey;
    // 朋友圈每次点击得到的奖励
    private BigDecimal money;
    //总奖励:总次数*每次的金额
    private BigDecimal totalMoney;
    // 任务名字
    private String missionTitle;
    // 任务图标
    private String missionIcon;
    // 接口链接:短链接
    private String shortUrl;
    //长链接
    private String longUrl;
    // 回调链接,如:广告链接
    private String callbackUrl;
    // 任务描述
    private String missionDesc;
    // 任务所需文字
    private String missionText;
    // 任务所需图标
    private String missionImg;
    // 示例图片
    private String exampleImg;
    // 该用户分享出去的链接共被点击次数
    private Integer totalClickTimes;
    //任务的总剩余次数
    private Integer leftClickTimes;
    //参与人数:分享链接的用户数
    private Integer partInNum;

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getPartInNum() {
        return partInNum;
    }

    public void setPartInNum(Integer partInNum) {
        this.partInNum = partInNum;
    }

    public Integer getLeftClickTimes() {
        return leftClickTimes;
    }

    public void setLeftClickTimes(Integer leftClickTimes) {
        this.leftClickTimes = leftClickTimes;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getMissionTitle() {
        return missionTitle;
    }

    public void setMissionTitle(String missionTitle) {
        this.missionTitle = missionTitle;
    }

    public String getMissionIcon() {
        return missionIcon;
    }

    public void setMissionIcon(String missionIcon) {
        this.missionIcon = missionIcon;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getMissionDesc() {
        return missionDesc;
    }

    public void setMissionDesc(String missionDesc) {
        this.missionDesc = missionDesc;
    }

    public String getMissionText() {
        return missionText;
    }

    public void setMissionText(String missionText) {
        this.missionText = missionText;
    }

    public String getMissionImg() {
        return missionImg;
    }

    public void setMissionImg(String missionImg) {
        this.missionImg = missionImg;
    }

    public String getExampleImg() {
        return exampleImg;
    }

    public void setExampleImg(String exampleImg) {
        this.exampleImg = exampleImg;
    }

    public Integer getTotalClickTimes() {
        return totalClickTimes;
    }

    public void setTotalClickTimes(Integer totalClickTimes) {
        this.totalClickTimes = totalClickTimes;
    }
}
