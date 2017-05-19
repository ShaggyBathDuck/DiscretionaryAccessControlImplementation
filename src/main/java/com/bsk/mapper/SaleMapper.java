package com.bsk.mapper;

import com.bsk.domain.Sale;
import com.bsk.dto.SaleDTO;
import org.springframework.stereotype.Component;

@Component
public class SaleMapper {

    public Sale map(SaleDTO saleDTO) {
        return new Sale(saleDTO.getInvoiceNumber(),
                saleDTO.getCustomer(),
                saleDTO.getDate());
    }
}
