package com.bsk.dto;


import com.bsk.domain.Purchase;
import com.bsk.domain.Ware;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WarehouseItemDTO {

    private Ware ware;

    private BigDecimal salesPrice;

    private String location;

    private Purchase purchase;

    private Integer amount;
}
