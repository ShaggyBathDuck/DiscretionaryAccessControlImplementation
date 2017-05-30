package com.bsk.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "przekazywanieuprawnien")
public class GrantPrivilege {

    @EmbeddedId
    private GrantPrivilegePK grantPrivilegePK;

    @ManyToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "klienci")
    private Privilege customer;

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
    private Privilege ware;

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

    @ManyToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "dostawcy")
    private Privilege vendor;

    @Column(name = "przejmij")
    private boolean take;


    public GrantPrivilege() {
    }

    public GrantPrivilege(GrantPrivilegePK grantPrivilegePK, Privilege customer, Privilege purchase, Privilege purchasePosition, Privilege ware, Privilege warehouseProduct, Privilege sale, Privilege salePosition, Privilege vendor, boolean take) {
        this.grantPrivilegePK = grantPrivilegePK;
        this.customer = customer;
        this.purchase = purchase;
        this.purchasePosition = purchasePosition;
        this.ware = ware;
        this.warehouseProduct = warehouseProduct;
        this.sale = sale;
        this.salePosition = salePosition;
        this.vendor = vendor;
        this.take = take;
    }

    public boolean getTake() {
        return take;
    }

    public void setTake(boolean take) {
        this.take = take;
    }

    public boolean isAdmin(){
        return this.grantPrivilegePK.isAdmin();
    }

    public boolean hasAnyGrantRight() {
        if (customer.hasAnyGrantRight() ||
                purchase.hasAnyGrantRight() ||
                purchasePosition.hasAnyGrantRight() ||
                ware.hasAnyGrantRight() ||
                warehouseProduct.hasAnyGrantRight() ||
                sale.hasAnyGrantRight() ||
                salePosition.hasAnyGrantRight() ||
                vendor.hasAnyGrantRight()) return true;
        return false;
    }

    public User getReceiver() {
        return this.grantPrivilegePK.getReceiver();
    }

    public User getGiver() {
        return this.grantPrivilegePK.getGiver();
    }
}
