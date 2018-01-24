package com.gaohf.utils;


import com.gaohf.config.BizConfig;
import com.gaohf.config.Constants;
import com.gaohf.enums.EnumClientError;
import com.gaohf.redis.RedisUtils;
import com.gaohf.service.UserService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Component
public class RedisLimitUtils {

    static Logger logger  = LoggerFactory.getLogger(RedisLimitUtils.class);

    private static RedisLimitUtils redisLimitUtils;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private BizConfig bizConfig;

    @Autowired
    private UserService userService;


    @PostConstruct
    public void init() {
        redisLimitUtils = this;
        redisLimitUtils.redisUtils = this.redisUtils;
        redisLimitUtils.userService = this.userService;
        redisLimitUtils.bizConfig = this.bizConfig;
    }

    /**
     * 热加载配置
     * @param bizConfig
     */
    public static void reloadConfig(BizConfig bizConfig) {
        redisLimitUtils.bizConfig = bizConfig;
    }

    /**
     * 获取当天剩余秒数
     * @return
     */
    public static long leftSeconds() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);

        long d1 = cal.getTime().getTime();
        long d2 = new Date().getTime();
        long sub = ((d1 - d2) / 1000L);

        if (sub < 0)
            sub = 1;

        return sub;
    }

    /**
     * 增加一次登录异常信息
     * @param key--登录名
     */
    public static void addPasswordErrorCount(String key) {
        String originalKey = key;
        key = Constants.LOGIN_ERROR_COUNT_PREFIX + key;
        String incrementKey = key + Constants.COMMON_SUFFIX;
        Long incrCount = redisLimitUtils.redisUtils.increment(incrementKey, redisLimitUtils.bizConfig.getPwdErrorExpired());
        if (null == incrCount)
            incrCount = 0L;

        if (incrCount >= redisLimitUtils.bizConfig.getPwdErrorCount()) {
            addAccountLockedCount(originalKey);
            delPasswordErrorCount(originalKey);
        }
    }

    /**
     * 清楚登录异常信息
     * @param key--登录名
     */
    public static void delPasswordErrorCount(String key) {
        key = Constants.LOGIN_ERROR_COUNT_PREFIX + key;
        redisLimitUtils.redisUtils.evict(key);
        String incrementKey = key + Constants.COMMON_SUFFIX;
        redisLimitUtils.redisUtils.evict(incrementKey);
    }

    /**
     * 增加一次账号暂停次数,时间为30分钟
     * @param key--登录名
     */
    public static void addAccountLockedCount(String key) {
        String originalKey = key;
        key = Constants.ACCOUNT_LOCKED_COUNT_PREFIX + key;
        String incrementKey = key + Constants.COMMON_SUFFIX;
        redisLimitUtils.redisUtils.increment(incrementKey, redisLimitUtils.bizConfig.getAccountLockedExpired());

        String hisIncrementKey = key + Constants.HISTORY_SUFFIX;
        Long count = redisLimitUtils.redisUtils.increment(hisIncrementKey, leftSeconds());

        if (count >= redisLimitUtils.bizConfig.getAccountLockedTimes()) {
            redisLimitUtils.userService.lockUserByName(originalKey, EnumClientError.PASSWORD_LOCKED_ERROR.getMsg());
        }
    }

    /**
     * 查询是否被暂停
     * @param key--登录名
     * @return
     */
    public static boolean isAccountLocked(String key) {
        key = Constants.ACCOUNT_LOCKED_COUNT_PREFIX + key;
        String incrementKey = key + Constants.COMMON_SUFFIX;

        Integer count = redisLimitUtils.redisUtils.get(incrementKey, Integer.class);
        if (null == count)
            return false;
        else
            return true;
    }

    /**
     * 清楚登录异常信息
     * @param key--登录名
     */
    public static void delAccountLockedCount(String key) {
        key = Constants.ACCOUNT_LOCKED_COUNT_PREFIX + key;
        redisLimitUtils.redisUtils.evict(key);

        String incrementKey = key + Constants.COMMON_SUFFIX;
        redisLimitUtils.redisUtils.evict(incrementKey);

        String hisIncrementKey = key + Constants.HISTORY_SUFFIX;
        redisLimitUtils.redisUtils.evict(hisIncrementKey);
    }

    /**
     * 自然日内连续登录5个新的终端设备，判断账号异常，需要通过安全校验才可以登陆
     * @param key--userId
     * @param clientSn
     * @return
     */
    public static boolean addNewClienLockCount(String key, String clientSn) {
        key = Constants.CLIENT_COUNT_PREFIX + key;
        String incrementKey = key + Constants.COMMON_SUFFIX;
        Long count = 0L;
        ArrayList<String> list = redisLimitUtils.redisUtils.get(key, ArrayList.class);
        if (null == list) {
            list = Lists.newArrayList();
            list.add(clientSn);
            count = redisLimitUtils.redisUtils.increment(incrementKey, leftSeconds());
        } else {
            if (!list.contains(clientSn)) {
                list.add(clientSn);
                count = redisLimitUtils.redisUtils.increment(incrementKey, leftSeconds());
            }
        }

        redisLimitUtils.redisUtils.put(key, list, leftSeconds());

        if (count > redisLimitUtils.bizConfig.getClientCount())
            return true;
        else
            return false;
    }

    /**
     * 自然日内连续登录5个新的终端设备
     * 清楚登录异常信息,需要通过安全校验才可以登陆
     * @param key--userId
     */
    public static void delNewClienLockCount(String key) {
        key = Constants.CLIENT_COUNT_PREFIX + key;
        redisLimitUtils.redisUtils.evict(key);
        String incrementKey = key + Constants.COMMON_SUFFIX;
        redisLimitUtils.redisUtils.evict(incrementKey);
    }

    /**
     * 判断是否异常（15分钟内最多连续请求5次验证码）
     * @param count--累加的次数
     * @return
     */
    public static boolean isTimeLimitSMS(Long count) {
        if (null == count)
            count = 0L;
        if (count > redisLimitUtils.bizConfig.getClientSMSTimelimitCount())
            return true;
        else
            return false;
    }

    /**
     * 15分钟内最多连续请求5次验证码
     * @param key--phone
     * @return
     */
    public static Long addTimeLimitSMSCount(String key, String type) {
        key = Constants.SMS_TIMELIMIT_COUNT_PREFIX + key;
        Long count = redisLimitUtils.redisUtils.increment(key, 15 * 60);

        return count;
    }

    /**
     * 15分钟内最多连续请求5次验证码
     * @param key--phone
     */
    public static void delTimeLimitSMSCount(String key) {
        key = Constants.SMS_TIMELIMIT_COUNT_PREFIX + key;
        redisLimitUtils.redisUtils.evict(key);
    }

    /**
     * 判断短信次数异常(自然日内连续请求15次短信服务，判断账号异常，需要通过安全校验才可以登陆)
     * @param count--当前异常次数
     * @return
     */
    public static boolean isSMSLimited(Long count) {
        if (null == count)
            count = 0L;
        if (count > redisLimitUtils.bizConfig.getClientSMSCount())
            return true;
        else
            return false;
    }

    /**
     * 自然日内连续请求15次短信服务，判断账号异常，需要通过安全校验才可以登陆
     * @param key --phone
     * @return
     */
    public static Long addSMSCount(String key, String type) {
        key = Constants.SMS_COUNT_PREFIX + key;
        Long count = redisLimitUtils.redisUtils.increment(key, leftSeconds());

        return count;
    }

    /**
     * 自然日内连续登录5个新的终端设备
     * 清楚登录异常信息,需要通过安全校验才可以登陆
     * @param key--phone
     */
    public static void delSMSCount(String key) {
        key = Constants.SMS_COUNT_PREFIX + key;
        redisLimitUtils.redisUtils.evict(key);
    }

    /**
     * 设备注册异常(同一个S/N设备每日仅可对三个不同手机号进行请求注册)
     * @param count--当前注册数量
     * @return
     */
    public static boolean isSNRegisterLimited(Long count) {
        if (null == count)
            count = 0L;
        if (count > redisLimitUtils.bizConfig.getClientSNRegisterCount())
            return true;
        else
            return false;
    }

    /**
     * 同一个S/N设备每日仅可对三个不同手机号进行请求注册
     * @param key --sn
     * @return
     */
    public static Long addSNRegisterCount(String key) {
        key = Constants.SN_REGISTER_COUNT_PREFIX + key;
        Long count = redisLimitUtils.redisUtils.increment(key, leftSeconds());

        return count;
    }

    /**
     * 一分钟内只允许发送一条短信
     * @param key
     * @param type
     * @return
     */
    public static Long addSMSOneMinuteLimit(String key, String type) {
        key = Constants.SMS_ONEMINUTELIMIT_COUNT_PREFIX + key;
        Long count = redisLimitUtils.redisUtils.increment(key, 60);

        return count;
    }

    /**
     * 判断是否异常（一分钟内只允许发送一条短信）
     * @param count--累加的次数
     * @return
     */
    public static boolean isSMSOneMinuteLimit(Long count) {
        if (null == count)
            count = 0L;
        if (count > 1)
            return true;
        else
            return false;
    }
}
