package com.example.ibmmqlistener.domain.request;


import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@XmlRootElement(name = "order")
public class OrderRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private int orderId;
    private int customerNumber;
    private String date;
    private List<ItemRequest> items;
    private AddressRequest shippingAddress;
    private AddressRequest billingAddress;

//    private Address billingAddress;

}
