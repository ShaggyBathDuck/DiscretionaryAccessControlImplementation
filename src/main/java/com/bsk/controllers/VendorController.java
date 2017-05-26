package com.bsk.controllers;


import com.bsk.domain.Vendor;
import com.bsk.services.TableNamesService;
import com.bsk.services.VendorService;
import com.bsk.util.EntityInfo;
import javafx.util.Pair;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.SortedMap;

@Controller()
@RequestMapping("/vendors")
public class VendorController {

    private VendorService vendorService;
    private TableNamesService tableNamesService;

    public VendorController(VendorService vendorService, TableNamesService tableNamesService) {
        this.vendorService = vendorService;
        this.tableNamesService = tableNamesService;
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
    public String delete(@PathVariable int id, Model model, RedirectAttributes attr) {
        try {
            vendorService.delete(id);
        } catch (DataIntegrityViolationException exception) {
            attr.addFlashAttribute("foreignKeyError", "Nie można usunąć wiersza - jest kluczem obcym w innej tabeli");
        }
        return showHome(model);
    }

    @RequestMapping(value = "/search")
    public String search(Model model, String content, RedirectAttributes attr) {
        List<Vendor> vendors = vendorService.findByAllAttributes(content);
        model.addAttribute("vendors", vendors);
        model.addAttribute("vendor", new Vendor());
        SortedMap<String, EntityInfo> entitiesInfo = tableNamesService.getDisplayableTableNames();
        Pair<String, SortedMap<String, EntityInfo>> data = new Pair<>("dostawcy", entitiesInfo);
        model.addAttribute("data", data);
        return "fragments/table :: tableDiv";
    }
}
