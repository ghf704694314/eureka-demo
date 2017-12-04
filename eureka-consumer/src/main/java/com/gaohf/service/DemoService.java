package com.gaohf.service;

import com.gaohf.entity.DemoEntity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * com.gaohf.service
 *
 * @Author : Gaohf
 * @Description :
 * @Date : 2017/11/30
 */
@Service
public class DemoService extends BaseService {

    @Autowired
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "fallBack")
    public String hello() {
        return restTemplate.getForEntity("http://eureka-client/hello",String.class).getBody();
    }

    @CacheResult
    @HystrixCommand(fallbackMethod = "fallBack")
    public DemoEntity getDemoById(@CacheKey("id") Long id){
        return restTemplate.getForEntity("http://eureka-client/demo/info/{1}",DemoEntity.class,id).getBody();
    }
}
