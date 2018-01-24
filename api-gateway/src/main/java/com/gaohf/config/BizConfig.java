package com.gaohf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by shade on 2017/4/6.
 */
@Configuration
@Scope("singleton")
public class BizConfig {

    private String smsUrl;
    private String smsAppkey;
    private String smsSecret;
    private String smsRegisterTemplate;
    private String smsLoginTemplate;
    private String smsBindThirdAccountTemplate;
    private String smsUnBindThirdAccountTemplate;
    private String smsforgetpasswordTemplate;
    private String smsforgetPaypasswordTemplate;
    private String smsSignName;

    private String url;
    private boolean isEncrypt;

    private boolean isBanned;

    private int pwdErrorCount;
    private long pwdErrorExpired;
    private long accountLockedExpired;
    private int accountLockedTimes;
    private int clientCount;
    private int clientSMSCount;
    private int clientSMSTimelimitCount;
    private int clientSNRegisterCount;

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public long getAccountLockedExpired() {
        return accountLockedExpired;
    }

    public void setAccountLockedExpired(long accountLockedExpired) {
        this.accountLockedExpired = accountLockedExpired;
    }

    public int getClientSNRegisterCount() {
        return clientSNRegisterCount;
    }

    public void setClientSNRegisterCount(int clientSNRegisterCount) {
        this.clientSNRegisterCount = clientSNRegisterCount;
    }

    public int getClientSMSTimelimitCount() {
        return clientSMSTimelimitCount;
    }

    public void setClientSMSTimelimitCount(int clientSMSTimelimitCount) {
        this.clientSMSTimelimitCount = clientSMSTimelimitCount;
    }

    public String getSmsUnBindThirdAccountTemplate() {
        return smsUnBindThirdAccountTemplate;
    }

    public void setSmsUnBindThirdAccountTemplate(String smsUnBindThirdAccountTemplate) {
        this.smsUnBindThirdAccountTemplate = smsUnBindThirdAccountTemplate;
    }

    public String getSmsBindThirdAccountTemplate() {
        return smsBindThirdAccountTemplate;
    }

    public void setSmsBindThirdAccountTemplate(String smsBindThirdAccountTemplate) {
        this.smsBindThirdAccountTemplate = smsBindThirdAccountTemplate;
    }

    public int getClientSMSCount() {
        return clientSMSCount;
    }

    public void setClientSMSCount(int clientSMSCount) {
        this.clientSMSCount = clientSMSCount;
    }

    public String getSmsUrl() {
        return smsUrl;
    }

    public void setSmsUrl(String smsUrl) {
        this.smsUrl = smsUrl;
    }

    public String getSmsAppkey() {
        return smsAppkey;
    }

    public void setSmsAppkey(String smsAppkey) {
        this.smsAppkey = smsAppkey;
    }

    public String getSmsSecret() {
        return smsSecret;
    }

    public void setSmsSecret(String smsSecret) {
        this.smsSecret = smsSecret;
    }

    public String getSmsRegisterTemplate() {
        return smsRegisterTemplate;
    }

    public void setSmsRegisterTemplate(String smsRegisterTemplate) {
        this.smsRegisterTemplate = smsRegisterTemplate;
    }

    public String getSmsLoginTemplate() {
        return smsLoginTemplate;
    }

    public void setSmsLoginTemplate(String smsLoginTemplate) {
        this.smsLoginTemplate = smsLoginTemplate;
    }

    public String getSmsforgetpasswordTemplate() {
        return smsforgetpasswordTemplate;
    }

    public void setSmsforgetpasswordTemplate(String smsforgetpasswordTemplate) {
        this.smsforgetpasswordTemplate = smsforgetpasswordTemplate;
    }

    public String getSmsforgetPaypasswordTemplate() {
        return smsforgetPaypasswordTemplate;
    }

    public void setSmsforgetPaypasswordTemplate(String smsforgetPaypasswordTemplate) {
        this.smsforgetPaypasswordTemplate = smsforgetPaypasswordTemplate;
    }

    public String getSmsSignName() {
        return smsSignName;
    }

    public void setSmsSignName(String smsSignName) {
        this.smsSignName = smsSignName;
    }

    public int getClientCount() {
        return clientCount;
    }

    public void setClientCount(int clientCount) {
        this.clientCount = clientCount;
    }

    public int getPwdErrorCount() {
        return pwdErrorCount;
    }

    public void setPwdErrorCount(int pwdErrorCount) {
        this.pwdErrorCount = pwdErrorCount;
    }

    public long getPwdErrorExpired() {
        return pwdErrorExpired;
    }

    public void setPwdErrorExpired(long pwdErrorExpired) {
        this.pwdErrorExpired = pwdErrorExpired;
    }

    public int getAccountLockedTimes() {
        return accountLockedTimes;
    }

    public void setAccountLockedTimes(int accountLockedTimes) {
        this.accountLockedTimes = accountLockedTimes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isEncrypt() {
        return isEncrypt;
    }

    public void setEncrypt(boolean encrypt) {
        isEncrypt = encrypt;
    }
}
