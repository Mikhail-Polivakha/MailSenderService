package com.example.MailSenderService.configuration;

import lombok.Data;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@PropertySource("classpath:source.properties")
public class JmsProviderConfiguration {

    private final String queueName = "mailQueue";
    private final String exchangeTopicName = "message_queue_exchange";

    @Value("${broker.username}")
    private String jmsConnectionUsername;

    @Value("${broker.port}")
    private int jmsConnectionPort;

    @Value("${broker.password}")
    private String jmsConnectionPassword;

    @Value("${broker.key}")
    private String connectionKey;

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(exchangeTopicName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(connectionKey);
    }

    @Bean
    CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory("localhost");
        factory.setPort(jmsConnectionPort);
        factory.setUsername(jmsConnectionUsername);
        factory.setPassword(jmsConnectionPassword);
        return factory;
    }

    @Bean
    SimpleMessageListenerContainer listenerContainer(ConnectionFactory connectionFactory,
                                                     MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
        listenerContainer.setConnectionFactory(connectionFactory);
        listenerContainer.setQueueNames(queueName);
        listenerContainer.setMessageListener(listenerAdapter);
        listenerContainer.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return listenerContainer;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(CustomEmailEventListenerAdapter listenerAdapter) {
        return new MessageListenerAdapter(listenerAdapter, "getUserFromQueueToSentEmail");
    }
}
