package com.iask.red_envelope.util;

import com.iask.red_envelope.consts.RequestConsts;
import com.wujie.common.validation.ValidationUtil;
import org.craigq.common.logger.LogMgr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wuqiang on 16-1-28.
 *
 * @author wuqiang
 */
public class CommonMethod {
    private CommonMethod() {
    }

    /**
     * Random一定要全局共用，否则老版本的jdk，将会出现随机seed相同的问题
     */
    public static final Random RANDOM = new Random();
    /**
     * do not change
     */
    private static final String PWD_SALT = "OoFUW20bvRgD";

    /**
     * 密码加密(md5+salt)
     *
     * @param passwordSrc 密码原文
     */
    public static String encyptPasswordSrc(String passwordSrc) {
        return encyptPasswordMd5(MD5Encryption.byte2String(MD5Encryption.encryptMsg(passwordSrc)));
    }

    /**
     * 把字符串中的字符串进行格式化 <br> 比如：<br> src={0}格式错误; <br> formatArg(src,"密码"));<br> 返回：密码错误
     */
    public static String formatArg(String src, Object arg) {
        if (arg == null || src == null || src.length() == 0) {
            return src;
        }
        return src.replace("{0}", String.valueOf(arg));
    }

    /**
     * 密码加密(md5+salt)
     *
     * @param passwordByMd5 密码md5密文
     */
    public static String encyptPasswordMd5(String passwordByMd5) {
        byte[] encryptBytes = MD5Encryption.encryptMsg(PWD_SALT + passwordByMd5.toUpperCase());
        return MD5Encryption.byte2String(encryptBytes).toLowerCase();
    }

    /**
     * 是否是有效的URL
     *
     * @param url
     * @return
     */
    public static boolean isValidUrl(String url) {
        return ValidationUtil.match("^([hH][tT][tT][pP]([sS]?):\\/\\/\\S+)$", url);
    }

    /**
     * 是否是有效的email
     *
     * @param email
     * @return
     */
    public static boolean isValidEmail(String email) {
        return ValidationUtil.match("^([a-zA-Z0-9\\.\\_\\-])+@([a-zA-Z0-9\\_\\-])+((\\.[a-zA-Z0-9\\_\\-]{1,6}){1,2})$", email);
    }

    /**
     * 是否是有效的手机号
     *
     * @param cellphone
     * @return
     */
    public static boolean isValidCellphone(String cellphone) {
        return ValidationUtil.match("^1[34578]\\d{9}$", cellphone);
    }

    /**
     * 判断是否是ajax请求（如果是iframe异步提交，如果带有ajax标记参数，则也返回true）
     *
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && "XMLHttpRequest".equalsIgnoreCase(xRequestedWith)) {
            return true;
        } else {
            // 尝试查找是否有 iframe模拟ajax标志参数
            String ajaxParamValue = request.getParameter(RequestConsts.IFRAME_AJAX_MARK_PARAM_NAME);
            return "1".equals(ajaxParamValue);
        }
    }

    /**
     * 验证手机号（isTelephoneValid这个方法完成）以及，修正手机号数据，用户有可能输入 8613812345678/+8613812345678/(86)13812345678，需要剔除掉“+”，“(”，“)”，“86”
     */
    public static String validateAndFixedTelephone(String telephoneString) {
        if (telephoneString == null) {
            return null;
        }
        telephoneString = telephoneString.replace("+", "").replace("(", "").replace(")", "");
        if (telephoneString.startsWith("86")) {
            telephoneString = telephoneString.substring(2).trim();
        }
        telephoneString = telephoneString.trim();
        if (!ValidateUtil.isChinaTelephone(telephoneString)) {
            // 手机号验证未通过
            return null;
        }
        return telephoneString;
    }

    /**
     * 为了避免requestUri与数据库不匹配，此方法会把多条/替换成一条/
     *
     * @param request
     * @return
     */
    public static String getFixedRequestUri(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        return getFixedRequestUri(requestUri, contextPath);
    }

