package com.gaohf;

//import com.gaohf.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringCloudApplication
//@SpringBootApplication
public class ApiGatewayApplication {

//	@Bean
//	public AccessFilter accessFilter(){
//		return new AccessFilter();
//	}

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
