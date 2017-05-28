package com.bsk.services;

import com.bsk.domain.GrantPrivilege;
import com.bsk.dto.GrantPrivilegeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface GrantPrivilegeService {
    List<GrantPrivilege> read();
    void save(GrantPrivilege grantPrivilege);
    void save(GrantPrivilegeDTO grantPrivilegeDTO, String giversUsername);
    void give(GrantPrivilegeDTO grantPrivilegeDTO, String giversUsername);
    GrantPrivilege getUserPrivilege(String username);
    List<GrantPrivilege> findAllWithTakePrivilege();
    List<String> findAllUsernamesForSelectedMode(boolean isModeTake, String loggedUser);
    Set<String> findAllUserAncestors(String loggedUser);
    void delete(GrantPrivilege deletedPrivilege);
    void update(GrantPrivilege newPrivilege, GrantPrivilege oldPrivilege);

}
