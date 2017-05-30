package com.bsk.controllers;


import com.bsk.dto.GrantPrivilegeDTO;
import com.bsk.services.GrantPrivilegeService;
import com.bsk.util.GrantPrivilegesUtilities;
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
    public String grant(@Valid GrantPrivilegeDTO grantPrivilegeDTO, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (
                !(grantPrivilegeDTO.getCustomer().hasEffectiveRights() ||
                        grantPrivilegeDTO.getPurchase().hasEffectiveRights() ||
                        grantPrivilegeDTO.getPurchasePosition().hasEffectiveRights() ||
                        grantPrivilegeDTO.getWare().hasEffectiveRights() ||
                        grantPrivilegeDTO.getWarehouseProduct().hasEffectiveRights() ||
                        grantPrivilegeDTO.getSale().hasEffectiveRights() ||
                        grantPrivilegeDTO.getSalePosition().hasEffectiveRights() ||
                        grantPrivilegeDTO.getVendor().hasEffectiveRights() ||
                        grantPrivilegeDTO.isTake())) {
            attr.addFlashAttribute("noGrants", true);
            return "redirect:/offering";
        }
        if (!GrantPrivilegesUtilities.haveCommonPart(grantPrivilegeDTO, grantPrivilegeService.getUserPrivilege(grantPrivilegeDTO.getReceiverName()))) {
            if (!bindingResult.hasErrors()) {
                grantPrivilegeService.save(grantPrivilegeDTO, authentication.getName());
                attr.addFlashAttribute("successfullyGranted", true);
                return "redirect:/offering";
            }
        }
        attr.addFlashAttribute("failedGranted", true);
        return "redirect:/offering";
    }

    @PostMapping(value = "/give")
    public String give(@Valid GrantPrivilegeDTO grantPrivilegeDTO, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        if (
                !(grantPrivilegeDTO.getCustomer().hasEffectiveRights() ||
                        grantPrivilegeDTO.getPurchase().hasEffectiveRights() ||
                        grantPrivilegeDTO.getPurchasePosition().hasEffectiveRights() ||
                        grantPrivilegeDTO.getWare().hasEffectiveRights() ||
                        grantPrivilegeDTO.getWarehouseProduct().hasEffectiveRights() ||
                        grantPrivilegeDTO.getSale().hasEffectiveRights() ||
                        grantPrivilegeDTO.getSalePosition().hasEffectiveRights() ||
                        grantPrivilegeDTO.getVendor().hasEffectiveRights() ||
                        grantPrivilegeService.getUserPrivilege(grantPrivilegeDTO.getReceiverName()).getTake()
                        )) {
            attr.addFlashAttribute("noGrants", true);
            return "redirect:/offering";
        }
            if (!bindingResult.hasErrors()) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                grantPrivilegeService.give(grantPrivilegeDTO, authentication.getName());
                attr.addFlashAttribute("successfullyGift", true);
                return "redirect:/offering";
            }


        attr.addFlashAttribute("failedGranted", true);
        return "redirect:/offering";
    }


}
