package com.bsk.controllers;

import com.bsk.domain.Sale;
import com.bsk.dto.SaleDTO;
import com.bsk.mapper.SaleMapper;
import com.bsk.services.SaleService;
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
@RequestMapping(value = "/sales")
public class SaleController {

    private SaleService saleService;
    private SaleMapper saleMapper;

    public SaleController(SaleService saleService, SaleMapper saleMapper) {
        this.saleService = saleService;
        this.saleMapper = saleMapper;
    }

    private String showHome(Model model) {
        model.addAttribute("sales", saleService.read());
        return "redirect:/?tabName=sprzedaze";
    }

    @PostMapping(value = "/create")
    public String create(@Valid @ModelAttribute("saleDTO") SaleDTO saleDTO, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            Sale sale = saleMapper.map(saleDTO);
            saleService.save(sale);
            return showHome(model);
        }
        return "redirect:/?tabName=sprzedaze";
    }

    @PutMapping(value = "/update/{id}")
    public String update(@PathVariable int id, @Valid Sale sale, Model model) {
        saleService.save(sale);
        return showHome(model);
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
