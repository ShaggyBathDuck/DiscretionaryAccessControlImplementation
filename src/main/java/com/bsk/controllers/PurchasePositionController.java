package com.bsk.controllers;

import com.bsk.domain.PurchasePosition;
import com.bsk.services.PurchasePositionService;
import com.bsk.services.TableNamesService;
import com.bsk.util.EntityInfo;
import javafx.util.Pair;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.SortedMap;

@Controller
@RequestMapping("/purchasepositions")
public class PurchasePositionController {

    private PurchasePositionService purchasePositionService;
    private TableNamesService tableNamesService;

    public PurchasePositionController(PurchasePositionService purchasePositionService, TableNamesService tableNamesService) {
        this.purchasePositionService = purchasePositionService;
        this.tableNamesService = tableNamesService;
    }

    private String showHome(Model model) {
        model.addAttribute("purchasepositions", purchasePositionService.read());
        return "redirect:/?tabName=pozycjezakupow";
    }

    @PostMapping(value = "/create")
    public String create(@Valid PurchasePosition purchasePosition, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        if (!bindingResult.hasErrors()) {
            purchasePositionService.save(purchasePosition);
            return showHome(model);
        }
        attr.addFlashAttribute("errors", bindingResult.getFieldErrors());
        return "redirect:/?tabName=pozycjezakupow";
    }

    @PutMapping(value = "/update/{id}")
    public String update(@PathVariable int id, @Valid PurchasePosition purchasePosition, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        if (!bindingResult.hasErrors()) {
            purchasePositionService.save(purchasePosition);
            return showHome(model);
        }
        attr.addFlashAttribute("errors", bindingResult.getFieldErrors());
        return "redirect:/?tabName=pozycjezakupow";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id, Model model, RedirectAttributes attr) {
        try {
            purchasePositionService.delete(id);
        } catch (DataIntegrityViolationException exception) {
            attr.addFlashAttribute("foreignKeyError", "Nie można usunąć wiersza - jest kluczem obcym w innej tabeli");
        }
        return showHome(model);
    }

    @RequestMapping(value = "/search")
    public String search(Model model, String content, RedirectAttributes attr) {
        List<PurchasePosition> purchasePositions;
        if (content.isEmpty() || !content.chars().allMatch(Character::isDigit))
            purchasePositions = purchasePositionService.read();
        else purchasePositions = purchasePositionService.findByAllAttributes(new BigDecimal(content));
        model.addAttribute("purchasepositions", purchasePositions);
        model.addAttribute("purchaseposition", new PurchasePosition());
        SortedMap<String, EntityInfo> entitiesInfo = tableNamesService.getDisplayableTableNames();
        Pair<String, SortedMap<String, EntityInfo>> data = new Pair<>("pozycjezakupow", entitiesInfo);
        model.addAttribute("data", data);
        return "fragments/table :: tableDiv";
    }
}
