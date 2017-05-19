package com.bsk.services;

import com.bsk.domain.Sale;
import com.bsk.repositories.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    private SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public void save(Sale sale) {
        saleRepository.save(sale);
    }

    public List<Sale> read() {
        return saleRepository.findAll();
    }

    public void delete(Integer id) {
        saleRepository.delete(id);
    }

    public Sale findById(Integer id) {
        return saleRepository.findOne(id);
    }
}
