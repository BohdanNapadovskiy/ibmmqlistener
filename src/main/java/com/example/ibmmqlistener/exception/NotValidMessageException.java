package com.example.ibmmqlistener.exception;

public class NotValidMessageException extends RuntimeException{
    public NotValidMessageException(String errorMessage) {
        super(errorMessage);
    }
}
