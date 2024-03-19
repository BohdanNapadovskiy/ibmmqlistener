package com.example.ibmmqlistener.jms;


import jakarta.jms.TextMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;



@Slf4j
@Component
@RequiredArgsConstructor
public class JmsBackoutListener {

    private final JmsTemplate jmsTemplate;

    public void receiveMessage() {
        TextMessage message = (TextMessage) jmsTemplate.receive("BACK.OUT.QUEUE");
        if (message != null) {
            try {
                log.info("Received message: " + message.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            log.info("No message received within the timeout period.");
        }

    }
}
