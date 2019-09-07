package com.pjqdyd.routing;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**   
 * @Description:  [消息生产者, 路由模式]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class RoutingProducer {

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
        channel.queueDeclare("routing_queue_1", true, false, false, null);
        channel.queueDeclare("routing_queue_2", true, false, false, null);

        /**
         * 声明交换机, 参数:
         * 1. 交换机名称
         * 2. 交换机类型
         */
        channel.exchangeDeclare("routing_exchange", BuiltinExchangeType.DIRECT);

        /**
         * 绑定操作, 参数:
         * 1. 队列名称
         * 2. 交换机名称
         * 3. routingKey
         */
        channel.queueBind("routing_queue_1", "routing_exchange", "user.add");

        channel.queueBind("routing_queue_2", "routing_exchange", "item.delete");
        channel.queueBind("routing_queue_2", "routing_exchange", "item.update");
        //提示(通配符模式):设置交换机类型为topic 路由key可以用通配符item.*, item.#匹配所有

        //创建消息
        String message1 = "Hello rabbitmq routing_exchange 用户添加!";
        String message2 = "Hello rabbitmq routing_exchange 列表删除!";
        String message3 = "Hello rabbitmq routing_exchange 列表更新!";

        /**
         * 消息发送, 参数:
         * 1. 消息发送使用的交换机对象, 默认Default Exchange
         * 2. 当前消息路由地址, 简单消息模式,路由地址可以直接写队列地址,
         * 3. 附加参数
         * 4. 消息
         */
        channel.basicPublish("routing_exchange", "user.add",null, message1.getBytes());
        channel.basicPublish("routing_exchange", "item.delete",null, message2.getBytes());
        channel.basicPublish("routing_exchange", "item.update",null, message3.getBytes());

        //关闭资源
        channel.close();
        connection.close();
    }

}
