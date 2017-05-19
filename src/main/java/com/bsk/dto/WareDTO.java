package com.bsk.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class WareDTO {

    private Integer swwCode;

    private Integer idNumber;

    private String unit;

    private String category;

    private String manufacturer;

    private String name;

    private String description;

    private BigDecimal taxRate;
}
