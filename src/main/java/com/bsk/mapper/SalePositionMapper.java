package com.bsk.mapper;

import com.bsk.domain.SalePosition;
import com.bsk.dto.SalePositionDTO;
import org.springframework.stereotype.Component;

@Component
public class SalePositionMapper {
    public SalePosition map(SalePositionDTO salePositionDTO) {
        return new SalePosition(salePositionDTO.getSale(),
                salePositionDTO.getWarehouseItem(),
                salePositionDTO.getAmount(),
                salePositionDTO.getDiscount());
    }
}
