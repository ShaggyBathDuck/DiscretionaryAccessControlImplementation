package com.bsk.controllers;

import com.bsk.domain.Purchase;
import com.bsk.dto.PurchaseDTO;
import com.bsk.mapper.PurchaseMapper;
import com.bsk.services.PurchaseService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/purchases")
public class PurchaseController {

    private PurchaseService purchaseService;
    private PurchaseMapper purchaseMapper;

    public PurchaseController(PurchaseService purchaseService, PurchaseMapper purchaseMapper) {
        this.purchaseService = purchaseService;
        this.purchaseMapper = purchaseMapper;
    }

    private String showHome(Model model) {
        model.addAttribute("purchases", purchaseService.read());
        return "redirect:/?tabName=zakupy";
    }

    @PostMapping(value = "/create")
    public String create(@Valid @ModelAttribute("purchaseDTO") PurchaseDTO purchaseDTO, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            Purchase purchase = purchaseMapper.map(purchaseDTO);
            purchaseService.save(purchase);
            return showHome(model);
        }
        return "redirect:/?tabName=zakupy";
    }

    @PutMapping(value = "/update/{id}")
    public String update(@PathVariable int id, @Valid Purchase purchase, Model model) {
        purchaseService.save(purchase);
        return showHome(model);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        purchaseService.delete(id);
        return showHome(model);
    }

    @InitBinder
    public void dateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }
}