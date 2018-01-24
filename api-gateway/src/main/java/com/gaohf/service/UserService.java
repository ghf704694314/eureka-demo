package com.gaohf.service;

import com.gaohf.entity.UserEntity;
import com.gaohf.redis.RedisUtils;
import com.gaohf.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * com.gaohf.service
 *
 * @Author : Gaohf
 * @Description :
 * @Date : 2017/12/27
 */
@Service
public class UserService {

//    @Autowired
//    private LoginInfoRepository loginInfoRepository;

//    @Autowired
//    private UserExtService userExtService;

//    @Autowired
//    private DeviceInfoService deviceInfoService;

    @Autowired
    private RedisTokenStore redisTokenStore;
//
//    @Autowired
//    private RabbitMqService rabbitMqService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserRepository userRepository;

    public UserEntity findByUserName(String username) {
        UserEntity user = redisUtils.get(username, UserEntity.class);
        if (null == user) {
            user = userRepository.findByUsernameAndEnabled(username, UserEntity.enable);
            updateUserCache(user);
        }

        return user;
    }

    @Transactional(readOnly = false)
    public void lockUserByName(String name, String remarks) {
        int count = userRepository.updateByUsername(name, remarks);
        if (count > 0) {
            UserEntity user = findByUserName(name);
            updateLockedUser(user, remarks);
            return;
        }
        count = userRepository.updateByEmail(name, remarks);
        if (count > 0) {
            UserEntity user = findByEmail(name);
            updateLockedUser(user, remarks);
            return;
        }
        count = userRepository.updateByOpenId(name, remarks);
        if (count > 0) {
            UserEntity user = findByOpenId(name);
            updateLockedUser(user, remarks);
            return;
        }
        count = userRepository.updateByPhone(name, remarks);
        if (count > 0) {
            UserEntity user = findByPhone(name);
            updateLockedUser(user, remarks);
            return;
        }
    }

    public UserEntity findByUserId(long userId){
        UserEntity user = redisUtils.get(String.valueOf(userId), UserEntity.class);
        if (null == user) {
            user = userRepository.findByUserIdAndEnabled(userId,UserEntity.enable);
            updateUserCache(user);
        }

        return user;
    }

    private void updateLockedUser(UserEntity user, String remarks) {
        user.setAccountNonLocked(false);
        user.setRemarks(remarks);
        updateUserCache(user);
    }

    public UserEntity findByEmail(String email) {
        UserEntity user = redisUtils.get(email, UserEntity.class);
        if (null == user) {
            user = userRepository.findByEmailAndEnabled(email, UserEntity.enable);
            updateUserCache(user);
        }

        return user;
    }

    public UserEntity findByOpenId(String openId) {
        UserEntity user = redisUtils.get(openId, UserEntity.class);
        if (null == user) {
            user = userRepository.findByOpenIdAndEnabled(openId, UserEntity.enable);
            updateUserCache(user);
        }

        return user;
    }

    public UserEntity findByPhone(String phone) {
        UserEntity user = redisUtils.get(phone, UserEntity.class);
        if (null == user) {
            user = userRepository.findByPhoneAndEnabled(phone, UserEntity.enable);
            updateUserCache(user);
        }

        return user;
    }

    @Transactional(readOnly = false)
    public void lockUserByUserId(long userId, String remarks) {
        int count = userRepository.updateByUserId(userId, remarks);
        if (count > 0) {
            UserEntity user = findByUserId(userId);
            updateLockedUser(user, remarks);
            return;
        }
    }

    /**
     * 设置用户缓存
     * @param user
     */
    private void updateUserCache(UserEntity user) {
        if (null == user)
            return;
        String key = user.getUserIdStr();
        if (StringUtils.isNotBlank(key))
            redisUtils.put(key, user);
    }
}
