package com.bsk.controllers;

import com.bsk.domain.Sale;
import com.bsk.services.SaleService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(value = "/sales")
public class SaleController {

    private SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    private String showHome(Model model) {
        model.addAttribute("sales", saleService.read());
        return "redirect:/?tabName=sprzedaze";
    }

    @PostMapping(value = "/create")
    public String create(@Valid @ModelAttribute("sale") Sale sale, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        if (!bindingResult.hasErrors()) {
            saleService.save(sale);
            return showHome(model);
        }
        attr.addFlashAttribute("errors", bindingResult.getFieldErrors());
        return "redirect:/?tabName=sprzedaze";
    }

    @PutMapping(value = "/update/{id}")
    public String update(@PathVariable int id, @Valid Sale sale, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        if (!bindingResult.hasErrors()) {
            saleService.save(sale);
            return showHome(model);
        }
        attr.addFlashAttribute("errors", bindingResult.getFieldErrors());
        return "redirect:/?tabName=sprzedaze";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        saleService.delete(id);
        return showHome(model);
    }


    @InitBinder
    public void dateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }
}
