package com.bsk.mapper;

import com.bsk.domain.PurchasePosition;
import com.bsk.dto.PurchasePositionDTO;
import org.springframework.stereotype.Component;

@Component
public class PurchasePositionMapper {
    public PurchasePosition map(PurchasePositionDTO purchasePositionDTO) {
        return new PurchasePosition(purchasePositionDTO.getPurchase(),
                purchasePositionDTO.getPrice(),
                purchasePositionDTO.getAmount());
    }
}
