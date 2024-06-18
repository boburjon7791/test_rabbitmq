package com.example.test_rabbitmq.consumer;

import com.example.test_rabbitmq.model.Post;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PostConsumer {

    /*@RabbitListener(queues = "${queue.name.post}")
    public void consumeMessage(String message){
        System.out.println(message+" handled from consumer");
    }*/
    @RabbitListener(queues = "${queue.name.post}")
    public void consumePost(Post post){
        System.out.println(post+" handled from consumer");
    }
}
