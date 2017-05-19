package com.bsk.services;


import com.bsk.domain.Vendor;
import com.bsk.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {
    private VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public List<Vendor> read() {
        return vendorRepository.findAll();
    }

    public void save(Vendor vendor) {
        vendorRepository.save(vendor);
    }

    public void delete(Integer id) {
        vendorRepository.delete(id);
    }

    public Vendor findById(Integer id) {
        return vendorRepository.findOne(id);
    }
}
