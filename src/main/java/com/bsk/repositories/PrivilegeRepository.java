package com.bsk.repositories;


import com.bsk.domain.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {
    Privilege findPrivilegeByCreateAndReadAndUpdateAndDelete(String create, String read, String update, String delete);
}
