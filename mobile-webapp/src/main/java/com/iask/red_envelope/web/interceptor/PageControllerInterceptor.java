package com.iask.red_envelope.web.interceptor;

import com.iask.red_envelope.config.Config;
import com.iask.red_envelope.consts.*;
import com.iask.red_envelope.exception.BusinessErrorMsgException;
import com.iask.red_envelope.model.cache.UserKeySecret;
import com.iask.red_envelope.service.user.UserCacheService;
import com.iask.red_envelope.util.CommonMethod;
import com.iask.red_envelope.util.JsonUtil;
import com.iask.red_envelope.util.RequestUtil;
import com.iask.red_envelope.util.SignUtil;
import com.iask.red_envelope.web.controller.page.auth.SignController;
import com.iask.red_envelope.web.logger.ControllerLogger;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.craigq.common.logger.ILoggerFactory;
import org.craigq.common.logger._LogMgrPackageAccesser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * Created by Summer on 16/8/24.
 */
@Component
public class PageControllerInterceptor implements MethodInterceptor {

    private static Logger selfLogger = LoggerFactory.getLogger(PageControllerInterceptor.class);
    private static Logger httpHeaderLogger = LoggerFactory.getLogger("httpHeaderLogger");

    // 需要登录才能访问的Controller的包路径
    private static final String userAuthPagePackage = SignController.class.getPackage().getName();

    @Autowired
    private ILoggerFactory<HttpServletRequest, ControllerLogger> loggerFactory;
    @Autowired
    private UserCacheService userCacheService;

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object result = null;
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        ControllerLogger logger = loggerFactory.getLogger(request);
        boolean isDebug = Config.isDebug();
        boolean hasError = false;
        boolean isAjax = CommonMethod.isAjaxRequest(request) || methodInvocation.getMethod().isAnnotationPresent(ResponseBody.class);
        boolean isWeiXinBrowser = CommonMethod.isWeixinBrower(request);
        String requestUri = CommonMethod.getFixedRequestUri(request);

        request.setAttribute(AttributeConsts.REQUEST_URI, requestUri);// 放在此处，Controller代码也可以获取到
        request.setAttribute(AttributeConsts.NETWORK_TYPE, CommonMethod.getWeixinNetworkType(request));// 当在微信浏览器中，获取用户当前网络类型，放在此处，Controller代码也可以获取到

