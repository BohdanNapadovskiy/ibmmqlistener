package com.example.ibmmqlistener.config;

import jakarta.jms.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;
import org.springframework.util.ErrorHandler;


@Configuration
public class JmsConfig {
    @Bean
    public JmsListenerContainerFactory<?> JmsFactory(ConnectionFactory connectionFactory,
                                                     MessageConverter messageConverter,
                                                     JmsErrorHandler errorHandler,
                                                     JmsTransactionManager transactionManager) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        factory.setTransactionManager(transactionManager);
        factory.setSessionTransacted(true);
        factory.setErrorHandler(errorHandler);
        return factory;
    }

    @Bean
    public MessageConverter jmsMessageConverter() {
        return new SimpleMessageConverter();
    }

    @Bean
    public JmsErrorHandler errorHandler() {
        return new JmsErrorHandler();
    }

    @Bean
    public JmsTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new JmsTransactionManager(connectionFactory);
    }
}
