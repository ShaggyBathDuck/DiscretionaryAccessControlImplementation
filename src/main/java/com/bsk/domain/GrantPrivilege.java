package com.bsk.domain;

import lombok.Getter;
import lombok.Setter;

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

    public GrantPrivilege() {
    }

    public GrantPrivilege(GrantPrivilegePK grantPrivilegePK, Privilege client, Privilege purchase, Privilege purchasePosition, Privilege product, Privilege warehouseProduct, Privilege sale, Privilege salePosition, boolean take) {
        this.grantPrivilegePK = grantPrivilegePK;
        this.client = client;
        this.purchase = purchase;
        this.purchasePosition = purchasePosition;
        this.product = product;
        this.warehouseProduct = warehouseProduct;
        this.sale = sale;
        this.salePosition = salePosition;
        this.take = take;
    }

    public GrantPrivilegePK getGrantPrivilegePK() {
        return grantPrivilegePK;
    }

    public void setGrantPrivilegePK(GrantPrivilegePK grantPrivilegePK) {
        this.grantPrivilegePK = grantPrivilegePK;
    }

    public Privilege getClient() {
        return client;
    }

    public void setClient(Privilege client) {
        this.client = client;
    }

    public Privilege getPurchase() {
        return purchase;
    }

    public void setPurchase(Privilege purchase) {
        this.purchase = purchase;
    }

    public Privilege getPurchasePosition() {
        return purchasePosition;
    }

    public void setPurchasePosition(Privilege purchasePosition) {
        this.purchasePosition = purchasePosition;
    }

    public Privilege getProduct() {
        return product;
    }

    public void setProduct(Privilege product) {
        this.product = product;
    }

    public Privilege getWarehouseProduct() {
        return warehouseProduct;
    }

    public void setWarehouseProduct(Privilege warehouseProduct) {
        this.warehouseProduct = warehouseProduct;
    }

    public Privilege getSale() {
        return sale;
    }

    public void setSale(Privilege sale) {
        this.sale = sale;
    }

    public Privilege getSalePosition() {
        return salePosition;
    }

    public void setSalePosition(Privilege salePosition) {
        this.salePosition = salePosition;
    }

    public boolean isTake() {
        return take;
    }

    public void setTake(boolean take) {
        this.take = take;
    }
}
