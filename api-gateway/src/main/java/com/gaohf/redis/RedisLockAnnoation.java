package com.gaohf.redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * com.gaohf.annoation
 *
 * @Author : Gaohf
 * @Description :
 * @Date : 2018/1/18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedisLockAnnoation {

    String keyPrefix() default "";

    /**
     * 要锁定的key中包含的属性
     */
    String[] keys() default {};

    /**
     * 是否阻塞锁；
     * 1. true：获取不到锁，阻塞一定时间；
     * 2. false：获取不到锁，立即返回
     */
    boolean isSpin() default true;

    /**
     * 超时时间
     */
    int expireTime() default 10000;

    /**
     * 等待时间
     */
    int waitTime() default 50;

    /**
     * 获取不到锁的等待时间
     */
    int retryTimes() default 20;
}
