package com.gaohf.service;

import com.gaohf.config.FullLogConfiguration;
import com.gaohf.fallback.HelloServiceFallback;
import org.apache.catalina.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * com.gaohf.controller
 *
 * @Author : Gaohf
 * @Description :@FeignClient配置到eureka-client实例,并配置服务降级策略和日志输出级别
 * @Date : 2017/12/13
 */
@FeignClient(value = "eureka-client",fallback = HelloServiceFallback.class,configuration = FullLogConfiguration.class)
public interface HelloService {

    @RequestMapping("/hello")
    String hello();

    @RequestMapping(value = "/hello1",method = RequestMethod.GET)
    String hello(@RequestParam("name") String name);

    @RequestMapping(value = "/hello2",method = RequestMethod.GET)
    String hello(@RequestParam("name") String name, @RequestParam("age") Integer age);

    @RequestMapping(value = "/hello3",method = RequestMethod.POST)
    String hello(@RequestBody User user);
}
