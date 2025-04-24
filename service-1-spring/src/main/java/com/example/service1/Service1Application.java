package com.example.service1;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Service1Application {
    public static void main(String[] args) {
        SpringApplication.run(Service1Application.class, args);
    }

    @Bean
    public CommandLineRunner runner(AmqpTemplate amqpTemplate) {

        return args -> {
            amqpTemplate.convertAndSend("ping", "ping");
            System.out.println("Service S1: Sent ping");
        };
    }
}
