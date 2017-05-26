package com.bsk.services;

import com.bsk.domain.SalePosition;
import com.bsk.repositories.SalePositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalePositionService {

    private SalePositionRepository salePositionRepository;

    public SalePositionService(SalePositionRepository salePositionRepository) {
        this.salePositionRepository = salePositionRepository;
    }

    public void save(SalePosition salePosition) {
        salePositionRepository.save(salePosition);
    }

    public List<SalePosition> read() {
        return salePositionRepository.findAll();
    }

    public void delete(Integer id) {
        salePositionRepository.delete(id);
    }

    public SalePosition findById(Integer id) {
        return salePositionRepository.findOne(id);
    }

    public List<SalePosition> findByAllAttributes(Integer number) {
        return salePositionRepository.findByAmountOrDiscount(number, number);
    }
}
