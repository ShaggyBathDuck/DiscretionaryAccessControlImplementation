package com.bsk.repositories;


import com.bsk.domain.Ware;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WareRepository extends JpaRepository<Ware, Integer> {
}