        try {
            String clientIp = RequestUtil.getClientIp(request);
            // 把Logger对象设置到ThreadLocal
            _LogMgrPackageAccesser.setThreadLogger(logger);
            logger.setRequestUri(requestUri);
            if (isDebug || httpHeaderLogger.isInfoEnabled()) {
                Enumeration<String> headers = request.getHeaderNames();
                Map<String, String> requestHeader = new HashMap<String, String>();
                if (headers != null) {
                    while (headers.hasMoreElements()) {
                        String name = headers.nextElement();
                        List<String> headerValueList = new ArrayList<>(3);
                        Enumeration<String> headerValues = request.getHeaders(name);
                        if (headerValues != null) {
                            while (headerValues.hasMoreElements()) {
                                headerValueList.add(headerValues.nextElement());
                            }
                        }
                        requestHeader.put(name, headerValuesToString(headerValueList));
                    }
                }
                logger.setRequestHeader(requestHeader);
                if (httpHeaderLogger.isInfoEnabled()) {
                    httpHeaderLogger.info(requestUri + " - requestHeader: \n" + requestHeader);
                }
            }
            // 设置客户端IP
            logger.setClientIp(clientIp);

            // 目标Controller的包路径
            String targetControllerPackage = methodInvocation.getThis().getClass().getPackage().getName();

            if (targetControllerPackage.startsWith(userAuthPagePackage)) { // 需要登录

                if(!isWeiXinBrowser) { // 不在微信浏览器

                    if(isAjax) { // ajax请求

                        String userKey = (String) request.getAttribute(RequestConsts.ATTR_USER_KEY);
                        UserKeySecret userKeySecret = userCacheService.getUserSecret(userKey);
                        String sign = request.getParameter(ParamConsts.sign);

                        // 验证签名
                        Map<String, String[]> oldParameterMap = request.getParameterMap();
                        Map<String, String> parameterMap = new HashMap<>(oldParameterMap.size());
                        Set<String> paramNameSet = oldParameterMap.keySet();
                        for (String pn : paramNameSet) {
                            if (!ParamConsts.sign.equals(pn)) {
                                String[] valueArray = oldParameterMap.get(pn);
                                if (valueArray != null && valueArray.length > 0) {
                                    parameterMap.put(pn, valueArray[0]);
                                }
                            }
                        }

                        String signByCalc = SignUtil.buildSign(userKeySecret.getUserSecret(), parameterMap);
                        if (!signByCalc.equals(sign.toUpperCase())) {
                            //  签名错误
                            hasError = true;
                            result = JsonConsts.error_param;
                        }

                    }else {

                        Integer userId = (Integer) request.getAttribute(RequestConsts.ATTR_USER_ID);

                        if(userId == null) { // 未登录,跳转本地登录页面

                            hasError = true;

                            // 判断是否是ios
                            Boolean isIos = CommonMethod.isAppleBrower(request);
                            request.setAttribute("isIos", isIos);

                            result = "page/mine/login";

                        }

                    }

                }

            }

            if (!hasError) {
                // 前面没有错误
                // 执行Controller代码
                result = methodInvocation.proceed();
            }

        } catch (Throwable throwable) {
            if (throwable instanceof BusinessErrorMsgException) {
                BusinessErrorMsgException businessErrorMsgException = (BusinessErrorMsgException) throwable;
                int code = JsonCodeConsts.error_param;
                if (businessErrorMsgException.getCode() != null && businessErrorMsgException.getCode().intValue() != JsonCodeConsts.success) {
                    code = businessErrorMsgException.getCode();
                }
                String msg = null;
                if (businessErrorMsgException.getMessage() != null && businessErrorMsgException.getMessage().length() > 0) {
                    msg = businessErrorMsgException.getMessage();
                }
                if (code == JsonCodeConsts.error_param && msg == null) {
                    result = JsonConsts.error_param;
                } else if (code == JsonCodeConsts.error_data_not_exist && msg == null) {
                    result = JsonConsts.error_data_not_exist;
                } else {
                    result = JsonUtil.buildError(code, msg);
                }
            } else {
                result = JsonConsts.error_system;
                logger.setException(throwable);
                logger.error(throwable);
            }
        } finally {

            request.removeAttribute(RequestConsts.ATTR_USER_ID);
            request.removeAttribute(RequestConsts.ATTR_USER_KEY);

            logger.setEndTime(System.currentTimeMillis());
            Throwable exception = logger.getException();
            // 请求是否成功：没有异常或者异常是BusinessErrorMsgException，都算成功
            boolean isSuccess = exception == null || (exception instanceof BusinessErrorMsgException);
            logger.close(isSuccess);
            // 清空ThreadLocal上的Logger
            _LogMgrPackageAccesser.setThreadLogger(null);
            if (!isSuccess) {
                // 请求不成功，发送异常报告
                try {
//                    OpUtil.sendControllerExceptionReport(systemReporterTask,
//                            request, logger.getException(), logger);
                } catch (Exception e) {
                    selfLogger.error("sendControllerExceptionReport", e);
                }
            }

        }
        return result;
    }

    private static String headerValuesToString(Collection<String> values) {
        if (values == null || values.size() == 0) {
            return "";
        }
        StringBuilder buf = new StringBuilder();
        int i = 0;
        for (String value : values) {
            if (i > 0) {
                buf.append(", ");
            }
            buf.append(value);
            i++;
        }
        return buf.toString();
    }

}
