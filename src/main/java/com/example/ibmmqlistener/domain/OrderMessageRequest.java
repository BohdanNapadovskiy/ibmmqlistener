package com.example.ibmmqlistener.domain;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@XmlRootElement(name = "orderMessage")
public class OrderMessageRequest implements Serializable {
    private String messageId;
    private String status;
    private String orderId;
    private String customerNumber;
    private String date;

}
