package com.bsk.services;

import com.bsk.connectors.PrivilegesConnector;
import com.bsk.domain.GrantPrivilege;
import com.bsk.domain.GrantPrivilegePK;
import com.bsk.domain.Privilege;
import com.bsk.domain.User;
import com.bsk.dto.GrantPrivilegeDTO;
import com.bsk.repositories.GrantPrivilegesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
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

    public void save(GrantPrivilegeDTO grantPrivilegeDTO, String username) {
        GrantPrivilege grantPrivilege = new GrantPrivilege(
                new GrantPrivilegePK(userService.findByLogin(username), userService.findByLogin(grantPrivilegeDTO.getReceiverName())),
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
    public void give(GrantPrivilegeDTO grantPrivilegeDTO, String username){
        this.removeByReceiver(userService.findByLogin(grantPrivilegeDTO.getReceiverName()));
        this.removeByReceiver(userService.findByLogin(username));
        this.removeByGiver(repository.findAllByGrantPrivilegePK_Giver(userService.findByLogin(username)));
        this.save(grantPrivilegeDTO, username);

    }

    private void removeByGiver(List<GrantPrivilege> list){
        for(GrantPrivilege privilege:list){
            removeByGiver(repository.findAllByGrantPrivilegePK_Giver(privilege.getGrantPrivilegePK().getReceiver()));
            repository.delete(privilege);
        }
    }
    private void removeByReceiver(User user){
        this.repository.deleteAllByGrantPrivilegePK_Receiver(user);
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
            Set<String> ancestorsUsernames = this.findAllAncestors(loggedUser);
            usernames= userService.read().stream()
                    .map(g -> g.getLogin())
                    .filter(username -> !ancestorsUsernames.contains(username))
                    .collect(Collectors.toSet());
        }
        return usernames
                .stream()
                .filter(l -> !l.equals(loggedUser))
                .collect(Collectors.toList());
    }

    private Set<String> findAllAncestors(String loggedUser){
        Set<String> ancestorsNames= new HashSet<>();
        ancestorsNames.add(loggedUser);
        List<GrantPrivilege> receivedPrivileges=repository.findAllByGrantPrivilegePK_Receiver(userService.findByLogin(loggedUser));
        String giverLogin;
        for (int i=0; i<receivedPrivileges.size();i++){
            giverLogin=receivedPrivileges.get(i).getGrantPrivilegePK().getGiver().getLogin();
            if(!loggedUser.equals(giverLogin))
                ancestorsNames.addAll(findAllAncestors(giverLogin));
        }
        return ancestorsNames;
    }
}
