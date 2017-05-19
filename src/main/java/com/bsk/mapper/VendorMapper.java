package com.bsk.mapper;

import com.bsk.domain.Vendor;
import com.bsk.dto.VendorDTO;
import org.springframework.stereotype.Component;

@Component
public class VendorMapper {

    public Vendor map(VendorDTO vendorDTO) {
        return new Vendor(vendorDTO.getNip(),
                vendorDTO.getName(),
                vendorDTO.getPhoneNumber(),
                vendorDTO.getStreet(),
                vendorDTO.getHouseNumber(),
                vendorDTO.getFlatNumber(),
                vendorDTO.getPostalCode(),
                vendorDTO.getCity());
    }
}

