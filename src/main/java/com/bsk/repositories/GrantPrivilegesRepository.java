package com.bsk.repositories;


import com.bsk.domain.GrantPrivilege;
import com.bsk.domain.GrantPrivilegePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrantPrivilegesRepository extends JpaRepository<GrantPrivilege, GrantPrivilegePK> {

}
