package com.bsk.controllers;

import com.bsk.domain.WarehouseItem;
import com.bsk.services.WarehouseItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/warehouseitems")
public class WarehouseItemController {
    private WarehouseItemService warehouseItemService;

    public WarehouseItemController(WarehouseItemService warehouseItemService) {
        this.warehouseItemService = warehouseItemService;
    }

    private String showHome(Model model) {
        model.addAttribute("warehouseItems", warehouseItemService.read());
        return "redirect:/?tabName=towarymagazyn";
    }

    @PostMapping(value = "/create")
    public String create(@Valid WarehouseItem warehouseItem, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        if (!bindingResult.hasErrors()) {
            warehouseItemService.save(warehouseItem);
            return showHome(model);
        }
        attr.addFlashAttribute("errors", bindingResult.getFieldErrors());
        return "redirect:/?tabName=towarymagazyn";
    }

    @PutMapping(value = "/update/{id}")
    public String update(@PathVariable int id, @Valid WarehouseItem warehouseItem, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        if (!bindingResult.hasErrors()) {
            warehouseItemService.save(warehouseItem);
            return showHome(model);
        }
        attr.addFlashAttribute("errors", bindingResult.getFieldErrors());
        return "redirect:/?tabName=towarymagazyn";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        warehouseItemService.delete(id);
        return showHome(model);
    }
}
