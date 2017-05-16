package com.bsk.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Getter
@Setter
public class CustomerDTO {

    @NotBlank
    @Pattern(regexp = "^((\\d{3}[- ]\\d{3}[- ]\\d{2}[- ]\\d{2})|(\\d{3}[- ]\\d{2}[- ]\\d{2}[- ]\\d{3}))$")
    private String nip;

    @NotBlank
    @Pattern(regexp = "^(?=^.{2,}$)([a-zA-Z\u0080-\u024F]+(?:. |-| |'))*[a-zA-Z\u0080-\u024F]*$")
    private String name;

    @NotBlank
    @Digits(integer = 9, fraction = 0)
    private String phoneNumber;

    @NotBlank
    @Pattern(regexp = "^(?=^.{2,}$)([a-zA-Z\u0080-\u024F]+(?:. |-| |'))*[a-zA-Z\u0080-\u024F]*$")
    private String street;

    @NotBlank
    @Digits(integer = 10, fraction = 0)
    private String houseNumber;

    @Digits(integer = 10, fraction = 0)
    @Min(value = 1)
    private int flatNumber;

    @NotBlank
    @Pattern(regexp = "^\\d{2}-\\d{3}$")
    private String postalCode;

    @NotBlank
    @Pattern(regexp = "^(?=^.{2,}$)([a-zA-Z\u0080-\u024F]+(?:. |-| |'))*[a-zA-Z\u0080-\u024F]*$")
    private String city;

    @Digits(integer = 2, fraction = 0)
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

}