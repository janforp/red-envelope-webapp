package com.iask.red_envelope.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.iask.red_envelope.consts.BaseConsts;
import com.iask.red_envelope.model.vo.JsonInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by wuqiang on 15-8-6.
 *
 * @author wuqiang
 */
public class JsonUtil {

    private static Logger selfLogger = LoggerFactory.getLogger(JsonUtil.class);

    public static String toJSONString(Object obj) {
        JSON.DEFFAULT_DATE_FORMAT = BaseConsts.FMT_yyyy_MM_dd_HH_mm_ss;
        String json = JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
        if (selfLogger.isInfoEnabled()) {
            selfLogger.info(json);
        }
        return json;
    }

    public static String buildSuccess(String msg) {
        return toJSONString(JsonInfo.buildSuccess(msg));
    }

    public static String buildSuccess(String msg, Object data) {
        return toJSONString(JsonInfo.buildSuccess(msg, data));
    }

    public static String buildSuccessData(Object data) {
        return toJSONString(JsonInfo.buildSuccessData(data));
    }

    public static String buildError(int code, String msg) {
        return toJSONString(JsonInfo.buildError(code, msg));
    }

    public static String buildErrorData(int code ,String msg ,Object data) {
        return toJSONString(JsonInfo.buildErrorData(code,msg,data));
    }

    public static String buildSuccessDataJson(Object data) {
        return toJSONString(JsonInfo.buildSuccessDataJson(data));
    }

}
