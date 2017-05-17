package com.bsk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Dostawcy")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vendor {

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


    public Vendor(String nip, String name, String phoneNumber, String street, String houseNumber, int flatNumber, String postalCode, String city, int discount) {
        this.nip = nip;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

}
