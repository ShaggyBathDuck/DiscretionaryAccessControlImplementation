package com.bsk.controllers;

import com.bsk.domain.Customer;
import com.bsk.services.CustomerService;
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


@Controller()
@RequestMapping("/customers")
@SessionAttributes("customers")
public class CustomerController {

    private CustomerService customerService;
    private TableNamesService tableNamesService;

    public CustomerController(CustomerService customerService, TableNamesService tableNamesService) {
        this.customerService = customerService;
        this.tableNamesService = tableNamesService;
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

    @RequestMapping(value = "/search")
    public String search(Model model, String content, RedirectAttributes attr) {
        List<Customer> customers = customerService.findByAllAttributes(content);
        model.addAttribute("customers", customers);
        model.addAttribute("customer", new Customer());
        SortedMap<String, EntityInfo> entitiesInfo = tableNamesService.getDisplayableTableNames();
        Pair<String, SortedMap<String, EntityInfo>> data = new Pair<>("klienci", entitiesInfo);
        model.addAttribute("data", data);
        return "fragments/table :: tableDiv";
    }
}
