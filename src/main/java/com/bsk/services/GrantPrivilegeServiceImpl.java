package com.bsk.services;

import com.bsk.domain.GrantPrivilege;
import com.bsk.domain.GrantPrivilegePK;
import com.bsk.domain.Privilege;
import com.bsk.domain.User;
import com.bsk.dto.GrantPrivilegeDTO;
import com.bsk.repositories.GrantPrivilegesRepository;
import com.bsk.util.GrantPrivilegesUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GrantPrivilegeServiceImpl implements GrantPrivilegeService {
    private GrantPrivilegesRepository repository;

    private PrivilegeService privilegeService;

    private UserService userService;

    @Autowired
    public GrantPrivilegeServiceImpl(GrantPrivilegesRepository repository, PrivilegeService privilegeService, UserService userService) {
        this.repository = repository;
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

    public void give(GrantPrivilegeDTO grantPrivilegeDTO, String giversUsername){
        this.removeByReceiver(userService.findByLogin(giversUsername));
        this.removeByGiver(repository.findAllByGrantPrivilegePK_Giver(userService.findByLogin(giversUsername)), grantPrivilegeDTO.getReceiverName());
        this.removeByReceiver(userService.findByLogin(grantPrivilegeDTO.getReceiverName()));
        if(this.getUserPrivilege(giversUsername).isAdmin())
            giversUsername=grantPrivilegeDTO.getReceiverName();
        this.save(grantPrivilegeDTO, giversUsername);

    }

    private void removeByGiver(List<GrantPrivilege> list, String receiver){
        for(GrantPrivilege privilege:list){
            if(!privilege.getGrantPrivilegePK().getReceiver().getLogin().equals(receiver)){
                removeByGiver(repository.findAllByGrantPrivilegePK_Giver(privilege.getGrantPrivilegePK().getReceiver()), receiver);
                repository.delete(privilege);
            }
        }
    }

    private void removeByReceiver(User user){
        this.repository.deleteAllByGrantPrivilegePK_Receiver(user);
    }

    public GrantPrivilege getUserPrivilege(String username){
        Privilege noPrivileges = new Privilege(1, "NONE","NONE", "NONE","NONE" );
        GrantPrivilege userPrivileges = new GrantPrivilege(new GrantPrivilegePK(new User(), this.userService.findByLogin(username)), noPrivileges, noPrivileges, noPrivileges, noPrivileges, noPrivileges, noPrivileges, noPrivileges, noPrivileges, false);
        List<GrantPrivilege> userPrivilegeList = this.read().stream()
                .filter(grantPrivilege -> grantPrivilege.getGrantPrivilegePK().getReceiver().getLogin().equals(username))
                .collect(Collectors.toList());
        for(GrantPrivilege grantPrivilege : userPrivilegeList){
            userPrivileges = GrantPrivilegesUtilities.connect(userPrivileges, grantPrivilege);
        }
        return userPrivileges;
    }

    public List<GrantPrivilege> findAllWithTakePrivilege(){
        return repository.findAllByTake(true);
    }

    public List<String> findAllUsernamesForSelectedMode(boolean isModeTake, String loggedUser){
        Set<String> usernames;
        if(isModeTake){
            usernames =this.findAllWithTakePrivilege()
                    .stream()
                    .map(g -> g.getGrantPrivilegePK().getReceiver().getLogin())
                    .collect(Collectors.toSet());
        }else{
            Set<String> ancestorsUsernames = this.findAllUserAncestors(loggedUser);
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

    public Set<String> findAllUserAncestors(String loggedUser){
        Set<String> ancestorsNames= new HashSet<>();
        ancestorsNames.add(loggedUser);
        List<GrantPrivilege> receivedPrivileges=repository.findAllByGrantPrivilegePK_Receiver(userService.findByLogin(loggedUser));
        String giverLogin;
        for (int i=0; i<receivedPrivileges.size();i++){
            giverLogin=receivedPrivileges.get(i).getGrantPrivilegePK().getGiver().getLogin();
            if(!loggedUser.equals(giverLogin))
                ancestorsNames.addAll(findAllUserAncestors(giverLogin));
        }
        return ancestorsNames;
    }


    public void delete(GrantPrivilege deletedPrivilege){
        this.depthUpdate(deletedPrivilege.getReceiver(), deletedPrivilege);
        this.repository.deleteAllByGrantPrivilegePK_Receiver(deletedPrivilege.getReceiver());
    }

    public void update(GrantPrivilege newPrivilege, GrantPrivilege oldPrivilege){
        GrantPrivilege diffPrivilege  = GrantPrivilegesUtilities.difference(newPrivilege, oldPrivilege);
        //Check if any privilege has been received, if yes need to update all children
        if(GrantPrivilegesUtilities.haveCommonPart(oldPrivilege, diffPrivilege))
            this.depthUpdate(newPrivilege.getReceiver(), newPrivilege);
        this.repository.deleteAllByGrantPrivilegePK_Receiver(oldPrivilege.getReceiver());
        this.save(newPrivilege);

    }

    private void depthUpdate(User privilegeOwner, GrantPrivilege updatedPrivilege){
        List<GrantPrivilege> givenPrivileges = this.repository.findAllByGrantPrivilegePK_Giver(privilegeOwner);
        Iterator<GrantPrivilege> iterator = givenPrivileges.iterator();
        while(iterator.hasNext()){
            GrantPrivilege privilege = iterator.next();
            depthUpdate(privilege.getReceiver(), updatedPrivilege);
            privilege = GrantPrivilegesUtilities.difference(privilege, updatedPrivilege);
            if(GrantPrivilegesUtilities.isEmpty(privilege)){
                this.repository.delete(privilege);
            }
        }
    }
}
