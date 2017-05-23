package com.bsk.controllers;

import com.bsk.dto.GrantPrivilegeDTO;
import com.bsk.services.GrantPrivilegeService;
import com.bsk.services.TableNamesService;
import com.bsk.services.UserService;
import com.bsk.util.EntityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
        model.addAttribute("users", grantPrivilegeService.findAllUsernamesForSelectedMode(grantMode.equals("give"), authentication.getName()));
        model.addAttribute("sentPrivilege", new GrantPrivilegeDTO());
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
