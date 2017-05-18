package com.bsk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private Integer swwCode;

    @NotNull
    @Column(name = "numeridentyfikacyjny")
    private Integer idNumber;

    @NotNull
    @Column(name = "jednostkamiary")
    private String unit;

    @NotNull
    @Column(name = "kategoria")
    private String category;

    @Column(name = "producent")
    private String manufacturer;

    @NotNull
    @Column(name = "nazwa")
    private String name;

    @Column(name = "opis")
    private String description;

    @NotNull
    @Column(name = "stawkavat")
    private BigDecimal taxRate;
}
