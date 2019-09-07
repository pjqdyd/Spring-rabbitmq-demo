package com.pjqdyd.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**   
 * @Description:  [消息生产者, 简单模式]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建链接工厂对象
        ConnectionFactory connectionFactory = new ConnectionFactory();

        //设置Rabbitmq服务主机地址,默认localhost
        connectionFactory.setHost("localhost");

        //设置Rabbitmq服务端口,默认5672
        connectionFactory.setPort(5672);

        //设置虚拟主机名字,默认/
        connectionFactory.setVirtualHost("/");

        //设置连接用户, 默认guest
        connectionFactory.setUsername("admin");

        //设置连接密码, 默认guest
        connectionFactory.setPassword("123456");

        //创建链接
        Connection connection = connectionFactory.newConnection();

        //创建频道
        Channel channel = connection.createChannel();

        /**
         * 声明队列, 参数:
         * 1. 队列的名称, 2. 消息是否持久化,
         * 3. 当前消息是否属于当连接对象独有,
         * 4. 消息使用后是否删除, 5. 附加参数
         */
        channel.queueDeclare("test_queue", true, false, false, null);

        //创建消息
        String message = "Hello rabbitmq!";

        /**
         * 消息发送, 参数:
         * 1. 消息发送使用的交换机对象, 默认Default Exchange
         * 2. 当前消息路由地址, 简单消息模式,路由地址可以直接写队列地址
         * 3. 附加参数
         * 4. 消息
         */
        channel.basicPublish("", "test_queue", null, message.getBytes());


        //关闭资源
        channel.close();
        connection.close();
    }

}
