package com.bsk.dto;

import com.bsk.domain.Sale;
import com.bsk.domain.WarehouseItem;
import lombok.Data;

@Data
public class SalePositionDTO {

    private Sale sale;

    private WarehouseItem warehouseItem;

    private Integer amount;

    private Integer discount;
}
