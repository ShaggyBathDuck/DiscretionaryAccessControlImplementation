package com.bsk.services;

import com.bsk.domain.GrantPrivilege;
import com.bsk.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminViewService {

    private GrantPrivilegeService grantPrivilegeService;

    private UserService userService;

    @Autowired
    public AdminViewService(GrantPrivilegeService grantPrivilegeService, UserService userService) {
        this.grantPrivilegeService = grantPrivilegeService;
        this.userService = userService;
    }

    public List<GrantPrivilege> read() {
        List<User> userList = userService.read();
        List<GrantPrivilege> usersPrivilegesList = new ArrayList<>(userList.size());
        userList.stream().forEach(user ->
                usersPrivilegesList.add(grantPrivilegeService.getUserPrivilege(user.getLogin())));
        return usersPrivilegesList.stream().filter(grantPrivilege -> !grantPrivilege.isAdmin()).collect(Collectors.toList());
    }

    public void update(GrantPrivilege newPrivilege) {
        grantPrivilegeService.update(newPrivilege, this.grantPrivilegeService.getUserPrivilege(newPrivilege.getReceiver().getLogin()));
    }

    public void delete(String user) {
        grantPrivilegeService.delete(this.grantPrivilegeService.getUserPrivilege(user));
    }

    public boolean save(GrantPrivilege grantPrivilege) {
        if (grantPrivilegeService.findAllUserAncestors(grantPrivilege.getReceiver().getLogin()).contains(grantPrivilege.getGiver().getLogin()))
            return false;
        grantPrivilegeService.save(grantPrivilege);
        return true;
    }

}
