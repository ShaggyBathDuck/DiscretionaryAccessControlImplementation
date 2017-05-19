package com.bsk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @DecimalMin(value = "0.01")
    private BigDecimal salesPrice;

    @NotNull
    @Column(name = "lokalizacja")
    @Pattern(regexp = "^(?=^.{2,}$)([a-zA-Z\u0080-\u024F]+(?:. |-| |'))*[a-zA-Z\u0080-\u024F]*$")
    private String location;

    @ManyToOne
    @JoinColumn(name = "idzakupu")
    private Purchase purchase;

    @NotNull
    @Column(name = "ilosc")
    @Min(value = 1)
    private Integer amount;

    public WarehouseItem(Ware ware, BigDecimal salesPrice, String location, Purchase purchase, Integer amount) {
        this.ware = ware;
        this.salesPrice = salesPrice;
        this.location = location;
        this.purchase = purchase;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return getId().toString();
    }
}
