package com.bsk.repositories;


import com.bsk.domain.GrantPrivilege;
import com.bsk.domain.GrantPrivilegePK;
import com.bsk.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GrantPrivilegesRepository extends JpaRepository<GrantPrivilege, GrantPrivilegePK> {
    List<GrantPrivilege> findAllByTake(boolean take);
    @Transactional
    void deleteAllByGrantPrivilegePK_Receiver(User user);
    @Transactional
    void deleteAllByGrantPrivilegePK_Giver(User user);
    @Transactional
    List<GrantPrivilege> findAllByGrantPrivilegePK_Giver(User user);

}
