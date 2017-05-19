package com.bsk.mapper;

import com.bsk.domain.Purchase;
import com.bsk.dto.PurchaseDTO;
import org.springframework.stereotype.Component;

@Component
public class PurchaseMapper {

    public Purchase map(PurchaseDTO purchaseDTO) {
        return new Purchase(purchaseDTO.getDate(),
                purchaseDTO.getVendor(),
                purchaseDTO.getInvoiceNumber());
    }
}
