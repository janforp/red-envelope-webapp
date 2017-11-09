package com.iask.red_envelope.model.vo;

import java.io.Serializable;

/**
 * Created by Summer on 16/7/22.
 */
public class RedUrlVo implements Serializable {

    // 红包链接
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
