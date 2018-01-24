package com.gaohf;

//import com.gaohf.filter.AccessFilter;
import com.gaohf.filter.ZuulFiltersShowFilter;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.ZuulFilter;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@EnableZuulProxy
@SpringCloudApplication
//@SpringBootApplication
public class ApiGatewayApplication {

//	@Bean
//	public ZuulFiltersShowFilter accessFilter(){
//		return new ZuulFiltersShowFilter();
//	}

	/**
	 * 集群模式的RedissonClient
	 * @http://blog.csdn.net/csujiangyu/article/details/51005342
	 * @return
	 */
	@Bean
	public RedissonClient redissonClient(){
		Config config=new Config();
		config.useClusterServers().addNodeAddress("127.0.0.1:6379","127.0.0.1:6380","127.0.0.1:6381");
		return Redisson.create(config);
	}

	/**
	 * 创建与版本号对应的路由规则 如:/appName-v1 -->/v1/appName
	 * @return
	 */
	@Bean
	public PatternServiceRouteMapper serviceRouteMapper(){
		return new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)","${version}/${name}");
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
}
