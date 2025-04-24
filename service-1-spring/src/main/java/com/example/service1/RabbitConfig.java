package com.example.service1;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String PING_QUEUE = "ping";
    public static final String PONG_QUEUE = "pong";

    @Bean
    public Queue pingQueue() {
        return new Queue(PING_QUEUE, false, false, false);
    }

    @Bean
    public Queue pongQueue() {
        return new Queue(PONG_QUEUE, false, false, false);
    }
}
