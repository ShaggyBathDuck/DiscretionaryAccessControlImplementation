package com.bsk.controllers;

import com.bsk.domain.Purchase;
import com.bsk.services.PurchaseService;
import com.bsk.services.TableNamesService;
import com.bsk.util.EntityInfo;
import javafx.util.Pair;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

@Controller
@RequestMapping("/purchases")
public class PurchaseController {

    private PurchaseService purchaseService;
    private TableNamesService tableNamesService;

    public PurchaseController(PurchaseService purchaseService, TableNamesService tableNamesService) {
        this.purchaseService = purchaseService;
        this.tableNamesService = tableNamesService;
    }

    private String showHome(Model model) {
        model.addAttribute("purchases", purchaseService.read());
        return "redirect:/?tabName=zakupy";
    }

    @PostMapping(value = "/create")
    public String create(@Valid Purchase purchase, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        if (!bindingResult.hasErrors()) {
            purchaseService.save(purchase);
            return showHome(model);
        }
        attr.addFlashAttribute("errors", bindingResult.getFieldErrors());
        return "redirect:/?tabName=zakupy";
    }

    @PutMapping(value = "/update/{id}")
    public String update(@PathVariable int id, @Valid Purchase purchase, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        if (!bindingResult.hasErrors()) {
            purchaseService.save(purchase);
            return showHome(model);
        }
        attr.addFlashAttribute("errors", bindingResult.getFieldErrors());
        return "redirect:/?tabName=zakupy";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id, Model model, RedirectAttributes attr) {
        try {
            purchaseService.delete(id);
        } catch (DataIntegrityViolationException exception) {
            attr.addFlashAttribute("foreignKeyError", "Nie można usunąć wiersza - jest kluczem obcym w innej tabeli");
        }
        return showHome(model);
    }

    @InitBinder
    public void dateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }

    @RequestMapping(value = "/search")
    public String search(Model model, String content, RedirectAttributes attr) {
        List<Purchase> purchases = purchaseService.findByAllAttributes(content);
        model.addAttribute("purchases", purchases);
        model.addAttribute("purchase", new Purchase());
        SortedMap<String, EntityInfo> entitiesInfo = tableNamesService.getDisplayableTableNames();
        Pair<String, SortedMap<String, EntityInfo>> data = new Pair<>("zakupy", entitiesInfo);
        model.addAttribute("data", data);
        return "fragments/table :: tableDiv";
    }
}
