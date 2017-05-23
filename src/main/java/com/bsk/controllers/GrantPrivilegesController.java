package com.bsk.controllers;


import com.bsk.dto.GrantPrivilegeDTO;
import com.bsk.services.GrantPrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/privilege")
public class GrantPrivilegesController {
    private GrantPrivilegeService grantPrivilegeService;

    @Autowired
    public GrantPrivilegesController(GrantPrivilegeService grantPrivilegeService) {
        this.grantPrivilegeService = grantPrivilegeService;
    }

    @PostMapping(value = "/grant")
    public String create(@Valid GrantPrivilegeDTO grantPrivilegeDTO, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        if (grantPrivilegeDTO.getCustomer().getId() == null &&
                grantPrivilegeDTO.getPurchase().getId() == null &&
                grantPrivilegeDTO.getPurchasePosition().getId() == null &&
                grantPrivilegeDTO.getWare().getId() == null &&
                grantPrivilegeDTO.getWarehouseProduct().getId() == null &&
                grantPrivilegeDTO.getSale().getId() == null &&
                grantPrivilegeDTO.getSalePosition().getId() == null &&
                grantPrivilegeDTO.getWare().getId() == null &&
                grantPrivilegeDTO.getVendor().getId() == null) {
            attr.addFlashAttribute("noGrants", true);
            return "redirect:/offering";
        }
        if (!bindingResult.hasErrors()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            grantPrivilegeService.save(grantPrivilegeDTO, authentication.getName());
            attr.addFlashAttribute("successfullyGranted", true);
            return "redirect:/offering";
        }
        attr.addFlashAttribute("failedGranted", true);
        return "redirect:/offering";
    }


}
