package com.bsk.controllers;

import com.bsk.domain.User;
import com.bsk.repositories.TableNamesRepository;
import com.bsk.services.UserService;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Controller
public class OfferingController {
    private UserService userService;

    private TableNamesRepository tableNamesRepository;

    @Autowired
    public OfferingController(UserService userService, TableNamesRepository tableNamesRepository) {
        this.userService = userService;
        this.tableNamesRepository = tableNamesRepository;
    }

    @GetMapping("/offering")
    public String offeringPage(Model model,  @RequestParam(defaultValue = "grant") String grantMode){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());
        model.addAttribute("grantMode", grantMode);
        return "offering";
    }


    @ModelAttribute("users")
    @ResponseBody
    public List<User> getUsers() {
        return userService.read();
    }

    @ModelAttribute("tableNames")
    @ResponseBody
    public List<String> getTables(){
        List<String> tables = new LinkedList<>(tableNamesRepository.getTableNames().keySet());
        tables.add(0, "Uprawnienia");
        return tables;
    }

}
