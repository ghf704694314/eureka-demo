package com.gaohf.exception;/**
 * Created by hanyong on 2017/1/4.
 */


import com.gaohf.enums.EnumClientError;
import org.springframework.security.core.AuthenticationException;

/**
 * @author hanyong
 * @Date 2017/1/4
 */
public class ClientException extends AuthenticationException {
    private String errorCode;
    private String errorMsg;

    public ClientException(String codeEnum, String errorMsg, Throwable cause) {
        super(errorMsg);
        this.errorCode = codeEnum;
        this.errorMsg = errorMsg;
    }
    public ClientException(String codeEnum, String errorMsg) {
        super(errorMsg);
        this.errorCode = codeEnum;
        this.errorMsg = errorMsg;
    }

    public ClientException(EnumClientError enumClientError) {
        super(enumClientError.toString());
        this.errorCode = enumClientError.getCode();
        this.errorMsg = enumClientError.getMsg();
    }

    public ClientException(String errorDescription) {
        super(errorDescription);
        this.errorMsg = errorDescription;
    }

    public String getCodeEnum() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
