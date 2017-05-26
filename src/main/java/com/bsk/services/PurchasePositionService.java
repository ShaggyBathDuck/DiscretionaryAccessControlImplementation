package com.bsk.services;

import com.bsk.domain.PurchasePosition;
import com.bsk.repositories.PurchasePositionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PurchasePositionService {

    private PurchasePositionRepository purchasePositionRepository;

    public PurchasePositionService(PurchasePositionRepository purchasePositionRepository) {
        this.purchasePositionRepository = purchasePositionRepository;
    }

    public void save(PurchasePosition purchasePosition) {
        purchasePositionRepository.save(purchasePosition);
    }

    public List<PurchasePosition> read() {
        return purchasePositionRepository.findAll();
    }

    public void delete(Integer id) {
        purchasePositionRepository.delete(id);
    }

    public PurchasePosition findById(Integer id) {
        return purchasePositionRepository.findOne(id);
    }

    public List<PurchasePosition> findByAllAttributes(BigDecimal number) {
        return purchasePositionRepository.findByPriceOrAmount(number, number.intValue());
    }
}
