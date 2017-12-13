package com.gaohf.fallback;

import com.gaohf.service.HelloService;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * com.gaohf.fallback
 *
 * @Author : Gaohf
 * @Description :服务降级类
 * @Date : 2017/12/13
 */
public class HelloServiceFallback implements HelloService {
    @Override
    public String hello() {
        return "error";
    }

    @Override
    public String hello(@RequestParam("name") String name) {
        return "error";
    }

    @Override
    public String hello(@RequestHeader("name") String name,@RequestHeader("age") Integer age) {
        return "error:name="+name+",age="+age;
    }

    @Override
    public String hello(User user) {
        return "error";
    }
}
