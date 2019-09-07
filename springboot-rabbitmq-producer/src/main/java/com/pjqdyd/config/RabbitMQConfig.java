package com.pjqdyd.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**   
 * @Description:  [RabbitMQ的配置类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Configuration
public class RabbitMQConfig {

    //创建队列
    @Bean(name = "itemQueue")
    public Queue itemQueue(){
        return QueueBuilder.durable("item_queue").build();
    }

    //创建交换机(使用通配符)
    @Bean(name = "itemTopicExchange")
    public Exchange itemTopicExchange(){
        return ExchangeBuilder.topicExchange("item_topic_exchange").build();
    }

    //交换机和队列绑定
    @Bean
    public Binding itemQueueBindExchange(@Qualifier("itemQueue") Queue queue,
                                         @Qualifier("itemTopicExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("item.*").noargs();
    }

}
