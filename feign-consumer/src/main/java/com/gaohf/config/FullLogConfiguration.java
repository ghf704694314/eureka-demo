package com.gaohf.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * com.gaohf.config
 *
 * @Author : Gaohf
 * @Description :自定义配置feign客户端日志输出级别
 * @Date : 2017/12/13
 */
@Configuration
public class FullLogConfiguration {

    /**
     * NONE:不记录任何信息
     * BASIC:仅记录请求方法、URL以及响应状态码和执行时间
     * HEADERS:除了记录BASIC级别的信息之外,还会记录请求和响应的头信息
     * FULL:记录了所有请求与响应的明细,包括头信息、请求体、元数据等
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

}
