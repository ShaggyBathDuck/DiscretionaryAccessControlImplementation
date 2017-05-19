package com.bsk.dto;

import com.bsk.domain.Customer;
import lombok.Data;

import java.util.Date;

@Data
public class SaleDTO {

    private String invoiceNumber;

    private Customer customer;

    private Date date;
}
