package com.bsk.controllers;

import com.bsk.domain.PurchasePosition;
import com.bsk.dto.PurchasePositionDTO;
import com.bsk.mapper.PurchasePositionMapper;
import com.bsk.services.PurchasePositionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/purchasepositions")
public class PurchasePositionController {

    private PurchasePositionService purchasePositionService;
    private PurchasePositionMapper purchasePositionMapper;

    public PurchasePositionController(PurchasePositionService purchasePositionService, PurchasePositionMapper purchasePositionMapper) {
        this.purchasePositionService = purchasePositionService;
        this.purchasePositionMapper = purchasePositionMapper;
    }

    private String showHome(Model model) {
        model.addAttribute("purchasepositions", purchasePositionService.read());
        return "redirect:/?tabName=pozycjezakupow";
    }

    @PostMapping(value = "/create")
    public String create(@Valid @ModelAttribute("purchasepositionDTO") PurchasePositionDTO purchasePositionDTO, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            PurchasePosition purchasePosition = purchasePositionMapper.map(purchasePositionDTO);
            purchasePositionService.save(purchasePosition);
            return showHome(model);
        }
        return "redirect:/?tabName=pozycjezakupow";
    }

    @PutMapping(value = "/update/{id}")
    public String update(@PathVariable int id, @Valid PurchasePosition purchasePosition, Model model) {
        purchasePositionService.save(purchasePosition);
        return showHome(model);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        purchasePositionService.delete(id);
        return showHome(model);
    }

}
