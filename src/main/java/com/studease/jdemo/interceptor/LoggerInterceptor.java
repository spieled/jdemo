package com.studease.jdemo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Author: liushaoping
 * Date: 2016/4/12.
 */
public class LoggerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        System.out.println("preHandle ....");

        /*HandlerMethod handlerMethod = (HandlerMethod) o;
        Logger logger = handlerMethod.getMethodAnnotation(Logger.class);
        if (logger == null) {
            logger = handlerMethod.getBean().getClass().getAnnotation(Logger.class);
        }
        if (logger == null) {
            System.out.println("方法和类声明都没有Logger注解，不对此方法进行日志记录");
        }
        boolean duration = logger.duration();
        final boolean parameter = logger.parameter();
        final boolean result = logger.result();*/

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle ....");

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion ....");

    }
}
