package com.example.service2

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageListener
import kotlin.concurrent.thread

fun main() {
    val factory = CachingConnectionFactory("localhost")
    val template = RabbitTemplate(factory)

    val pingQueue = "ping"
    val pongQueue = "pong"

    factory.createConnection().createChannel(true).use { channel ->
        channel.queueDeclare(pingQueue, false, false, false, null)
        channel.queueDeclare(pongQueue, false, false, false, null)
    }

    val container = SimpleMessageListenerContainer(factory)
    container.setQueueNames(pingQueue)
    container.setMessageListener(MessageListener { message: Message ->
        val body = String(message.body)
        println("Service S2: Received $body")
        thread {
            Thread.sleep(1000)
            template.convertAndSend(pongQueue, "pong")
            println("Service S2: Sent pong")
            Thread.sleep(10000)
            template.convertAndSend(pingQueue, "ping")
            println("Service S2: Sent ping")
        }
    })
    container.start()

    println("Service S2: Waiting for messages...")
    Thread.currentThread().join()
}
