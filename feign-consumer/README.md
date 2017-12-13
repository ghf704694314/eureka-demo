feign封装了restTemplate和ribbon
使用Spring-Cloud-Feign提供的声明式服务绑定功能
来实现对服务接口的调用。

@FeignClient("eureka-client"),可插拔
使用FeignClient注解绑定需要访问的服务实例
依然是利用ribbon维护了针对eureka-clien的服务列表信息
并通过轮询实现了客户端负载均衡。

请求压缩
feign支持对请求与响应压缩

日志配置
logging.level.<FeignClient>:DEBUG <FeginClient>为接口完整路径
另外还需掉正Logging.Level日志级别，默认为NONE
可以在启动类上创建Bean,也可以通过实现配置类，然后再具体的Feign
客户端来指定配置类以实现是否要调整不同的日志级别