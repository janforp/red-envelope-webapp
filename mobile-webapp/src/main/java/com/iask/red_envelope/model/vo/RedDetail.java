package com.iask.red_envelope.model.vo;

import java.io.Serializable;

/**
 * Created by Summer on 16/7/13.
 */
public class RedDetail implements Serializable {

    // id
    private Integer id;
    // 头像
    private String img;
    // 客户名称
    private String name;
    // 最大红包
    private String money;
    // 已抢个数
    private Integer drawNum;
    // 总个数
    private Integer totalNum;
    // 状态 0-结束, 1-进行中, 2-已抢光
    private Integer status;
    // 跳转链接
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Integer getDrawNum() {
        return drawNum;
    }

    public void setDrawNum(Integer drawNum) {
        this.drawNum = drawNum;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
