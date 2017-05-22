package com.bsk.services;

import com.bsk.connectors.PrivilegesConnector;
import com.bsk.domain.GrantPrivilege;
import com.bsk.domain.Privilege;
import com.bsk.domain.User;
import com.bsk.repositories.GrantPrivilegesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GrantPrivilegeService {
    private GrantPrivilegesRepository repository;

    private PrivilegesConnector privilegesConnector;

    @Autowired
    public GrantPrivilegeService(GrantPrivilegesRepository repository, PrivilegesConnector privilegesConnector) {
        this.repository = repository;
        this.privilegesConnector = privilegesConnector;
    }



    public List<GrantPrivilege> read() {
        return repository.findAll();
    }

    public void save(GrantPrivilege grantPrivilege) {
        repository.save(grantPrivilege);
    }

    public GrantPrivilege getUserPrivilege(String username){
        Privilege noPrivileges = new Privilege(1, "NONE","NONE", "NONE","NONE" );
        GrantPrivilege userPrivileges= new GrantPrivilege(null,noPrivileges,noPrivileges,noPrivileges,noPrivileges,noPrivileges,noPrivileges,noPrivileges,false);
        List<GrantPrivilege> userPrivilegeList = this.read().stream()
                .filter(grantPrivilege -> grantPrivilege.getGrantPrivilegePK().getReceiver().getLogin().equals(username))
                .collect(Collectors.toList());
        for(GrantPrivilege grantPrivilege : userPrivilegeList){
            userPrivileges = privilegesConnector.connect(userPrivileges, grantPrivilege);
        }
        return userPrivileges;
    }

    public List<GrantPrivilege> findAllByTake(boolean take){
        return repository.findAllByTake(take);
    }

}
