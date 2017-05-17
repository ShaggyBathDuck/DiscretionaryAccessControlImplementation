package com.bsk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity(name = "towarymagazyn")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseItem {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "idtowaru")
    private Ware ware;

    @NotNull
    @Column(name = "cenasprzedazy")
    private BigDecimal salesPrice;

    @NotNull
    @Column(name = "lokalizacja")
    private String location;

    @ManyToOne
    @JoinColumn(name = "idzakupu")
    private Purchase purchase;

    @NotNull
    @Column(name = "ilosc")
    private Integer amount;

}
