package com.example.ibmmqlistener.validation;

import com.example.ibmmqlistener.domain.OrderMessage;
import com.example.ibmmqlistener.service.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class MessageValidator  {
    private final OrderRepository repository;

    public boolean validateMessage(Message message) throws JMSException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        TextMessage textMessage = (TextMessage) message;
        OrderMessage order = mapper.readValue(textMessage.getText(), OrderMessage.class);
        return !repository.existsById(order.getMessageId());
    }


}

