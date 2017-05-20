package com.bsk.controllers;


import com.bsk.domain.Vendor;
import com.bsk.services.VendorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller()
@RequestMapping("/vendors")
public class VendorController {

    private VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    private String showHome(Model model) {
        model.addAttribute("vendors", vendorService.read());
        return "redirect:/?tabName=dostawcy";
    }

    @PostMapping(value = "/create")
    public String create(@Valid Vendor vendor, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        if (!bindingResult.hasErrors()) {
            vendorService.save(vendor);
            return showHome(model);
        }
        attr.addFlashAttribute("errors", bindingResult.getFieldErrors());
        return "redirect:/?tabName=dostawcy";
    }

    @PutMapping(value = "/update/{id}")
    public String update(@PathVariable int id, @Valid Vendor vendor, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        if (!bindingResult.hasErrors()) {
            vendorService.save(vendor);
            return showHome(model);
        }
        attr.addFlashAttribute("errors", bindingResult.getFieldErrors());
        return "redirect:/?tabName=dostawcy";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        vendorService.delete(id);
        return showHome(model);
    }
}
