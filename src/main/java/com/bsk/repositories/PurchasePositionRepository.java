package com.bsk.repositories;

import com.bsk.domain.PurchasePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasePositionRepository extends JpaRepository<PurchasePosition, Integer> {
}
