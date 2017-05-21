package com.bsk.controllers;

import com.bsk.domain.SalePosition;
import com.bsk.services.SalePositionService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "salepositions")
public class SalePositionController {

    private SalePositionService salePositionService;

    public SalePositionController(SalePositionService salePositionService) {
        this.salePositionService = salePositionService;
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
}
