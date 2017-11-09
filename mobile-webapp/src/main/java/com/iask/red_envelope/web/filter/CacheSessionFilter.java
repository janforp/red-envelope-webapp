package com.iask.red_envelope.web.filter;

import com.iask.red_envelope.consts.RequestConsts;
import com.iask.red_envelope.util.CommonMethod;
import com.iask.red_envelope.util.RequestUtil;
import com.iask.red_envelope.service.user.SessionCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by craig on 16/2/11.
 */
@Component
public class CacheSessionFilter implements Filter {
    private static Logger selfLogger = LoggerFactory.getLogger(CacheSessionFilter.class);
    // 页面请求的requestURI前缀
    private String pageRequestUriPrefix = "/c/p/";
    @Autowired
    private SessionCacheService sessionCacheService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestUri = CommonMethod.getFixedRequestUri(request);
        if (requestUri.startsWith(pageRequestUriPrefix)) {
            String cacheSessionId = sessionCacheService.getRequestedCacheSessionId(request);
            if (cacheSessionId == null) {
                // 这个请求是当前会话的第一个请求
                cacheSessionId = sessionCacheService.initialize(request, response);// 初始化一个session：产生新的sessionId并设置到request属性范围、setCookie到response；产生新的会话Map并设置到属性范围
            } else {
                // 尝试从缓存获取会话Map
                Map<String, Serializable> cachedSessionMap = sessionCacheService.getCachedSession(cacheSessionId);
                if (cachedSessionMap == null) {
                    // 缓存中没有这个sessionId对应的会话Map（sessionId已过期、或者cookie中的sessionId是伪造的）
                    cacheSessionId = sessionCacheService.initialize(request, response); // 初始化一个session：产生新的sessionId并设置到request属性范围、setCookie到response；产生新的会话Map并设置到属性范围
                } else {
                    // 缓存中找到了这个会话Map，直接设置到request属性范围
                    request.setAttribute(SessionCacheService.CACHE_SESSION, cachedSessionMap);
                }
            }
            try {
                filterChain.doFilter(request, response);
            } finally {
                Map<String, Serializable> cachedSessionMap = sessionCacheService.getSession(request);
                if (cachedSessionMap == null) {
                    cachedSessionMap = new HashMap<>(0);
                    request.setAttribute(SessionCacheService.CACHE_SESSION, cachedSessionMap);
                }
                sessionCacheService.updateCachedSession(cacheSessionId, cachedSessionMap);
            }
        } else {
            if (requestUri.equals("/") || requestUri.equals("/index.html") || requestUri.equals("/index.htm")) {
                String appUrl = RequestUtil.getAppUrl(request);
                StringBuilder redirectUri = new StringBuilder(appUrl.length() + 32);
                redirectUri.append(appUrl).append(RequestConsts.INDEX_PAGE);
                String queryString = request.getQueryString();
                if (queryString != null && queryString.length() > 0) {
                    redirectUri.append("?").append(queryString);
                }
                response.resetBuffer();
                response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
                response.setHeader("Location", redirectUri.toString());// 302永久重定向到首页真实路径
                return;
            }
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}