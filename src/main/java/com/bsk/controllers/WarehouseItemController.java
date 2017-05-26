package com.bsk.controllers;

import com.bsk.domain.WarehouseItem;
import com.bsk.services.TableNamesService;
import com.bsk.services.WarehouseItemService;
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

@Controller
@RequestMapping("/warehouseitems")
public class WarehouseItemController {
    private WarehouseItemService warehouseItemService;
    private TableNamesService tableNamesService;

    public WarehouseItemController(WarehouseItemService warehouseItemService, TableNamesService tableNamesService) {
        this.warehouseItemService = warehouseItemService;
        this.tableNamesService = tableNamesService;
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
    public String delete(@PathVariable int id, Model model, RedirectAttributes attr) {
        try {
            warehouseItemService.delete(id);
        } catch (DataIntegrityViolationException exception) {
            attr.addFlashAttribute("foreignKeyError", "Nie można usunąć wiersza - jest kluczem obcym w innej tabeli");
        }
        return showHome(model);
    }

    @RequestMapping(value = "/search")
    public String search(Model model, String content, RedirectAttributes attr) {
        List<WarehouseItem> warehouseItems = warehouseItemService.findByAllAttributes(content);
        model.addAttribute("warehouseitems", warehouseItems);
        model.addAttribute("warehouseitem", new WarehouseItem());
        SortedMap<String, EntityInfo> entitiesInfo = tableNamesService.getDisplayableTableNames();
        Pair<String, SortedMap<String, EntityInfo>> data = new Pair<>("towarymagazyn", entitiesInfo);
        model.addAttribute("data", data);
        return "fragments/table :: tableDiv";
    }
}
