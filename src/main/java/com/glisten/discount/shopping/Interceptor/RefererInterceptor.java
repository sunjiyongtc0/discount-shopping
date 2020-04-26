package com.glisten.discount.shopping.Interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

//防盗链设置（图片防盗）
public class RefererInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RefererInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String referer = request.getHeader("referer");
        String host = request.getServerName();
        if (referer == null) {
            // 状态置为404
            logger.info("防盗链拦截");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return false;
        }
        java.net.URL url = null;
        try {
            url = new java.net.URL(referer);
        } catch (MalformedURLException e) {
            logger.error("非正常访问" + e.getMessage());
            // URL解析异常，也置为404
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return false;
        }
        // 首先判断请求域名和referer域名是否相同
        if (!host.equals(url.getHost())) {
            // 如果不等，判断是否在白名单中
//            if (properties.getRefererDomain() != null) {
//                for (String s : properties.getRefererDomain()) {
//                    if (s.equals(url.getHost())) {
//                        return true;
//                    }
//                }
//            }
            logger.error("非正常域名访问");
            // URL解析异常，也置为404
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return false;
        }
        return true;

    }
}