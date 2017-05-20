package com.bsk.repositories;


import com.bsk.domain.SalePosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalePositionRepository extends JpaRepository<SalePosition, Integer> {
}
