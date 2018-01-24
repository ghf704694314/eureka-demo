package com.gaohf.enums;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shade on 2017/7/26.
 */
public enum EnumClientError {

    SUCCESS("0", "success"),
    UNKNOW_ERROR("-1000", "unknow"),
    LOCKED_ERROR("-1", "您的账号存在异常已被冻结，请及时联系平台客服人员"),
    PARAMS_ERROR("-2", "参数错误"),
    PASSWORDEXPIRED_ERROR("-3", "您的密码频繁输入错误，请30分钟后再尝试"),
    SMSTYPE_ERROR("-4", "未知的短信类型"),
    UNLOGINED_ERROR("-5", "登录过期"),
    TYPE_ERROR("-6", "未知的类型"),
    USERBINDED_ERROR("-7", "该账号已被绑定"),
    PHONENOTFOUND_ERROR("-8", "手机号未注册"),
    SMSCOUNT_ERROR("-9", "当前账号今日已频繁获取验证码"),
    PHONEBIND_ERROR("-10", "请使用绑定手机号解绑"),
    PHONEREGISTERED_ERROR("-11", "该手机号已被注册"),
    USERREGISTERED_ERROR("-12", "用户已存在"),
    SNREGISTER_ERROR("-13", "当前设备今日已多次注册"),
    USERNAME_ERROR("-14", "用户名不符合规则"),
    EMAIL_ERROR("-15", "邮箱不符合规则"),
    PHONE_ERROR("-16", "手机号不符合规则"),
    ACCOUNT_EXISTED_ERROR("-17", "账号已存在"),
    CREDENTIAL_ERROR("-18", "凭证错误"),
    REGISTER_DEFAULT_ERROR("-19", "该微信账号已绑定手机"),
    PHONE_ALREADY_BINDED_ERROR("-20", "该手机号已绑定公众号"),
    SIGN_ERROR("-30", "签名异常"),
    ACCOUNTNOTFOUND_ERROR("-101", "用户不存在"),
    ACCOUNTORPWD_ERROR("-102", "账号或密码错误"),
    VERIFYCODE_ERROR("-103", "验证码错误"),
    WX_ERROR("-104", "微信token无效"),
    PASSWORD_ERROR("-105", "密码格式错误，请重新输入"),
    OLDPASSWORD_ERROR("-106", "密码错误，如果您未设置过密码，请直接找回密码。"),
    NEWPASSWORD_ERROR("-107", "新密码不能与原密码相同"),
    SNLOGINED_ERROR("-108", "当前账号已频繁在多设备登录"),
    SMS_ONEMINUTE_ERROR("-109", "您的操作过于频繁，请稍后再试"),
    PASSWORD_LOCKED_ERROR("-110", "您的密码频繁输入错误，已暂停服务"),
    SMS_LOCKED_ERROR("-111", "您获取的验证码过于频繁，已暂停服务"),
    REGISTER_BANNED_ERROR("-112", "因系统升级，暂不支持该功能")
    ;

    private String code;
    private String msg;

    private EnumClientError(String name, String value){
        this.code = name;
        this.msg = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        Map<String, String> json = new HashMap<String, String>();
        json.put("errorCode", this.code);
        json.put("errorMsg", this.msg);

        return JSON.toJSONString(json);
    }
}
