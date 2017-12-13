feign封装了restTemplate和ribbon
使用Spring-Cloud-Feign提供的声明式服务绑定功能
来实现对服务接口的调用。

@FeignClient("eureka-client"),可插拔
使用FeignClient注解绑定需要访问的服务实例
依然是利用ribbon维护了针对eureka-clien的服务列表信息
并通过轮询实现了客户端负载均衡。
