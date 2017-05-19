package com.bsk.controllers;


import com.bsk.domain.Ware;
import com.bsk.services.WareService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller()
@RequestMapping("/wares")
public class WareController {

    private WareService wareService;

    public WareController(WareService wareService) {
        this.wareService = wareService;
    }

    private String showHome(Model model) {
        model.addAttribute("wares", wareService.read());
        return "redirect:/?tabName=towary";
    }

    @PostMapping(value = "/create")
    public String create(@Valid Ware ware, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            Ware toSave = ware;
            wareService.save(toSave);
            return showHome(model);
        }
        return "redirect:/?tabName=towary";
    }

    @PutMapping(value = "/update/{id}")
    public String update(@PathVariable int id, @Valid Ware ware, Model model) {
        wareService.save(ware);
        return showHome(model);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        wareService.delete(id);
        return showHome(model);
    }
}
