package com.iask.red_envelope.model.ip;


import java.io.Serializable;

/**
 * Created by Jan on 16/10/27.
 */
public class TaobaoIP implements Serializable {

    private String code;

    private IpData data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public IpData getData() {
        return data;
    }

    public void setData(IpData data) {
        this.data = data;
    }
}
