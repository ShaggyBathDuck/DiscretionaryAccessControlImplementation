package com.bsk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Klienci")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "nip")
    private String nip;

    @Column(name = "nazwa")
    private String name;

    @Column(name = "telefon")
    private String phoneNumber;

    @Column(name = "ulica")
    private String street;

    @Column(name = "numerdomu")
    private String houseNumber;

    @Column(name = "numerlokalu")
    private int flatNumber;

    @Column(name = "kodpocztowy")
    private String postalCode;

    @Column(name = "miasto")
    private String city;

    @Column(name = "rabat")
    private int discount;
}
