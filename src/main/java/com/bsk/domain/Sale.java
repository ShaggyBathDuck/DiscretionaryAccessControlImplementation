package com.bsk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[a-zA-z0-9-]{5,}")
    private String invoiceNumber;

    @ManyToOne
    @JoinColumn(name = "idklienta")
    @NotNull
    private Customer customer;

    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date date;

    public Sale(String invoiceNumber, Customer customer, Date date) {
        this.invoiceNumber = invoiceNumber;
        this.customer = customer;
        this.date = date;
    }

    @Override
    public String toString() {
        return getId().toString();
    }
}
