package com.pjqdyd.worker;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**   
 * @Description:  [消息消费者, 工作者模式]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class WorkerConsumerOne {

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

        //创建消费者, 并覆盖设置消息处理
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            /**
             * 消息处理
             * @param consumerTag
             * @param envelope    :消息的封装
             * @param properties  :额外参数
             * @param body        :消息信息
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                long id = envelope.getDeliveryTag();
                String exchange = envelope.getExchange();
                String routingKey = envelope.getRoutingKey();
                String message = new String(body, "UTF-8");

                System.out.println("id:" + id + ",exchange:" + exchange + ",routingKey:" + routingKey);
                System.out.println("收到的消息是:" + message);
            }
        };

        /**
         * 消息监听, 参数:
         * 1. 要监听的消息队列名称
         * 2. 消息消费应答模式
         * 3. 如何处理消息, (消费者对象)
         */
        channel.basicConsume("worker_queue", true, defaultConsumer);

        //关闭资源, (不建议, 需要一直监听消息)
        //channel.close();
        //connection.close();
    }

}
