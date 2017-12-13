package com.gaohf.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * com.gaohf.controller
 *
 * @Author : Gaohf
 * @Description :
 * @Date : 2017/12/13
 */
@FeignClient("eureka-client")
public interface HelloService {

    @RequestMapping("/hello")
    String hello();
}
