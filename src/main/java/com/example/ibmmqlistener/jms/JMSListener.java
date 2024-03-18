package com.example.ibmmqlistener.jms;

import com.example.ibmmqlistener.domain.Order;
import com.example.ibmmqlistener.service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jmx.JmxException;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.jms.BytesMessage;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.HashMap;


@Slf4j
@Component
@RequiredArgsConstructor
public class JMSListener {
    private OrderRepository repository;
    private final JmsTemplate jmsTemplate;
//    private final OrderRepository repository;
//    private final JMSProducer producer;

    //    @JmsListener(destination = "ORDER.REQUEST")
    @JmsListener(destination = "ORDER.REQUEST", containerFactory = "myJmsFactory")
    public void process(MapMessage message) {
        if (!validMessage(message)) {

        }
        repository.save(Order.buildFromMessage(message));
        jmsTemplate.convertAndSend();
        System.out.println(message);
    }

    private boolean validMessage(MapMessage message) {
        return false;
    }
}
