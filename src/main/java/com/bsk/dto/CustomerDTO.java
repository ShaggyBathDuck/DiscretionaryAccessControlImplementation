package com.bsk.dto;

import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
public class CustomerDTO {

    @NotBlank
    @Digits(integer = 10, fraction = 0)
    private String nip;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]+$")
    private String name;

    @NotBlank
    @Digits(integer = 9, fraction = 0)
    private String phoneNumber;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]+$")
    private String street;

    @NotBlank
    @Digits(integer = 10, fraction = 0)
    private String houseNumber;

    @Digits(integer = 10, fraction = 0)
    private int flatNumber;

    @NotBlank
    @Pattern(regexp = "^[0-9]{2}-[0-9]{3}$")
    private String postalCode;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]+$")
    private String city;

    @Digits(integer = 10, fraction = 0)
    private int discount;

    public CustomerDTO(String nip, String name, String phoneNumber, String street, String houseNumber, int flatNumber, String postalCode, String city, int discount) {
        this.nip = nip;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.discount = discount;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(int flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}