package com.bsk.dto;

import lombok.Data;

@Data
public class CustomerDTO {

    private String nip;

    private String name;

    private String phoneNumber;

    private String street;

    private String houseNumber;

    private int flatNumber;

    private String postalCode;

    private String city;

    private int discount;

}