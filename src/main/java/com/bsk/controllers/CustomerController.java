package com.bsk.controllers;

import com.bsk.domain.Customer;
import com.bsk.services.CustomerService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller()
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    private String showHome(Model model) {
        model.addAttribute("customers", customerService.read());
        return "redirect:/?tabName=klienci";
    }

    @PostMapping(value = "/create")
    public String create(@Valid Customer customer, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        if (!bindingResult.hasErrors()) {
            customerService.save(customer);
            return showHome(model);
        }
        attr.addFlashAttribute("errors", bindingResult.getFieldErrors());
        return "redirect:/?tabName=klienci";
    }

    @PutMapping(value = "/update/{id}")
    public String update(@PathVariable int id, @Valid Customer customer, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        if (!bindingResult.hasErrors()) {
            customerService.save(customer);
            return showHome(model);
        }
        attr.addFlashAttribute("errors", bindingResult.getFieldErrors());
        return "redirect:/?tabName=klienci";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id, Model model, RedirectAttributes attr) {
        try {
            customerService.delete(id);
        } catch (DataIntegrityViolationException exception) {
            attr.addFlashAttribute("foreignKeyError", "Nie można usunąć wiersza - jest kluczem obcym w innej tabeli");
        }
        return showHome(model);
    }
}
