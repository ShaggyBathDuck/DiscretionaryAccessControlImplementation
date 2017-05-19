package com.bsk.mapper;

import com.bsk.domain.Ware;
import com.bsk.dto.WareDTO;
import org.springframework.stereotype.Component;

@Component
public class WareMapper {
    public Ware map(WareDTO wareDTO) {
        return new Ware(wareDTO.getSwwCode(),
                wareDTO.getIdNumber(),
                wareDTO.getUnit(),
                wareDTO.getCategory(),
                wareDTO.getManufacturer(),
                wareDTO.getName(),
                wareDTO.getDescription(),
                wareDTO.getTaxRate());
    }
}
