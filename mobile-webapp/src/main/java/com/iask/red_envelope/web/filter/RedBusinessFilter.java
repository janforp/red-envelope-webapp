package com.iask.red_envelope.web.filter;

import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.consts.SessionConsts;
import com.iask.red_envelope.model.cache.UserKeySecret;
import com.iask.red_envelope.service.user.SessionCacheService;
import com.iask.red_envelope.service.user.UserCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by Summer on 16/8/24.
 */
@Component
public class RedBusinessFilter implements Filter {

    private static Logger selfLogger = LoggerFactory.getLogger(RedBusinessFilter.class);

    @Autowired
    private SessionCacheService sessionCacheService;
    @Autowired
    private UserCacheService userCacheService;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * 登录之后把userId,userKey存入request作用域
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 判断是否登录,若登录 设置userId到Request属性范围中
        Integer userId = null;
        String cacheSessionId = sessionCacheService.getRequestedCacheSessionId(request);
        if(cacheSessionId != null) {
            Map<String, Serializable> sessionMap = sessionCacheService.getCachedSession(cacheSessionId);
            if(sessionMap != null) {
                String userKey = (String) sessionMap.get(SessionConsts.USER_KEY);
                UserKeySecret userKeySecret = userCacheService.getUserSecret(userKey);
                if(userKeySecret != null) {
                    userId = userKeySecret.getUserId();
                    request.setAttribute(RequestConsts.ATTR_USER_ID, userId);
                    request.setAttribute(RequestConsts.ATTR_USER_KEY, userKey);
                }
            }
        }

        filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {
    }
}
