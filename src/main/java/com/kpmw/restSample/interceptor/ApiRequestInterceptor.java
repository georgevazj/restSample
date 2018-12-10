/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kpmw.restSample.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;

/**
 *
 * @author jvazquez
 */

@Component
public class ApiRequestInterceptor extends HandlerInterceptorAdapter {
    
    private static final Logger log = LoggerFactory.getLogger(ApiRequestInterceptor.class);
    
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {
        long startTime = System.currentTimeMillis();
        log.info("Request URL:: " + request.getRequestURI().toString() + ":: Start Time=" + System.currentTimeMillis());
        request.setAttribute("startTime", startTime);
        
        HttpServletRequest requestCacheWrapperObject = new ContentCachingRequestWrapper(request);
        requestCacheWrapperObject.getParameterMap();
        
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView) throws Exception {
        System.out.println("Request URL::" + request.getRequestURL().toString() + " Sent to handler :: Current Time=" + System.currentTimeMillis());
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) {
        long startTime = (Long) request.getAttribute("startTime");
        log.info("Request URL::" + request.getRequestURL().toString() + ":: End Time=" + System.currentTimeMillis());
        log.info("Request URL::" + request.getRequestURL().toString()
				+ ":: Time Taken=" + (System.currentTimeMillis() - startTime));
    }
    
}
