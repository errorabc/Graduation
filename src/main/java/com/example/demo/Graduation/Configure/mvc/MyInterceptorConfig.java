package com.example.demo.Graduation.Configure.mvc;


import com.alibaba.fastjson.JSON;
import com.example.demo.Graduation.controller.UserController.LoginController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyInterceptorConfig implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        logger.info("请求地址" + arg0.getRequestURI());
    }

    /*
     * 处理请求完成后视图渲染之前的处理操作
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        logger.info("跳转视图" + arg0.getRequestURI());
    }

    /*
     * 进入controller层之前拦截请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        logger.info("携带参数" + JSON.toJSONString(request.getParameterMap()));
        logger.info("                    ");
        return true;
    }


}
