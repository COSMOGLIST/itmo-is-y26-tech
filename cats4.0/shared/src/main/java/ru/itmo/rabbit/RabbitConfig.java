package ru.itmo.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory,
            Jackson2JsonMessageConverter messageConverter
    ) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public Queue newOwnerQueue(){
        return new Queue(QueueCommands.NEW_OWNER);
    }

    @Bean
    public Queue deleteOwnerQueue(){
        return new Queue(QueueCommands.DELETE_OWNER);
    }

    @Bean
    public Queue newCatQueue(){
        return new Queue(QueueCommands.NEW_CAT);
    }

    @Bean
    public Queue deleteCatQueue(){
        return new Queue(QueueCommands.DELETE_CAT);
    }

    @Bean
    public Queue newFriendQueue(){
        return new Queue(QueueCommands.NEW_FRIENDS);
    }

    @Bean
    public Queue dropFriendQueue(){
        return new Queue(QueueCommands.DROP_FRIENDS);
    }

    @Bean
    public Queue changeOwnerQueue(){
        return new Queue(QueueCommands.CHANGE_OWNER);
    }
}