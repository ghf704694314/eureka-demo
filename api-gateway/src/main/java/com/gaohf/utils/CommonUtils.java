package com.gaohf.utils;

import com.gaohf.config.Constants;
import com.gaohf.enums.EnumClientError;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommonUtils {

    private static Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    /**
     * 获得用户远程地址
     */
    public static String getRemoteAddr(HttpServletRequest request){
        String remoteAddr = request.getHeader("X-Real-IP");
        logger.debug("X-Real-IP:{}", remoteAddr);
        if (StringUtils.isBlank(remoteAddr)) {
            remoteAddr = request.getHeader("X-Forwarded-For");
            logger.debug("X-Forwarded-For:{}", remoteAddr);
        }
        if (StringUtils.isBlank(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
            logger.debug("Proxy-Client-IP:{}", remoteAddr);
        }
        if (StringUtils.isBlank(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
            logger.debug("WL-Proxy-Client-IP:{}", remoteAddr);
        }
        return StringUtils.isNotBlank(remoteAddr) ? remoteAddr : request.getRemoteAddr();
    }

    /**
     * 微信校验token有效性
     * @param restTemplate
     * @param token
     * @param openId
     * @return
     */
    public static boolean verifyTokenFromWX(RestTemplate restTemplate, String token, String openId) {
        String url = String.format("%s?access_token=%s&openid=%s", Constants.WX_SNS_URL, token, openId);
        String str = restTemplate.getForObject(url, String.class);
        try {
            Map map = JsonMapper.getInstance().fromJson(str, Map.class);
            String code = String.valueOf(map.get("errcode"));
            if (StringUtils.equals(code, "0"))
                return true;
            else
                return false;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    /**
     * 生成纯数字的code
     * @param num-位数
     * @return
     */
    public static String getRandomCode(int num) {
        double value = Math.pow(10, num-1);
        int result = (int) Math.round(((Math.random() * 9) + 1)*value);

        return String.valueOf(result);
    }

    /**
     * 生成随机用户名
     *
     * 生成规则“xg_”加8位随机数字
     * @return
     */
    public static String generateUsername() {
        return Constants.USERNAME_PREFIX + getRandomCode(8);
    }

    /**
     * 生成随机6位数字密码
     * @return
     */
    public static String generatePassword() {
        return EntryptPwdUtils.entryptPassword(getRandomCode(6));
    }

    /**
     * 校验密码复杂度
     * @param password--8~20位的必须包含数字或字母或特殊符号中的两种
     * @return
     */
    public static boolean verifyPassword(String password) {
        // 判断密码是否包含数字：包含返回1，不包含返回0
        int i = password.matches(".*\\d+.*") ? 1 : 0;

        // 判断密码是否包含字母：包含返回1，不包含返回0
        int j = password.matches(".*[a-zA-Z]+.*") ? 1 : 0;

        // 判断密码是否包含特殊符号(~!@#$%^&*()_+|<>,.?/:;'[]{}\)：包含返回1，不包含返回0
        int k = password.matches(".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*") ? 1 : 0;

        // 判断密码长度是否在6-16位
        int l = password.length();

        if (i + j + k < 2 || l < 8 || l > 20) {
            return false;
        }

        return true;
    }

    /**
     * 校验用户名
     * @param username--大于6位的 数字或字母或汉字，不包含特殊符号
     * @return
     */
    public static boolean verifyUsername(String username) {
        // 判断密码是否包含数字：包含返回1，不包含返回0
        int i = username.matches(".*\\d+.*") ? 1 : 0;

        // 判断密码是否包含字母：包含返回1，不包含返回0
        int j = username.matches(".*[a-zA-Z]+.*") ? 1 : 0;

        int k = username.matches(".*[\\\\u4e00-\\\\u9fa5]+.*") ? 1 : 0;

        // 判断密码是否包含特殊符号(~!@#$%^&*()_+|<>,.?/:;'[]{}\)：包含返回1，不包含返回0
        int m = username.matches(".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*") ? 1 : 0;

        // 判断密码长度是否在6-16位
        int l = username.length();

        if (i + j + k < 1 || m ==1 || l < 6) {
            return false;
        }

        return true;
    }

    /**
     * 校验phone
     * @param phone
     * @return
     */
    public static boolean verifyPhone(String phone) {
        return  phone.matches("1\\d{10}");
    }

    /**
     * 校验email
     * @param email
     * @return
     */
    public static boolean verifyEmail(String email) {
        return  email.matches("\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}");
    }

    /**
     * 获取错误信息
     * @param code
     * @param msg
     * @return
     */
    public static String getSuperErrorMsg(String code, String msg) {
        Map<String, String> json = new HashMap<String, String>();
        json.put("errorCode", EnumClientError.LOCKED_ERROR.getCode());
        json.put("errorMsg", msg);

        return JsonMapper.toJsonString(json);
    }
}
