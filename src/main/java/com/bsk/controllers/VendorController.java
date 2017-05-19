package com.bsk.controllers;


import com.bsk.domain.Vendor;
import com.bsk.services.VendorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public String create(@Valid Vendor vendor, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            Vendor toSave = vendor;
            vendorService.save(toSave);
            return showHome(model);
        }
        return "redirect:/?tabName=dostawcy";
    }

    @PutMapping(value = "/update/{id}")
    public String update(@PathVariable int id, @Valid Vendor vendor, Model model) {
        vendorService.save(vendor);
        return showHome(model);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        vendorService.delete(id);
        return showHome(model);
    }
}
