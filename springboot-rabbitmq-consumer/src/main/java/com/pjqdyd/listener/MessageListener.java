package com.pjqdyd.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**   
 * @Description:  [RabbitMQ消息监听者对象]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Component
public class MessageListener {

    @RabbitListener(queues = {"item_queue"})
    public void getMessage(String message){
        System.out.println("收到的消息:" + message);
    }

}
