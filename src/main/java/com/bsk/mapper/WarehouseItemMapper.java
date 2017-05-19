package com.bsk.mapper;

import com.bsk.domain.WarehouseItem;
import com.bsk.dto.WarehouseItemDTO;
import org.springframework.stereotype.Component;

@Component
public class WarehouseItemMapper {
    public WarehouseItem map(WarehouseItemDTO warehouseItemDTO) {
        return new WarehouseItem(warehouseItemDTO.getWare(),
                warehouseItemDTO.getSalesPrice(),
                warehouseItemDTO.getLocation(),
                warehouseItemDTO.getPurchase(),
                warehouseItemDTO.getAmount());
    }
}
