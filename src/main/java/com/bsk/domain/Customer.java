package com.bsk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity(name = "Klienci")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Size(min = 13, max = 13)
    @Column(name = "nip")
    private String nip;

    @Column(name = "nazwa")
    private String name;

    @Size(min = 9, max = 9)
    @Column(name = "telefon")
    private String phoneNumber;

    @Size(min = 2)
    @Column(name = "ulica")
    private String street;

    @Column(name = "numerdomu")
    private String houseNumber;

    @Column(name = "numerlokalu")
    private int flatNumber;

    @Size(min = 6, max = 6)
    @Column(name = "kodpocztowy")
    private String postalCode;

    @Size(min = 3)
    @Column(name = "miasto")
    private String city;

    @Column(name = "rabat")
    private int discount;
}
