
###     Win10下的docker安装rabbitmq (Linux下的docker类似)

1. 拉取rabbitmq镜像(带web控制界面的): 

`docker pull rabbitmq:management`

2. 查看镜像:

`docker images`

3. 在D盘创建挂载卷目录:

`D:\dockerdata\dockerrq\config`

`D:\dockerdata\dockerrq\schema`


4. 运行容器:

`docker run -d --name my_rq -p 5672:5672 -p 15672:15672 -v D:\dockerdata\dockerrq\config\:/var/lib/rabbitmq/config -v D:\dockerdata\dockerrq\schema\:/var/lib/rabbitmq/schema -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=123456 rabbitmq:management`

```
-p 映射暴露服务运行的端口（5672：应用访问端口；15672：控制台Web端口号）

-v 映射目录或文件(挂载数据目录和配置目录)；

-e 指定环境变量；（RABBITMQ_DEFAULT_USER：默认的用户名；RABBITMQ_DEFAULT_PASS：默认用户名的密码）
      RabbitMQ默认账户密码为guest/guest，在启动容器时设置变量参数改用户名admin, 密码123456
```

5. 查看运行的容器:

`docker ps`


6. 浏览器访问localhost:15672输入admin密码123456登录管理界面.
