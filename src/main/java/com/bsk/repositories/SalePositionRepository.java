package com.bsk.repositories;


import com.bsk.domain.SalePosition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalePositionRepository extends JpaRepository<SalePosition, Integer> {
    List<SalePosition> findByAmountOrDiscount(Integer amount, Integer discount);
}
