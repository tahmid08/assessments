package com.example.service1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PongListener {

    private final AmqpTemplate amqpTemplate;

    private static final Logger log = LoggerFactory.getLogger(PongListener.class);

    public PongListener(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @RabbitListener(queues = "pong")
    public void handlePong(String message) throws InterruptedException {
        log.info("Service S1: Received " + message);
        Thread.sleep(10000);
        amqpTemplate.convertAndSend("ping", "ping");
        log.info("Service S1: Sent ping");
    }
}
