package com.gaohf.controller;

import com.gaohf.entity.User;
import org.apache.log4j.Logger;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

/**
 * com.gaohf.controller
 *
 * @Author : Gaohf
 * @Description :
 * @Date : 2017/11/28
 */
@RestController
public class BaseController {

    private final Logger logger=Logger.getLogger(this.getClass());

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/hello")
    public String index(){
        ServiceInstance instance=discoveryClient.getLocalServiceInstance();
        logger.info("/hello,host:"+instance.getHost()+",service_id:"+instance.getServiceId());
        return "hello SpringCloud";
    }

    @RequestMapping(value = "/hello1",method = RequestMethod.GET)
    public String hello(@RequestHeader String name){
        return "Hello"+name;
    }

    @RequestMapping(value = "/hello2",method = RequestMethod.GET)
    public User hello(@RequestHeader String name,@RequestHeader Integer age){
        return new User(name,age);
    }

    @RequestMapping(value = "/hello3",method = RequestMethod.POST)
    public String hello(@RequestBody User user){
        return "Hello"+user.getName()+","+user.getAge();
    }
}
