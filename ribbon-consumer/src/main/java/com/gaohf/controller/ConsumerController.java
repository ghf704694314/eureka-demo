package com.gaohf.controller;

import com.gaohf.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * com.gaohf.controller
 *
 * @Author : Gaohf
 * @Description :
 * @Date : 2017/11/28
 */
@RestController
public class ConsumerController extends BaseController {

//    @Autowired
//    private RestTemplate restTemplate;
    @Autowired
    private DemoService demoService;

//    @RequestMapping(value = "/ribbon-consumer")
//    public String helloConsumer(){
//        return restTemplate.getForEntity("http://eureka-client/hello",String.class).getBody();
//    }

    @RequestMapping(value = "/ribbon-consumer")
    public String helloConsumer(){
        return demoService.hello();
    }
}