    /**
     * 为了避免requestUri与数据库不匹配，此方法会把多条/替换成一条/
     *
     * @param requestUri
     * @return
     */
    public static String getFixedRequestUri(String requestUri, String contextPath) {
        String requestUriLower = requestUri.toLowerCase();
        if (requestUriLower.startsWith("http://")) {
            requestUri = requestUri.substring(requestUriLower.indexOf("/", requestUriLower.indexOf("http://") + "http://".length()));
        } else if (requestUriLower.startsWith("https://")) {
            requestUri = requestUri.substring(requestUriLower.indexOf("/", requestUriLower.indexOf("https://") + "https://".length()));
        }
        while (requestUri.contains("//")) {
            requestUri = requestUri.replace("//", "/");
        }
        if (contextPath != null && contextPath.length() > 0) {
            requestUri = requestUri.substring(requestUri.indexOf(contextPath) + contextPath.length());
        }
        return requestUri;
    }

    /**
     * 判断是否是移动浏览器
     *
     * @param request
     * @return
     */
    public static boolean isMobileBrower(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        return userAgent != null ? userAgent.contains("AppleWebKit") && userAgent.contains("Mobile") : false;
    }

    /**
     * 判断是否在微信浏览器中
     *
     * @param request
     * @return
     */
    public static boolean isWeixinBrower(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        return userAgent != null ? userAgent.contains("MicroMessenger") : false;
    }

    /**
     * 判断是否是苹果设备上
     * @param request
     * @return
     */
    public static  boolean isAppleBrower(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if(userAgent != null && (userAgent.contains("iPhone") || userAgent.contains("iPad") || userAgent.contains("iPod"))) {
            return true;
        }
        return false;
    }

    /**
     * 不能从userAgent中获取到网络类型时，默认取这个值
     */
    public static final String default_network_type;
    private static final String weixinNetworkTypePrefix;
    private static Pattern weixinNetworkTypePattern;
    private static Set<String> weixinAllNetworkTypes;

    static {
        default_network_type = "wifi";
        weixinNetworkTypePrefix = "NetType/";
        weixinNetworkTypePattern = Pattern.compile(weixinNetworkTypePrefix + "[A-Za-z0-9]+");
        weixinAllNetworkTypes = new HashSet<>(4);
        weixinAllNetworkTypes.add("2g");
        weixinAllNetworkTypes.add("3g");
        weixinAllNetworkTypes.add("4g");
        weixinAllNetworkTypes.add("wifi");
    }

    /**
     * 获取微信浏览器提供的当前网络类型
     * 微信网络类型: 2g，3g，4g，wifi
     *
     * @param request
     * @return
     */
    public static String getWeixinNetworkType(HttpServletRequest request) {
        if (isWeixinBrower(request)) {
            String networkType = null;
            String userAgent = request.getHeader("User-Agent");
            if (userAgent != null) {
                Matcher matcher = weixinNetworkTypePattern.matcher(userAgent);
                while (matcher.find()) {
                    String tmp = matcher.group();
                    networkType = tmp.replace(weixinNetworkTypePrefix, "").toLowerCase();
                    break;
                }
            }
            if (networkType == null) {
                networkType = default_network_type;
            } else {
                networkType = weixinAllNetworkTypes.contains(networkType) ? networkType : default_network_type;
            }
            return networkType;
        } else {
            return default_network_type;
        }
    }

    /**
     * @param request
     * @param response
     * @param httpStatusCode HTTP状态码；如200, 404, 500
     * @param errorJson
     */
    public static void sendErrorJsonResponse(HttpServletRequest request, HttpServletResponse response,
                                             int httpStatusCode, String errorJson) {
        String charSet = request.getCharacterEncoding();
//        String contentType = "application/json;charset=" + charSet;
        String contentType = RequestConsts.CONTENT_TYPE_JSON;
        // 在响应头中声明，响应内容采用明文方式
        response.setHeader(RequestConsts.RESPONSE_HEADER_RESPONSE_DATA_TYPE, RequestConsts.VALUE_DATA_TYPE_PUB);
        response.setCharacterEncoding(charSet);
        response.setContentType(contentType);
        response.setStatus(httpStatusCode);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(errorJson);
        } catch (Exception e) {
            LogMgr.getLogger().error(e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }


    /**
     * 为用户产生userSecret,长度32位
     */
    public static String generateUserSecret() {
        return RandomUtil.getRandomString(32);
    }


}
