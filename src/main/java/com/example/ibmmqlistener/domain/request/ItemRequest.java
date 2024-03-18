package com.example.ibmmqlistener.domain.request;

//import jakarta.xml.bind.annotation.XmlElement;
//import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@XmlRootElement(name = "item")
public class ItemRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private int productId;
    private int quantity;
    private double price;

}
