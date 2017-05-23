package com.bsk.services;

import com.bsk.connectors.PrivilegesConnector;
import com.bsk.domain.GrantPrivilege;
import com.bsk.domain.GrantPrivilegePK;
import com.bsk.domain.Privilege;
import com.bsk.dto.GrantPrivilegeDTO;
import com.bsk.repositories.GrantPrivilegesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GrantPrivilegeService {
    private GrantPrivilegesRepository repository;

    private PrivilegesConnector privilegesConnector;

    private PrivilegeService privilegeService;

    private UserService userService;

    @Autowired
    public GrantPrivilegeService(GrantPrivilegesRepository repository, PrivilegesConnector privilegesConnector, PrivilegeService privilegeService, UserService userService) {
        this.repository = repository;
        this.privilegesConnector = privilegesConnector;
        this.privilegeService = privilegeService;
        this.userService = userService;
    }



    public List<GrantPrivilege> read() {
        return repository.findAll();
    }

    public void save(GrantPrivilege grantPrivilege) {
        repository.save(grantPrivilege);
    }

    public void save(GrantPrivilegeDTO grantPrivilegeDTO, String username) { //TODO Sprawdzanie czy nie występują cykle
        GrantPrivilege grantPrivilege = new GrantPrivilege(
                new GrantPrivilegePK(userService.findByLogin(grantPrivilegeDTO.getReceiverName()), userService.findByLogin(username)),
                privilegeService.findFirstByCRUD(grantPrivilegeDTO.getCustomer()),
                privilegeService.findFirstByCRUD(grantPrivilegeDTO.getPurchase()),
                privilegeService.findFirstByCRUD(grantPrivilegeDTO.getPurchasePosition()),
                privilegeService.findFirstByCRUD(grantPrivilegeDTO.getWare()),
                privilegeService.findFirstByCRUD(grantPrivilegeDTO.getWarehouseProduct()),
                privilegeService.findFirstByCRUD(grantPrivilegeDTO.getSale()),
                privilegeService.findFirstByCRUD(grantPrivilegeDTO.getSalePosition()),
                privilegeService.findFirstByCRUD(grantPrivilegeDTO.getVendor()),
                grantPrivilegeDTO.isTake());
        repository.save(grantPrivilege);
    }

    public GrantPrivilege getUserPrivilege(String username){
        Privilege noPrivileges = new Privilege(1, "NONE","NONE", "NONE","NONE" );
        GrantPrivilege userPrivileges = new GrantPrivilege(null, noPrivileges, noPrivileges, noPrivileges, noPrivileges, noPrivileges, noPrivileges, noPrivileges, noPrivileges, false);
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

    public List<String> findAllUsernamesForSelectedMode(boolean isModeTake, String loggedUser){
        Set<String> usernames;
        if(isModeTake){
            usernames =this.findAllByTake(isModeTake)
                    .stream()
                    .map(g -> g.getGrantPrivilegePK().getReceiver().getLogin())
                    .collect(Collectors.toSet());
        }else{
            usernames= userService.read().stream()
                    .map(g -> g.getLogin())
                    .collect(Collectors.toSet());
        }
        return usernames
                .stream()
                .filter(l -> !l.equals(loggedUser))
                .collect(Collectors.toList());
    }
}
