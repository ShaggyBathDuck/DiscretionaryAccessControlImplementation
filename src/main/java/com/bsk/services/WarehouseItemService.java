package com.bsk.services;


import com.bsk.domain.WarehouseItem;
import com.bsk.repositories.WarehouseItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseItemService {

    private WarehouseItemRepository warehouseItemRepository;

    public WarehouseItemService(WarehouseItemRepository warehouseItemRepository) {
        this.warehouseItemRepository = warehouseItemRepository;
    }

    public void save(WarehouseItem warehouseItem) {
        warehouseItemRepository.save(warehouseItem);
    }

    public List<WarehouseItem> read() {
        return warehouseItemRepository.findAll();
    }

    public void delete(Integer id) {
        warehouseItemRepository.delete(id);
    }

    public WarehouseItem findById(Integer id) {
        return warehouseItemRepository.findOne(id);
    }
}
