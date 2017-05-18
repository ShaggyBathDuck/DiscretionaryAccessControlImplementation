package com.bsk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "Zakupy")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "iddostawcy")
    private Vendor vendor;

    @NotNull
    @Column(name = "nrfaktury")
    private String invoiceNumber;


}
