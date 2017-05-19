package com.bsk.dto;


import com.bsk.domain.Purchase;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchasePositionDTO {

    private Purchase purchase;

    private BigDecimal price;

    private Integer amount;
}
