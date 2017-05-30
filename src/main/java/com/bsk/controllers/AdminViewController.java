package com.bsk.controllers;


import com.bsk.domain.GrantPrivilege;
import com.bsk.services.AdminViewService;
import com.bsk.services.TableNamesService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller()
@RequestMapping("/admin-view")
public class AdminViewController {
    private AdminViewService adminViewService;
    private TableNamesService tableNamesService;

    public AdminViewController(AdminViewService adminViewService, TableNamesService tableNamesService) {
        this.adminViewService = adminViewService;
        this.tableNamesService = tableNamesService;
    }

    @GetMapping
    public String adminViewPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());
        model.addAttribute("privileges", adminViewService.read());
        model.addAttribute("userPrivileges", adminViewService.getUserPrivilege(authentication.getName()));
        model.addAttribute("entitiesInfo", tableNamesService.getDisplayableTableNames());
        model.addAttribute("templateprivilege", new GrantPrivilege());


        return "/admin-view";
    }


}
