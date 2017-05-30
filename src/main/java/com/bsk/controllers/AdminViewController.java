package com.bsk.controllers;


import com.bsk.domain.GrantPrivilege;
import com.bsk.dto.GrantPrivilegeDTO;
import com.bsk.services.AdminViewService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public AdminViewController(AdminViewService adminViewService) {
        this.adminViewService = adminViewService;
    }

    @GetMapping
    public String adminViewPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());
        model.addAttribute("privileges", adminViewService.read());
        return "/admin-view";
    }


    @PostMapping(value = "/create")
    public String create(@Valid GrantPrivilege grantPrivilege, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        if (!bindingResult.hasErrors()) {
            adminViewService.save(grantPrivilege);
            return adminViewPage(model);
        }
        attr.addFlashAttribute("errors", bindingResult.getFieldErrors());
        return "redirect:/admin-view";
    }

    @PutMapping(value = "/update/{receiver}")
    public String update(@Valid GrantPrivilege updatedPrivilege, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        if (!bindingResult.hasErrors()) {
            adminViewService.update(updatedPrivilege);
            return adminViewPage(model);
        }
        attr.addFlashAttribute("errors", bindingResult.getFieldErrors());
        return "redirect:/admin-view";
    }

    @DeleteMapping(value = "/delete/{receiver}")
    public String delete(@PathVariable String receiver, Model model, RedirectAttributes attr) {
        adminViewService.delete(receiver);
        return adminViewPage(model);
    }

}
