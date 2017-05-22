package com.bsk.controllers;

import com.bsk.connectors.PrivilegesConnector;
import com.bsk.domain.GrantPrivilege;
import com.bsk.domain.Privilege;
import com.bsk.domain.User;
import com.bsk.dto.GrantPrivilegeDTO;
import com.bsk.repositories.TableNamesRepository;
import com.bsk.services.GrantPrivilegeService;
import com.bsk.services.TableNamesService;
import com.bsk.services.UserService;
import com.bsk.util.EntityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

@Controller
public class OfferingController {
    private UserService userService;

    private GrantPrivilegeService grantPrivilegeService;

    private TableNamesService tableNamesService;

    @Autowired
    public OfferingController(UserService userService, GrantPrivilegeService grantPrivilegeService, TableNamesService tableNamesService) {
        this.userService = userService;
        this.grantPrivilegeService = grantPrivilegeService;
        this.tableNamesService = tableNamesService;
    }


    @GetMapping("/offering")
    public String offeringPage(Model model,  @RequestParam(defaultValue = "grant") String grantMode){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());
        model.addAttribute("grantMode", grantMode);
        model.addAttribute("userPrivileges", grantPrivilegeService.getUserPrivilege(authentication.getName()));
        model.addAttribute("users", userService.findAllUsersNamesByTake(grantMode.equals("give"), authentication.getName()));
        return "offering";
    }


    @ModelAttribute("tableNames")
    @ResponseBody
    public List<String> getTables(){
        return new ArrayList<>(tableNamesService.getDisplayableTableNames().keySet());
    }

    @ModelAttribute("privilegeInfo")
    @ResponseBody
    public EntityInfo getPrivilegeInfo(){
        return  tableNamesService.getTableNames().get("uprawnienia");
    }

    @ModelAttribute("grantedPrivilegeInfo")
    @ResponseBody
    public EntityInfo getGrantedPrivilegeInfo(){
        return tableNamesService.getTableNames().get("przekazywanieuprawnien");
    }





}
