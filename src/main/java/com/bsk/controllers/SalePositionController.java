package com.bsk.controllers;

import com.bsk.domain.SalePosition;
import com.bsk.services.SalePositionService;
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
import java.util.List;
import java.util.SortedMap;

@Controller
@RequestMapping(value = "salepositions")
public class SalePositionController {

    private SalePositionService salePositionService;
    private TableNamesService tableNamesService;

    public SalePositionController(SalePositionService salePositionService, TableNamesService tableNamesService) {
        this.salePositionService = salePositionService;
        this.tableNamesService = tableNamesService;
    }

    private String showHome(Model model) {
        model.addAttribute("salepositions", salePositionService.read());
        return "redirect:/?tabName=pozycjesprzedazy";
    }

    @PostMapping(value = "/create")
    public String create(@Valid @ModelAttribute("saleposition") SalePosition salePosition, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        if (!bindingResult.hasErrors()) {
            salePositionService.save(salePosition);
            return showHome(model);
        }
        attr.addFlashAttribute("errors", bindingResult.getFieldErrors());
        return "redirect:/?tabName=pozycjesprzedazy";
    }

    @PutMapping(value = "/update/{id}")
    public String update(@PathVariable int id, @Valid SalePosition salePosition, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        if (!bindingResult.hasErrors()) {
            salePositionService.save(salePosition);
            return showHome(model);
        }
        attr.addFlashAttribute("errors", bindingResult.getFieldErrors());
        return "redirect:/?tabName=pozycjesprzedazy";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id, Model model, RedirectAttributes attr) {
        try {
            salePositionService.delete(id);
        } catch (DataIntegrityViolationException exception) {
            attr.addFlashAttribute("foreignKeyError", "Nie można usunąć wiersza - jest kluczem obcym w innej tabeli");
        }
        return showHome(model);
    }

    @RequestMapping(value = "/search")
    public String search(Model model, String content, RedirectAttributes attr) {
        List<SalePosition> salePositions;
        if (content.isEmpty() || !content.chars().allMatch(Character::isDigit))
            salePositions = salePositionService.read();
        else salePositions = salePositionService.findByAllAttributes(Integer.parseInt(content));
        model.addAttribute("salepositions", salePositions);
        model.addAttribute("saleposition", new SalePosition());
        SortedMap<String, EntityInfo> entitiesInfo = tableNamesService.getDisplayableTableNames();
        Pair<String, SortedMap<String, EntityInfo>> data = new Pair<>("pozycjesprzedazy", entitiesInfo);
        model.addAttribute("data", data);
        return "fragments/table :: tableDiv";
    }
}
