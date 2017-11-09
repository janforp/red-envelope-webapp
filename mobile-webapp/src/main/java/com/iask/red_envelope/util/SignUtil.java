package com.iask.red_envelope.util;

import java.util.Arrays;
import java.util.Map;

public class SignUtil {
    /**
     * 对参数进行签名
     *
     * @param appSecret    API密钥
     * @param allStringMap 所有的参数（除sign）
     * @return 最终返回的是大写
     */
    public static String buildSign(String appSecret, Map allStringMap) {
        StringBuilder buf = new StringBuilder(512);
        buf.append(appSecret);
        Object[] keys = allStringMap.keySet().toArray();
        Arrays.sort(keys);
        for (Object key : keys) {
            Object value = allStringMap.get(key);
            if (key != null && value != null) {
                buf.append(key).append(value);
            }
        }
        buf.append(appSecret);
        String sign = MD5Encryption.byte2String(MD5Encryption.encryptMsg(buf.toString())).toUpperCase();
        return sign;
    }
}
