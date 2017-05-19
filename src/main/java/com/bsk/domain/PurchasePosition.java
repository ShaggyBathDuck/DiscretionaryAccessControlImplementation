package com.bsk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity(name = "pozycjezakupow")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchasePosition {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idzakupu")
    @NotNull
    private Purchase purchase;

    @NotNull
    @Column(name = "cena")
    @DecimalMin(value = "0.01")
    private BigDecimal price;

    @NotNull
    @Column(name = "ilosc")
    @Min(value = 1)
    private Integer amount;

    public PurchasePosition(Purchase purchase, BigDecimal price, Integer amount) {
        this.purchase = purchase;
        this.price = price;
        this.amount = amount;
    }
}
