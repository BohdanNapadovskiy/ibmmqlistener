package com.example.ibmmqlistener.domain;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
//@XmlRootElement(name = "OrderMessage")
//@XmlAccessorType(XmlAccessType.FIELD)
public class OrderMessage implements Serializable {
    private String message;
    private String identifier;


}
