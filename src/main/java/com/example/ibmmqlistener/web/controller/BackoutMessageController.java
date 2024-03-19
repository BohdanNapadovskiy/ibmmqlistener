package com.example.ibmmqlistener.web.controller;


import com.example.ibmmqlistener.jms.JmsBackoutListener;
import com.ibm.mq.MQException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BackoutMessageController {
    private final JmsBackoutListener jmsBackoutListener;

    @GetMapping(value = "/backout")
    public void createOrder() throws MQException {
        jmsBackoutListener.receiveMessage();
    }
}
