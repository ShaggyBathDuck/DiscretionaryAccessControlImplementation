package com.bsk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity(name = "towary")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ware {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "kodsww")
    @Size(min = 3)
    private Integer swwCode;

    @NotNull
    @Column(name = "numeridentyfikacyjny")
    @Size(min = 1)
    private Integer idNumber;

    @NotNull
    @Column(name = "jednostkamiary")
    @Pattern(regexp = "^[a-zA-z]{1,}")
    private String unit;

    @NotNull
    @Column(name = "kategoria")
    @Pattern(regexp = "^(?=^.{2,}$)([a-zA-Z\\u0080-\\u024F]+(?:. |-| |'))*[a-zA-Z\\u0080-\\u024F]*$")
    private String category;

    @Column(name = "producent")
    @Pattern(regexp = "^(?=^.{2,}$)([a-zA-Z\\u0080-\\u024F]+(?:. |-| |'))*[a-zA-Z\\u0080-\\u024F]*$")
    private String manufacturer;

    @NotNull
    @Column(name = "nazwa")
    @Pattern(regexp = "^(?=^.{2,}$)([a-zA-Z\\u0080-\\u024F]+(?:. |-| |'))*[a-zA-Z\\u0080-\\u024F]*$")
    private String name;

    @Column(name = "opis")
    @Pattern(regexp = "^(?=^.{2,}$)([a-zA-Z\\u0080-\\u024F]+(?:. |-| |'))*[a-zA-Z\\u0080-\\u024F]*$")
    private String description;

    @NotNull
    @Column(name = "stawkavat")
    @DecimalMin(value = "1")
    @DecimalMax(value = "99")
    private BigDecimal taxRate;

    public Ware(Integer swwCode, Integer idNumber, String unit, String category, String manufacturer, String name, String description, BigDecimal taxRate) {
        this.swwCode = swwCode;
        this.idNumber = idNumber;
        this.unit = unit;
        this.category = category;
        this.manufacturer = manufacturer;
        this.name = name;
        this.description = description;
        this.taxRate = taxRate;
    }

    @Override
    public String toString() {
        return getId().toString();
    }
}
