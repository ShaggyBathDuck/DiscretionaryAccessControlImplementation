package com.bsk.controllers;


import com.bsk.domain.Ware;
import com.bsk.services.TableNamesService;
import com.bsk.services.WareService;
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
@RequestMapping("/wares")
public class WareController {

    private WareService wareService;
    private TableNamesService tableNamesService;

    public WareController(WareService wareService, TableNamesService tableNamesService) {
        this.wareService = wareService;
        this.tableNamesService = tableNamesService;
    }

    private String showHome(Model model) {
        model.addAttribute("wares", wareService.read());
        return "redirect:/?tabName=towary";
    }

    @PostMapping(value = "/create")
    public String create(@Valid Ware ware, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        if (!bindingResult.hasErrors()) {
            wareService.save(ware);
            return showHome(model);
        }
        attr.addFlashAttribute("errors", bindingResult.getFieldErrors());
        return "redirect:/?tabName=towary";
    }

    @PutMapping(value = "/update/{id}")
    public String update(@PathVariable int id, @Valid Ware ware, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        if (!bindingResult.hasErrors()) {
            wareService.save(ware);
            return showHome(model);
        }
        attr.addFlashAttribute("errors", bindingResult.getFieldErrors());
        return "redirect:/?tabName=towary";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id, Model model, RedirectAttributes attr) {
        try {
            wareService.delete(id);
        } catch (DataIntegrityViolationException exception) {
            attr.addFlashAttribute("foreignKeyError", "Nie można usunąć wiersza - jest kluczem obcym w innej tabeli");
        }
        return showHome(model);
    }

    @RequestMapping(value = "/search")
    public String search(Model model, String content, RedirectAttributes attr) {
        List<Ware> wares = wareService.findByAllAttributes(content);
        model.addAttribute("wares", wares);
        model.addAttribute("ware", new Ware());
        SortedMap<String, EntityInfo> entitiesInfo = tableNamesService.getDisplayableTableNames();
        Pair<String, SortedMap<String, EntityInfo>> data = new Pair<>("towary", entitiesInfo);
        model.addAttribute("data", data);
        return "fragments/table :: tableDiv";
    }
}
