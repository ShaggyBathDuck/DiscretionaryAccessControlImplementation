package com.bsk.repositories;


import com.bsk.domain.Ware;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WareRepository extends JpaRepository<Ware, Integer> {
    List<Ware> findByUnitContainsOrCategoryContainsOrManufacturerContainsOrNameContainsOrDescriptionContains
            (String unit, String category, String manufacturer, String name, String description);
}
