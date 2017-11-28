# eureka-demo

##1.Eureka集群配置
####1.1修改hosts文件
127.0.0.1 xxx1
127.0.0.1 xxx2
####1.2运行jar
java -jar xxx.jar --spring.profiles.active=xxx1
java -jar xxx.jar --spring.profiles.active=xxx2