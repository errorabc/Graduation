package com.example.demo.Graduation.Configure.mvc;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

//自定义拦截器配置
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private MyInterceptorConfig myInterceptorConfig;

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(myInterceptorConfig).addPathPatterns("/**").excludePathPatterns("/login", "/loginin", "/css/**", "/fonts/**", "/images/**", "/js/**", "/lib/**","/bootstrap/**","/layui2.5/**");
    }


}
