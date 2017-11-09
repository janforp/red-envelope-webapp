package com.iask.red_envelope.util;

import java.io.Serializable;

/**
 * Created by Jan on 16/10/19.
 * 返回 结果
 */
//{"code":0,"msg":"抢任务成功"} ,{"code":9,"msg":"任务已被抢光"}
public class Result implements Serializable {

    private Integer code;
    private String  msg;
    private Object object;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
