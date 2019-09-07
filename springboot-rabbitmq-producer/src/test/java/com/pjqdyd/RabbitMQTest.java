package com.pjqdyd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**   
 * @Description:  [RabbitMQ生产者发消息测试类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitMQTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 测试发送消息
     */
    @Test
    public void testSendMessage(){
        rabbitTemplate.convertAndSend("item_topic_exchange", "item.add", "Hello Springboot 用户添加!");
    }

}
