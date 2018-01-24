package com.gaohf.config;

import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final String RSA_PUBLIC_KEY =
            "-----BEGIN PUBLIC KEY-----\n" +
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDb52+t179zitR63y+xdOFLFzv+\n" +
            "axJ33LEypK3P0LoSpP7TI/NV7TMD0DXE8dTwmX1l5Pu5Fku7i+rY18ie6IpdC67L\n" +
            "s5BGtA1cc+Cj2TShoWwmHxAv2f7Dfdcxcb+F/AP1n3gUIbe/cdnFV6gk3/Qj+32b\n" +
            "t+DEXAe7Enn4bR+wkQIDAQAB" + "-----END PUBLIC KEY-----\n";
    public static final String RSA_PRIVATE_KEY =
            "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIICXQIBAAKBgQDb52+t179zitR63y+xdOFLFzv+axJ33LEypK3P0LoSpP7TI/NV\n" +
            "7TMD0DXE8dTwmX1l5Pu5Fku7i+rY18ie6IpdC67Ls5BGtA1cc+Cj2TShoWwmHxAv\n" +
            "2f7Dfdcxcb+F/AP1n3gUIbe/cdnFV6gk3/Qj+32bt+DEXAe7Enn4bR+wkQIDAQAB\n" +
            "AoGBAL48RcG3TdlvtYciQskamrfyPqVHDXABzI1KJ08Le50bfZM8m4qvK53V5l4c\n" +
            "8yC9N90JLbadkiCJ1z2F1vmZo+9ltPLp47Nt+YvRcO4WZF203STUQ7TNRJvNGzp8\n" +
            "7aKLXcNfVuNkpuDV6RatIAy64fcTBC7wiYpK56WC56bJ6ZfBAkEA9FtmELQeSNXG\n" +
            "+EbzVYXunlneU0VDLXhWCCoSJ+QYo3XSHgSgol4E6Ej3R1A7dyaUR7R8lADGcfvS\n" +
            "tareUQAXKQJBAOZhxEZi9Xkf5/l6HGrM8om4kxjtOR11KRD8ygBJj1L/+jPzwdUf\n" +
            "pR8wHBfjzb5wT0kr7p54WvvCurkW2lhygykCQQCR64Kntufe1spauQPWroQwS7pY\n" +
            "P6Q+Iv49IeJ1r+CGvHUdN+Y1D7AehnQkQeVA4ejqaP175Hqc1qSdl8vq0wMBAkAe\n" +
            "RSxH30rHnnSZZ/0B32nixJi3Rrwhbbp2m/Bit0eYrT+PwfBu81h7z5NxZCItao5Q\n" +
            "W/BfbArwV3a3SRRpWKp5AkA4jrXdJ4oOdzqqx9eSUUbjQwM45jv6NhDSVts+Lcn2\n" +
            "WdyDsDYCHiHUrrNfapgy2pRBUioA/TPbi+zrba2lATZQ" +
            "-----END RSA PRIVATE KEY-----\n";

    public static final String WX_SNS_URL = "https://api.weixin.qq.com/sns/auth";
    public static final String CLIENT_SN = "client_sn";
    public static final String DEVICE_TYPE = "device_type";
    public static final String VERSION = "version";
    public static final String CLIENT_SOURCE = "client_source";
    public static final String IP = "client_ip";
    public static final String WXGZH = "wxgzh";

    public static final List<String> HEADER_ARRAY = Arrays.asList("authorization", CLIENT_SN, DEVICE_TYPE, VERSION, CLIENT_SOURCE, IP);

    public static final String USERNAME_PREFIX = "xg_";

    public static final String SAFESYSTEM_LOCK = "safe system lock the user";

    public static final String LOGIN_ERROR_COUNT_PREFIX = "login_error_count_";
    public static final String ACCOUNT_LOCKED_COUNT_PREFIX = "account_locked_count_";
    public static final String CLIENT_COUNT_PREFIX = "client_count_";
    public static final String SMS_COUNT_PREFIX = "sms_count_";
    public static final String SMS_TIMELIMIT_COUNT_PREFIX = "sms_time_limit_count_";
    public static final String SMS_ONEMINUTELIMIT_COUNT_PREFIX = "sms_one_minute_limit_count_";

    public static final String SN_REGISTER_COUNT_PREFIX = "sn_count_";

    public static final String COMMON_SUFFIX = "_increment";

    public static final String HISTORY_SUFFIX = "_history_increment";
}
