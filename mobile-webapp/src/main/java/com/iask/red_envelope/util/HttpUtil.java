package com.iask.red_envelope.util;

import org.craigq.common.logger.ILogger;
import org.craigq.common.logger.LogMgr;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by wuqiang on 15-9-29.
 *
 * @author wuqiang
 */
public class HttpUtil {
    private static int connectTimeout = 10000;//10秒
    private static int readTimeout = 15000;//15秒

    /**
     * 采用get方式下载文件
     *
     * @param url
     * @param filePath
     * @return 下载成功或失败
     */
    public static boolean download(String url, String filePath) {
        boolean flag = false;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            File targetFile = new File(filePath);
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            URL urlObj = new URL(url);
            conn = (HttpURLConnection) urlObj.openConnection();
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            conn.connect();
            inputStream = conn.getInputStream();
            outputStream = new FileOutputStream(targetFile);
            byte[] data = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(data)) != -1) {
                outputStream.write(data, 0, len);
            }
            flag = true;
        } catch (Exception e) {
            flag = false;
            ILogger logger = LogMgr.getLogger();
            if (logger.isWarnEnabled()) {
                logger.warn("HttpUtil.download: " + e);
            }
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                }
            }
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e) {
                }
            }
        }
        return flag;
    }

    /**
     * 采用get方式下载文件
     *
     * @param url
     * @param filePath
     * @return contentType（如果返回null表明下载失败，如果返回：""，表明下载成功但服务器没有返回Content-Type）
     */
    public static String downloadAndRetrunContentType(String url, String filePath) {
        String contentType = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            File targetFile = new File(filePath);
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            URL urlObj = new URL(url);
            conn = (HttpURLConnection) urlObj.openConnection();
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            conn.connect();
            inputStream = conn.getInputStream();
            outputStream = new FileOutputStream(targetFile);
            byte[] data = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(data)) != -1) {
                outputStream.write(data, 0, len);
            }
            contentType = conn.getContentType();
            if (contentType == null) {
                contentType = "";
            }
        } catch (Exception e) {
            contentType = null;
            ILogger logger = LogMgr.getLogger();
            if (logger.isWarnEnabled()) {
                logger.warn("HttpUtil.download: " + e);
            }
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                }
            }
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e) {
                }
            }
        }
        return contentType;
    }

    /**
     * 采用get方式下载文件
     *
     * @param url
     * @param outputStream 目标输出流（需要外部自行关闭）
     * @return contentType（如果返回null表明下载失败，如果返回：""，表明下载成功但服务器没有返回Content-Type）
     */
    public static String downloadToOutputStreamAndRetrunContentType(String url, OutputStream outputStream) {
        String contentType = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        try {
            URL urlObj = new URL(url);
            conn = (HttpURLConnection) urlObj.openConnection();
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            conn.connect();
            inputStream = conn.getInputStream();
            byte[] data = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(data)) != -1) {
                outputStream.write(data, 0, len);
            }
            contentType = conn.getContentType();
            if (contentType == null) {
                contentType = "";
            }
        } catch (Exception e) {
            contentType = null;
            ILogger logger = LogMgr.getLogger();
            if (logger.isWarnEnabled()) {
                logger.warn("HttpUtil.download: " + e);
            }
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                }
            }
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                }
            }
        }
        return contentType;
    }
}
