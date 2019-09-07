package com.pjqdyd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**   
 * @Description:  [RabbitMQ消费者启动类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@SpringBootApplication
public class RqConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RqConsumerApplication.class, args);
    }

}
