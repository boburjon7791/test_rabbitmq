package com.example.test_rabbitmq.producer;

import com.example.test_rabbitmq.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostProducer {
    @Value(value = "${exchange.name.post}")
    private String exchangeName;

    @Value(value = "${routing.key.post}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    /*public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
        System.out.println(message+" send from producer");
    }*/

    public void sendPost(Post post) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, post);
        System.out.println(post+" send from producer");
    }
}
