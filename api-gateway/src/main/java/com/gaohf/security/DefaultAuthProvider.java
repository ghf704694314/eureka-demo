package com.gaohf.security;

import com.gaohf.entity.UserEntity;
import com.gaohf.enums.EnumClientError;
import com.gaohf.exception.ClientException;
import com.gaohf.service.UserService;
import com.gaohf.utils.CommonUtils;
import com.gaohf.utils.EntryptPwdUtils;
import com.gaohf.utils.RedisLimitUtils;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.Map;


/**
 * com.gaohf.security
 *
 * @Author : Gaohf
 * @Description :默认的登录校验类
 * @Date : 2017/12/27
 */
public class DefaultAuthProvider implements AuthenticationProvider {

    private UserService userService;

    public DefaultAuthProvider(UserService userService){
        this.userService=userService;
    }

    /**
     * 自定义的权限校验类
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Map details = (Map) authentication.getDetails();
        String type = String.valueOf(details.get("login_type"));
        if (!StringUtils.equals(type, "userName"))
            return null;

        String name = authentication.getName();
        UserEntity user = userService.findByUserName(name);
        if (null == user) {
            throw new ClientException(EnumClientError.ACCOUNTORPWD_ERROR);
        }

        if (!EntryptPwdUtils.validatePassword(String.valueOf(authentication.getCredentials()), user.getPassword())) {
            RedisLimitUtils.addPasswordErrorCount(name);
            throw new ClientException(EnumClientError.ACCOUNTORPWD_ERROR);
        }

        boolean isNonLocked = user.isAccountNonLocked();
        boolean result = RedisLimitUtils.addNewClienLockCount(user.getUserIdStr(), String.valueOf(details.get("client_sn")));
        String msg = user.getRemarks();
        if (result) {
            msg = EnumClientError.SNLOGINED_ERROR.getMsg();
            userService.lockUserByUserId(user.getUserId(), EnumClientError.SNLOGINED_ERROR.getMsg());
            isNonLocked = false;
        }

        if (!isNonLocked) {
            throw new ClientException(EnumClientError.LOCKED_ERROR.getCode(), CommonUtils.getSuperErrorMsg(EnumClientError.LOCKED_ERROR.getCode(), msg));
        }

        return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(),
                authentication.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}