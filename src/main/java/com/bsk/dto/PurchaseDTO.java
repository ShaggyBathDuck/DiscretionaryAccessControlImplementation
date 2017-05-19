package com.bsk.dto;

import com.bsk.domain.Vendor;
import lombok.Data;

import java.util.Date;

@Data
public class PurchaseDTO {

    private Date date;

    private Vendor vendor;

    private String invoiceNumber;
}
