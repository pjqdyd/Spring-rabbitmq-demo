
#### Spring + RabbitMQ实现消息队列的案例

#### 项目结构:
 ```
  ├─rabbitmq-demo                 RabbitMQ实现消息发送接收单独的demo模块
  ├─springboot-abbitmq-producer   Springboot整合RabbitMQ生产者模块
  ├─springboot-abbitmq-consumer   Springboot整合RabbitMQ消费者模块
  ├─.gitignore                    gitignore文件
  ├─docker-rabbitmq.md            Docker安装RabbitMQ的文档
  ├─README.md                     README.md文件
  └─pom.xml                       父模块pom文件
 ```

#### 如何运行:

 方法一: 运行rabbitmq-demo模块下的类主方法测试消息发送接收(设置RabbitMQ的连接信息).
 
 方法二: 
  1. 配置springboot-abbitmq-producer生产者模块下的application.yml(设置RabbitMQ的连接信息).
     运行测试类的方法发送信息
     
  2. 配置springboot-abbitmq-consumer消费者模块下的application.yml(设置RabbitMQ的连接信息).
     启动应用接收信息

 
