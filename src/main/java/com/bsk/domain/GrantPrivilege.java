package com.bsk.domain;

import javax.persistence.*;

@Entity(name = "przekazywanieuprawnien")
public class GrantPrivilege {

    @EmbeddedId
    private GrantPrivilegePK grantPrivilegePK;


    @ManyToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "klienci")
    private Privilege client;

    @ManyToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "zakupy")
    private Privilege purchase;

    @ManyToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "pozycjezakupow")
    private Privilege purchasePosition;

    @ManyToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "towary")
    private Privilege product;

    @ManyToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "towarymagazyn")
    private Privilege warehouseProduct;

    @ManyToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "sprzedaze")
    private Privilege sale;

    @ManyToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "pozycjesprzedazy")
    private Privilege salePosition;

    @Column(name = "przejmij")
    private boolean take;

}
