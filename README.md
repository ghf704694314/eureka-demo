# eureka-demo

##1.Eureka集群配置
####1.1修改hosts文件
127.0.0.1 xxx1
127.0.0.1 xxx2
####1.2运行jar
java -jar xxx.jar --spring.profiles.active=xxx1
java -jar xxx.jar --spring.profiles.active=xxx2


##demo启动步骤
1.注册中心 eureka-server
2.服务提供者和消费者eureka-client ribbon-consumer
3.断路器仪表盘hystrix-dashboard 集群监控turbine