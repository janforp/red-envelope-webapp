package com.iask.red_envelope.util.shorten_url;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Jan on 16/11/16.
 * http://581.so/h/api.html
 */
public class So581ShortUrlUtil {

    public static String getShortUrl(String longUrl) throws IOException {


        String requestUrl = "http://581.so/api.php?url="+longUrl;

        StringBuffer response = new StringBuffer();
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(requestUrl);
        client.executeMethod(method);
        client.setTimeout(0);
        if (method.getStatusCode() == HttpStatus.SC_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

        }
        return response.toString();

    }
}
