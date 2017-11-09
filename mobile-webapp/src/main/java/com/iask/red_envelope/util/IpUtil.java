package com.iask.red_envelope.util;


import com.alibaba.fastjson.JSON;
import com.iask.red_envelope.model.ip.IpData;
import com.iask.red_envelope.model.ip.TaobaoIP;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Jan on 16/10/26.
 * 关于IP的方法
 */
public class IpUtil {

    /**
     * 取得客户端ip地址（可能有多个，如：192.168.10.2,192.168.10.1）<br>
     *
     * @param request HttpServletRequest
     * @return
     */
    public static String getIp(HttpServletRequest request) {

        return RequestUtil.getClientIp(request);
    }


    /**
     * 根据IP回去地址信息
     * @param ip
     * @return
     */
    public static IpData getRegionData(String ip) throws IOException {

        String url = "http://ip.taobao.com/service/getIpInfo.php?ip=";

        String returnJson = ImitateSendRequestUtil.doGet(url+ip,null,"GBK");

        //在此最好能判断下,返回的字符串是否是标准的json格式的字符串,若不是,则直接使用IP,不要地址信息

        if (!returnJson.startsWith("{")) {
            returnJson = "{"+returnJson;
        }
        if (!returnJson.endsWith("}")) {
            returnJson = returnJson+"}";
        }

        TaobaoIP taobaoIP = JSON.parseObject(returnJson,TaobaoIP.class);

        IpData data = taobaoIP.getData();
        if ("0".equals(taobaoIP.getCode().trim())){
            data.setIsInvalidIP(0);
        }else {
            data.setIsInvalidIP(1);
        }
        return data;
    }

}
