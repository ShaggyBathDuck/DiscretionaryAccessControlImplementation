package com.bsk.repositories;


import com.bsk.domain.WarehouseItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarehouseItemRepository extends JpaRepository<WarehouseItem, Integer> {
    List<WarehouseItem> findByLocationContains(String location);
}
