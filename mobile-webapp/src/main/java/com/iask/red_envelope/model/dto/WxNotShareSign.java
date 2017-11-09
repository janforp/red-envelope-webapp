package com.iask.red_envelope.model.dto;

import java.util.List;

/**
 * Created by Jan on 16/8/30.
 * 微信签名
 */
public class WxNotShareSign {

    //是否调试
    private Boolean debug;
    //公众号id
    private String  appId;
    //当前时间戳
    private String  timestamp;
    //随机字符串
    private String  nonceStr;
    //当前请求露肩
    private String  url;
    //签名
    private String  signature;
    //接口名字
    private List<String> jsApiList;

    public Boolean getDebug() {
        return debug;
    }

    public void setDebug(Boolean debug) {
        this.debug = debug;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public List<String> getJsApiList() {
        return jsApiList;
    }

    public void setJsApiList(List<String> jsApiList) {
        this.jsApiList = jsApiList;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
