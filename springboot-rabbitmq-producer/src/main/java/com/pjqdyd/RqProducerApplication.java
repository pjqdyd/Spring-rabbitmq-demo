package com.pjqdyd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**   
 * @Description:  [RabbitMq生产者启动类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@SpringBootApplication
public class RqProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RqProducerApplication.class, args);
    }

}
