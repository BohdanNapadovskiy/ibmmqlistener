package com.example.ibmmqlistener.jms;

import com.example.ibmmqlistener.domain.OrderMessage;
import com.example.ibmmqlistener.exception.NotValidMessageException;
import com.example.ibmmqlistener.service.repository.OrderRepository;
import com.example.ibmmqlistener.validation.MessageValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;


@Slf4j
@Component
@RequiredArgsConstructor
public class JMSListener {
    private final MessageValidator validator;
    private final OrderRepository repository;
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper mapper;


    @JmsListener(destination = "ORDER.REQUEST", containerFactory = "JmsFactory")
    @Transactional
    public void process(Message message) {
        log.info("Processing message from queue: {}", message);
        long startTime = System.nanoTime();
        try {
            validator.validateMessage(message); // Assuming validator is available in the class context.
            processMessage(message);
            logExecutionTime(startTime);
        } catch (JMSException | JsonProcessingException | NotValidMessageException e) {
            logErrorAndRethrow(e, startTime);
        }
    }


    private void processMessage(Message message) throws JMSException, JsonProcessingException {
        if (message instanceof TextMessage textMessage) {
            OrderMessage order = mapper.readValue(textMessage.getText(), OrderMessage.class);
            repository.save(order);
            jmsTemplate.convertAndSend("ORDER.RESPONSE", order);
        } else {
            throw new IllegalArgumentException("Message must be of type TextMessage");
        }
    }

    private void logExecutionTime(long startTime) {
        long endTime = System.nanoTime();
        long executionTimeInMs = (endTime - startTime) / 1_000_000;
        log.info("Total execution time: {} ms", executionTimeInMs);
    }

    private void logErrorAndRethrow(Exception e,long startTime) {
        logExecutionTime(startTime);
        log.info("Sending message  to the backout queue time {}", Instant.now());
        log.error("Error processing message: ", e);
        throw new RuntimeException("Failed to process the JMS message", e);
    }


}
