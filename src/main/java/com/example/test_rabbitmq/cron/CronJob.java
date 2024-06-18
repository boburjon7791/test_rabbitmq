package com.example.test_rabbitmq.cron;

import com.example.test_rabbitmq.model.Post;
import com.example.test_rabbitmq.producer.PostProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class CronJob {
    private final PostProducer postProducer;
/*    @Scheduled(cron = "0,10,20,30,40,50 * * * * *")
    public void sendMessage(){
        System.out.println("cron job is working");
        postProducer.sendMessage("Hello World from cron job");
    }*/
    @Scheduled(cron = "0,10,20,30,40,50 * * * * *")
    public void sendPost(){
        System.out.println("cron job is working");
        postProducer.sendPost(
                Post.builder()
                .id(0L)
                .title("hello")
                .body("hello body post")
                .createdAt(LocalDateTime.now())
                .build()
        );
    }
}
