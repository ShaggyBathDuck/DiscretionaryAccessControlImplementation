package com.bsk.services;


import com.bsk.domain.Purchase;
import com.bsk.repositories.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    private PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public void save(Purchase purchase) {
        purchaseRepository.save(purchase);
    }

    public List<Purchase> read() {
        return purchaseRepository.findAll();
    }

    public void delete(Integer id) {
        purchaseRepository.delete(id);
    }

    public Purchase findById(Integer id) {
        return purchaseRepository.findOne(id);
    }


}
