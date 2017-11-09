package com.iask.red_envelope.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.iask.red_envelope.config.Config;
import com.iask.red_envelope.consts.JsonCodeConsts;
import com.iask.red_envelope.consts.JsonConsts;
import com.iask.red_envelope.consts.ParamConsts;
import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.exception.BusinessErrorMsgException;
import com.iask.red_envelope.exception.UnableEncryptResponseException;
import com.iask.red_envelope.model.vo.CustomerCacheVo;
import com.iask.red_envelope.model.vo.EncryptDataVo;
import com.iask.red_envelope.service.RequestIdService;
import com.iask.red_envelope.util.CommonMethod;
import com.iask.red_envelope.util.JsonUtil;
import com.iask.red_envelope.util.RequestUtil;
import com.iask.red_envelope.util.SignUtil;
import com.iask.red_envelope.web.logger.ControllerLogger;
import com.wujie.common.security.aes.AESEncryption;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.craigq.common.logger.ILoggerFactory;
import org.craigq.common.logger._LogMgrPackageAccesser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.async.StandardServletAsyncWebRequest;
import org.springframework.web.context.request.async.WebAsyncManager;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by wuqiang on 16-6-29.
 *
 * @author wuqiang
 */
@Component
public class ApiPubControllerInterceptor implements MethodInterceptor {

    private static Logger selfLogger = LoggerFactory.getLogger(ApiPubControllerInterceptor.class);
    private static Logger httpHeaderLogger = LoggerFactory.getLogger("httpHeaderLogger");
    @Autowired
    private ILoggerFactory<HttpServletRequest, ControllerLogger> loggerFactory;
    @Autowired
    private RequestIdService requestIdService;

    private static final Pattern req_id_pattern = Pattern.compile("^[a-zA-Z0-9]{1,32}$");
    private static final Field WebAsyncManager_asyncWebRequest;

    static {
        try {
            WebAsyncManager_asyncWebRequest = WebAsyncManager.class.getDeclaredField("asyncWebRequest");
            WebAsyncManager_asyncWebRequest.setAccessible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        HttpServletRequest request = null;
        Object result = null;
        String secret = null;
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        request = requestAttributes.getRequest();
        if (request.getContentType() != null && request.getContentType().toLowerCase().startsWith("multipart/form-data;")) {
            // 如果是上传文件的请求，则要重新获取request的包装类
            WebAsyncManager webAsyncManager = (WebAsyncManager) request.getAttribute("org.springframework.web.context.request.async.WebAsyncManager.WEB_ASYNC_MANAGER");
            if (webAsyncManager != null) {
                Object asyncWebRequestObj = WebAsyncManager_asyncWebRequest.get(webAsyncManager);
                if (asyncWebRequestObj != null && asyncWebRequestObj instanceof StandardServletAsyncWebRequest) {
                    StandardServletAsyncWebRequest asyncWebRequest = (StandardServletAsyncWebRequest) asyncWebRequestObj;
                    HttpServletRequest multipartRequestObj = asyncWebRequest.getRequest();
                    if (multipartRequestObj != null
                        // && multipartRequestObj instanceof DefaultMultipartHttpServletRequest
                            ) {
                        request = multipartRequestObj;
                    }
                }
            }
        }
        String requestUri = CommonMethod.getFixedRequestUri(request);
        ControllerLogger logger = loggerFactory.getLogger(request);
        try {
            String clientIp = RequestUtil.getClientIp(request);
            // 把Logger对象设置到ThreadLocal
            _LogMgrPackageAccesser.setThreadLogger(logger);
            logger.setRequestUri(requestUri);
            logger.setClientIp(clientIp);

            Long timestamp = null;
            try {
                String timestampStr = request.getParameter(ParamConsts.timestamp);
                if (timestampStr != null && timestampStr.length() != 0) {
                    timestamp = Long.parseLong(timestampStr);
                }
            } catch (Exception e) {
            }
            String sign = request.getParameter(ParamConsts.sign);
            String req_id = request.getParameter(ParamConsts.req_id);
            String user_key = request.getParameter(ParamConsts.user_key);
            if (user_key == null || user_key.length() == 0 || user_key.length() > 32) {
                return JsonConsts.error_param;
            } else if (timestamp == null || timestamp < 0) {
                return JsonConsts.error_param;
            } else if (sign == null || sign.length() == 0) {
                return JsonConsts.error_param;
            } else if (req_id == null || req_id.length() == 0) {
                return JsonConsts.error_param;
            } else if (req_id.length() > 32 || (!req_id_pattern.matcher(req_id).matches())) {
                // req_id格式错误
                return JsonConsts.error_param_format_req_id;
            } else if (sign.length() != 32) {
                // MD5签名一定是32位
                return JsonConsts.error_sign;
            }

            {
                // 验证时间戳
                // 请求中的时间戳与服务器时间允许的最大误差（单位秒），小于等于0：表示不验证时间戳
                long timeDeviation = Config.getSecurityTimeDeviation();
                long nowSec = System.currentTimeMillis() / 1000;
                if (timeDeviation > 0 && Math.abs(nowSec - timestamp) > timeDeviation) {
                    // 时间戳超过允许误差
                    return JsonConsts.error_timestamp;
                }
            }

            CustomerCacheVo customer = requestIdService.getCustomerByUserKey(user_key);
            if (customer == null) {
                // 找不到对应的客户
                return JsonConsts.error_param;
            }

            secret = customer.getCustomerSecret();

            String queryString = request.getQueryString();
            if (queryString != null && queryString.contains(secret)) {
                // 避免开发者在参数中输出appSecret，如果有，则直接返回签名错误
                return JsonConsts.error_sign;
            }

            {
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
                String signByCalc = SignUtil.buildSign(secret, parameterMap);
                if (!signByCalc.equals(sign.toUpperCase())) {
                    //  签名错误
                    return JsonConsts.error_sign;
                }
            }

            {
                // 验证req_id是否重复
                if (requestIdService.isReqIdExists(customer.getCustomerId(), req_id)) {
                    return JsonConsts.error_repeat_request;
                }
            }

            if (httpHeaderLogger.isInfoEnabled()) {
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
                httpHeaderLogger.info(requestUri + " - requestHeader: \n" + requestHeader);
            }
            // 没有协议错误，则执行业务方法
            requestIdService.setReqIdCache(customer.getCustomerId(), req_id);
            result = methodInvocation.proceed();


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
