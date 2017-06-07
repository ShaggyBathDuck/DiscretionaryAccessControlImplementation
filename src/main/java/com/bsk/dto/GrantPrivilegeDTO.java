package com.bsk.dto;


import com.bsk.domain.Privilege;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
public class GrantPrivilegeDTO {
    @NotEmpty
    private String receiverName;

    private Privilege customer;

    private Privilege purchase;

    private Privilege purchasePosition;

    private Privilege ware;

    private Privilege warehouseProduct;

    private Privilege sale;

    private Privilege salePosition;

    private Privilege vendor;

    private boolean take;

    public GrantPrivilegeDTO() {
    }

    public GrantPrivilegeDTO(String receiverName, Privilege customer, Privilege purchase, Privilege purchasePosition, Privilege ware, Privilege warehouseProduct, Privilege sale, Privilege salePosition, Privilege vendor, boolean take) {
        this.receiverName = receiverName;
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
}
