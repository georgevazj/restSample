/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kpmw.restSample.configuration;

import com.kpmw.restSample.interceptor.ApiRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author jvazquez
 */

@Configuration
public class ApiMVCConfig implements WebMvcConfigurer {
    
    @Autowired
    private ApiRequestInterceptor apiRequestInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiRequestInterceptor)
                .addPathPatterns("/**/questions/**/");
    }
    
}
