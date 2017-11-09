package com.iask.red_envelope.model.vo;

import com.iask.red_envelope.consts.JsonCodeConsts;

import java.io.Serializable;

/**
 * Created by wuqiang on 15-8-6.
 * <p/>
 * JSON响应对象，均由此类封装
 *
 * @author wuqiang
 */
public class JsonInfo implements Serializable {

    // 代码
    private Integer code;
    // 失败或成功消息
    private String msg;
    // 返回数据
    private Object data;

    private JsonInfo() {
    }

    public static JsonInfo buildSuccess(String msg) {
        return build(JsonCodeConsts.success, msg, null);
    }

    public static JsonInfo buildSuccess(String msg, Object data) {
        return build(JsonCodeConsts.success, msg, data);
    }

    public static JsonInfo buildSuccessData(Object data) {
        return build(JsonCodeConsts.success, null, data);
    }

    public static JsonInfo buildSuccessDataJson(Object data) {
        return build(JsonCodeConsts.api_success, null, data);
    }

    public static JsonInfo buildError(int code, String msg) {
        return build(code, msg, null);
    }
    public static JsonInfo buildErrorData(int code ,String msg ,Object data) {
        return build(code , msg ,data) ;
    }


    public static JsonInfo build(int code, String msg, Object data) {
        JsonInfo json = new JsonInfo();
        json.code = code;
        json.msg = msg;
        json.data = data;
        return json;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

}
