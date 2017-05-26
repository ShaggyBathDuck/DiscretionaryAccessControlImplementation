package com.bsk.repositories;

import com.bsk.domain.PurchasePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PurchasePositionRepository extends JpaRepository<PurchasePosition, Integer> {
    List<PurchasePosition> findByPriceOrAmount(BigDecimal price, Integer amount);
}
