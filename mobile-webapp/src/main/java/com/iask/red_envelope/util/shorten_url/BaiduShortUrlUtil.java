package com.iask.red_envelope.util.shorten_url;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Jan on 16/11/15.
 * 百度短链接服务
 */
public class BaiduShortUrlUtil {


    public static String doGPostShortUrl(String longUrl) throws IOException {
        String url = "http://dwz.cn/create.php";
        StringBuffer response = new StringBuffer();
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);
        method.setParameter("url",longUrl);

        client.executeMethod(method);
        client.setTimeout(0);
        if (method.getStatusCode() == HttpStatus.SC_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), "utf-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

        }
        return response.toString();
    }

}
