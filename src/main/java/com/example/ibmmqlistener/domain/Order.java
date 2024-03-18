package com.example.ibmmqlistener.domain;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    public static Order buildFromMessage() {

    }

}
