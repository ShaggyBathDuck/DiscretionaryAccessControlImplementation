package com.bsk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private Purchase purchase;

    @NotNull
    @Column(name = "cena")
    private BigDecimal price;

    @NotNull
    @Column(name = "ilosc")
    private Integer amount;

}
