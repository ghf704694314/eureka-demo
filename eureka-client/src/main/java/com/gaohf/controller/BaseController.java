package com.gaohf.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return "index";
    }
}
