package com.example.ibmmqlistener.domain.request;



import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
//@XmlRootElement(name = "shippingAddress")
//@XmlAccessorType(XmlAccessType.FIELD)//@XmlRootElement(name = "employee")
public class AddressRequest {

    private String name;
    private String street;
    private String city;
    private String state;
    private String zip;
}
