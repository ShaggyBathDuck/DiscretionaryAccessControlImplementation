package com.bsk.dto;


import com.bsk.domain.Privilege;
import org.hibernate.validator.constraints.NotEmpty;


public class GrantPrivilegeDTO {
    @NotEmpty
    private String receiverName;

    private Privilege client;

    private Privilege purchase;

    private Privilege purchasePosition;

    private Privilege product;

    private Privilege warehouseProduct;

    private Privilege sale;

    private Privilege salePosition;

    private boolean take;

    public GrantPrivilegeDTO() {
    }

    public GrantPrivilegeDTO(String receiverName, Privilege client, Privilege purchase, Privilege purchasePosition, Privilege product, Privilege warehouseProduct, Privilege sale, Privilege salePosition, boolean take) {
        this.receiverName = receiverName;
        this.client = client;
        this.purchase = purchase;
        this.purchasePosition = purchasePosition;
        this.product = product;
        this.warehouseProduct = warehouseProduct;
        this.sale = sale;
        this.salePosition = salePosition;
        this.take = take;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
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
