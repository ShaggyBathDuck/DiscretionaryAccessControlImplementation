package com.bsk.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    @Min(value = 1)
    private Integer amount;

    @NotNull
    @Column(name = "rabat")
    @Min(value = 1)
    @Max(value = 99)
    private Integer discount;

    public SalePosition(Sale sale, WarehouseItem warehouseItem, Integer amount, Integer discount) {
        this.sale = sale;
        this.warehouseItem = warehouseItem;
        this.amount = amount;
        this.discount = discount;
    }
}
