package com.bsk.repositories;


import com.bsk.domain.GrantPrivilege;
import com.bsk.domain.GrantPrivilegePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrantPrivilegesRepository extends JpaRepository<GrantPrivilege, GrantPrivilegePK> {
    List<GrantPrivilege> findAllByTake(boolean take);
}
