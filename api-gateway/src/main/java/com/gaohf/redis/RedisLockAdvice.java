package com.gaohf.redis;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.ThreadUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * com.gaohf.redis
 *
 * @Author : Gaohf
 * @Description :
 * @Date : 2018/1/18
 */
@Component
@Aspect
public class RedisLockAdvice {

    private static final Logger logger = LoggerFactory.getLogger(RedisLockAdvice.class);

    @Resource
    private RedisDistributionLock redisDistributionLock;

    @Around("@annotation(RedisLockAnnoation)")
    public Object processAround(ProceedingJoinPoint pjp) throws Throwable {
        //获取方法上的注解对象
        String methodName = pjp.getSignature().getName();
        Class<?> classTarget = pjp.getTarget().getClass();
        Class<?>[] par = ((MethodSignature) pjp.getSignature()).getParameterTypes();
        Method objMethod = classTarget.getMethod(methodName, par);
        RedisLockAnnoation redisLockAnnoation = objMethod.getDeclaredAnnotation(RedisLockAnnoation.class);

        //拼装分布式锁的key
        String[] keys = redisLockAnnoation.keys();
        Object[] args = pjp.getArgs();
        Object arg = args[0];
        StringBuilder temp = new StringBuilder();
        temp.append(redisLockAnnoation.keyPrefix());
        for (String key : keys) {
            String getMethod = "get" + StringUtils.capitalize(key);
            temp.append(MethodUtils.invokeExactMethod(arg, getMethod)).append("_");
        }
        String redisKey = StringUtils.removeEnd(temp.toString(), "_");

        //执行分布式锁的逻辑
        if (redisLockAnnoation.isSpin()) {
            //阻塞锁
            int lockRetryTime = 0;
            try {
                while (!redisDistributionLock.lock(redisKey, redisLockAnnoation.expireTime())) {
                    if (lockRetryTime++ > redisLockAnnoation.retryTimes()) {
                        logger.error("lock exception. key:{}, lockRetryTime:{}", redisKey, lockRetryTime);
                        throw new RuntimeException("系统错误");
                    }
                    ThreadUtil.holdXms(redisLockAnnoation.waitTime());
                }
                return pjp.proceed();
            } finally {
                redisDistributionLock.unlock(redisKey);
            }
        } else {
            //非阻塞锁
            try {
                if (!redisDistributionLock.lock(redisKey)) {
                    logger.error("lock exception. key:{}", redisKey);
                    throw new RuntimeException("系统错误");
                }
                return pjp.proceed();
            } finally {
                redisDistributionLock.unlock(redisKey);
            }
        }
    }
}
