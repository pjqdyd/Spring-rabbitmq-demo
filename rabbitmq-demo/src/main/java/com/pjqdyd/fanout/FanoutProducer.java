package com.pjqdyd.fanout;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**   
 * @Description:  [消息生产者, 发布订阅模式]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class FanoutProducer {

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建链接工厂对象
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("123456");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        /**
         * 声明2个队列, 参数:
         * 1. 队列的名称, 2. 消息是否持久化,
         * 3. 当前消息是否属于当连接对象独有,
         * 4. 消息使用后是否删除, 5. 附加参数
         */
        channel.queueDeclare("fanout_queue_1", true, false, false, null);
        channel.queueDeclare("fanout_queue_2", true, false, false, null);

        /**
         * 声明交换机, 参数:
         * 1. 交换机名称
         * 2. 交换机类型
         */
        channel.exchangeDeclare("fanout_exchange", BuiltinExchangeType.FANOUT);

        /**
         * 绑定操作, 参数:
         * 1. 队列名称
         * 2. 交换机名称
         * 3. routingKey
         */
        channel.queueBind("fanout_queue_1", "fanout_exchange", "");
        channel.queueBind("fanout_queue_2", "fanout_exchange", "");

        //创建消息
        String message = "Hello rabbitmq fanout_exchange!";

        /**
         * 消息发送, 参数:
         * 1. 消息发送使用的交换机对象, 默认Default Exchange
         * 2. 当前消息路由地址, 简单消息模式,路由地址可以直接写队列地址,
         * 3. 附加参数
         * 4. 消息
         */
        channel.basicPublish("fanout_exchange", "",null, message.getBytes());

        //关闭资源
        channel.close();
        connection.close();
    }

}
