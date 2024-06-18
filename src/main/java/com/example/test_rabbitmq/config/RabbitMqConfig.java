package com.example.test_rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Value(value = "${queue.name.post}")
    private String postQueueName;

    @Value(value = "${json.queue.name.post}")
    private String jsonPostQueueName;

    @Value(value = "${exchange.name.post}")
    private String postExchangeName;

    @Value(value = "${routing.key.post}")
    private String postRoutingKey;

    @Value(value = "${json.routing.key.post}")
    private String postJsonRoutingKey;
    @Bean
    public Queue queue(){
        return new Queue(postQueueName);
    }
//    exchange is common
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(postExchangeName);
    }
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(topicExchange())
                .with(postRoutingKey);
    }
//    json configurations
    @Bean
    public Queue jsonPostQueue(){
        return new Queue(jsonPostQueueName);
    }
    @Bean
    public Binding bindingJson(){
        return BindingBuilder.bind(jsonPostQueue())
                .to(topicExchange())
                .with(postJsonRoutingKey);
    }
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
