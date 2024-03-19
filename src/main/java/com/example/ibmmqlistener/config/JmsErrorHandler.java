package com.example.ibmmqlistener.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ErrorHandler;

@Slf4j
public class JmsErrorHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable t) {
        log.error("An error has occurred in the @JmsListener: " + t.getMessage());
    }
}
