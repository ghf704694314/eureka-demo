package com.gaohf.service;

import com.alibaba.fastjson.JSONObject;
import com.gaohf.entity.DemoEntity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
    @HystrixCollapser(batchMethod = "findAll",collapserProperties = {
            @HystrixProperty(name="timerDelayInMilliseconds",value = "100")
    })
    public DemoEntity getDemoById(@CacheKey("id") Long id){
        String jsonString=restTemplate.getForEntity("http://eureka-client/demo/{1}",String.class,id).getBody();
        return JSONObject.parseObject(jsonString,DemoEntity.class);
    }

    @CacheRemove(commandKey = "getDemoById")
    @HystrixCommand(fallbackMethod = "fallBack")
    public String update(@CacheKey("id") DemoEntity demoEntity){
        return restTemplate.postForObject("http://eureka-client/demo/update",demoEntity,String.class);
    }

    @HystrixCommand(fallbackMethod = "fallBack")
    public List<DemoEntity> findAll(List<Long> ids){
        return restTemplate.getForObject("http://eureka-client/demo?ids={1}",List.class, StringUtils.join(ids,","));
    }

}
