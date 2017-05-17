package com.bsk.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "pozycjesprzedazy")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalePosition {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idsprzedaz")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "idmagazynowe")
    private WarehouseItem warehouseItem;

    @NotNull
    @Column(name = "ilosc")
    private Integer amount;

    @NotNull
    @Column(name = "rabat")
    private Integer discount;
}
