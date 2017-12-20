##动态路由

启动eureka-server,config-server,api-gateway,feign-consumer,eureka-client

访问api-gateway/routes看到读取的配置
可以访问相关feign-consumer和eureka-client接口

通过config-server读取的配置可以实现动态更新的功能