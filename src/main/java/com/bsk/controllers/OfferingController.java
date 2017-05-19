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
import java.util.List;

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
    public List<String> getUsernames() {
        return userService.readUsernames();
    }

    @ModelAttribute("tableNames")
    @ResponseBody
    public List<String> getTables(){
        return new ArrayList<>(tableNamesRepository.getTableNames().keySet());
    }

}
