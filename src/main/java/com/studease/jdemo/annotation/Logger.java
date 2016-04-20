package com.studease.jdemo.annotation;

import java.lang.annotation.*;

/**
 * 日志注解
 * Author: liushaoping
 * Date: 2016/4/12.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Logger {
    /**
     * 记录执行耗时时间
     *
     * @return
     */
    boolean duration() default true;

    /**
     * 记录入参
     *
     * @return
     */
    boolean parameter() default false;

    /**
     * 记录返回值
     *
     * @return
     */
    boolean result() default false;
}
