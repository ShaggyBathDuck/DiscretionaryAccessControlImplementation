package com.bsk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity(name = "Sprzedaze")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "nrfaktury")
    private String invoiceNumber;

    @ManyToOne
    @JoinColumn(name = "idklienta")
    private Customer customer;

    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date date;
}
