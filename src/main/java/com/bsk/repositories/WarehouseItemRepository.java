package com.bsk.repositories;


import com.bsk.domain.WarehouseItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseItemRepository extends JpaRepository<WarehouseItem, Integer> {
}
