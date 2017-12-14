##路由功能
建议使用面向服务的路由
path映射到某个具体的服务，url则交给Eureka的服务发现机制
去自动维护。
启动eureka-sever,eureka-client,feign-consumer,api-gateway
访问http://localhost:5555/api-a/hello,会被映射到eureka-client的/hello上
http://localhost:5555/api-b/feign-consumer,会被映射到feign-consumer的/feign-consumer上
    
##请求过滤
只需继承ZuulFilter抽象类并实现它定义的4个抽象函数
就可以完成对请求的拦截和过滤了。
在实现自定义过滤器之后，它并不会直接生效，还需要为其创建具体的Bean
才能启动该过滤器。
http://localhost:5555/api-a/hello?accessToken=token 通过accessFilter进行拦截之后
路由到eureka-client的/hello

##Cookie与信息头
默认情况下，Zuul在请求路由时会通过默认的zuul.sensitiveHeaders参数定义,过滤Cookie、
Set-Cookie、Authorization三个属性。
可以通过设置全局zuul.sensitiveHeaders为空来覆盖默认值，但不推荐
使用下面两种
1. 对指定路由开启自定义敏感头
zuul.routes.<router>.customSensitiveHeaders=true
2. 将指定路由的敏感头设置为空
zuul.routes.<router>.sensitiveHeaders=

##总结Zuul
1. 作为系统的唯一入口,屏蔽了系统内部各个微服务的细节
2. 可以与服务之力框架结合,实现自动化的服务实例维护以及负载
    均衡的路由转发。
3. 可以实现接口权限校验与微服务业务逻辑的解耦
4. 通过服务王冠中的过滤器,在各生命周期中去校验请求的内容,将原声在对外服务层
    做的校验前移,保证了微服务的无状态性,同时降低了微服务的测试难度,让服务本身
    更集中关注业务逻辑的处理