package com.example.ibmmqlistener.web.controller;


import com.example.ibmmqlistener.domain.OrderMessage;
import com.example.ibmmqlistener.domain.OrderMessageRequest;
import com.ibm.mq.MQException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessageController {

    private final JmsTemplate jmsTemplate;


    @PostMapping(value = "/send", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> createOrder(@RequestBody OrderMessageRequest orderMessage) throws MQException {

        log.info("Sending order message {} to the queue time {}", orderMessage.getMessageId(), Instant.now());
        jmsTemplate.convertAndSend("ORDER.REQUEST", orderMessage);
        return new ResponseEntity(orderMessage, HttpStatus.ACCEPTED);

    }
}
