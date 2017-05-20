package com.bsk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

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
    @NotBlank
    @Pattern(regexp = "^((\\d{3}[- ]\\d{3}[- ]\\d{2}[- ]\\d{2})|(\\d{3}[- ]\\d{2}[- ]\\d{2}[- ]\\d{3}))$")
    private String nip;

    @Column(name = "nazwa")
    @Pattern(regexp = "^(?=^.{2,}$)([a-zA-Z\\u0080-\\u024F]+(?:. |-| |'))*[a-zA-Z\\u0080-\\u024F]*$")
    private String name;

    @Column(name = "telefon")
    @Pattern(regexp = "^[0-9]{9,9}")
    private String phoneNumber;

    @Column(name = "ulica")
    @Pattern(regexp = "^(?=^.{2,}$)([a-zA-Z\\u0080-\\u024F]+(?:. |-| |'))*[a-zA-Z\\u0080-\\u024F]*$")
    private String street;

    @Column(name = "numerdomu")
    @Digits(integer = 10, fraction = 0)
    private String houseNumber;

    @Column(name = "numerlokalu")
    @Digits(integer = 9, fraction = 0)
    private int flatNumber;

    @Column(name = "kodpocztowy")
    @Pattern(regexp = "^\\d{2}-\\d{3}$")
    private String postalCode;

    @Column(name = "miasto")
    @Pattern(regexp = "^(?=^.{2,}$)([a-zA-Z\\u0080-\\u024F]+(?:. |-| |'))*[a-zA-Z\\u0080-\\u024F]*$")
    private String city;


    public Vendor(String nip, String name, String phoneNumber, String street, String houseNumber, int flatNumber, String postalCode, String city) {
        this.nip = nip;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

    @Override
    public String toString() {
        return getId().toString();
    }

}
