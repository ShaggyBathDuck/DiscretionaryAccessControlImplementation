package com.bsk.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "PrzekazywanieUprawnien")
public class GrantPrivilege {

    @Column(name = "dawca")
    private User giver;

    @Column(name = "biorca")
    private User receiver;

    @Column(name = "zakupy")
    private Privilege purchase;

    @Column(name = "pozycjeZakupow")
    private Privilege purchasePosition;

    @Column(name = "towary")
    private Privilege product;

    @Column(name = "towaryMagazyn")
    private Privilege warehouseProduct;

    @Column(name = "sprzedaze")
    private Privilege sale;

    @Column(name = "pozycjeSprzedazy")
    private Privilege salePosition;

    @Column(name = "przejmij")
    private boolean take;

    public GrantPrivilege() {
    }

    public GrantPrivilege(User giver, User receiver, Privilege purchase, Privilege purchasePosition, Privilege product, Privilege warehouseProduct, Privilege sale, Privilege salePosition, boolean take) {
        this.giver = giver;
        this.receiver = receiver;
        this.purchase = purchase;
        this.purchasePosition = purchasePosition;
        this.product = product;
        this.warehouseProduct = warehouseProduct;
        this.sale = sale;
        this.salePosition = salePosition;
        this.take = take;
    }

    public User getGiver() {
        return giver;
    }

    public void setGiver(User giver) {
        this.giver = giver;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
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
