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


@Slf4j
@Component
@RequiredArgsConstructor
public class JMSListener {
    private final MessageValidator validator;
    private final OrderRepository repository;
    private final JmsTemplate jmsTemplate;


    @JmsListener(destination = "ORDER.REQUEST", containerFactory = "JmsFactory")
    @Transactional
    public void process(Message message) {
        System.out.println(message);
        try {
            if (!validator.validateMessage(message)) {
//                jmsTemplate.convertAndSend("BACK.OUT.QUEUE",message );
                throw new NotValidMessageException("Not valid message id " + message.getJMSMessageID());
            }
            processMessage(message);
        } catch (JMSException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    private void processMessage(Message message) throws JMSException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        TextMessage textMessage = (TextMessage) message;
        OrderMessage order = mapper.readValue(textMessage.getText(), OrderMessage.class);
        repository.save(order);
        jmsTemplate.convertAndSend("ORDER.RESPONSE", order);

    }


}
