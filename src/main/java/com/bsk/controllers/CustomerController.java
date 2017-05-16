package com.bsk.controllers;

import com.bsk.domain.Customer;
import com.bsk.dto.CustomerDTO;
import com.bsk.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    public String create(@Valid @ModelAttribute("customerDTO") CustomerDTO customerDTO, BindingResult bindingResult, Model model) {
        if(!bindingResult.hasErrors()){
            Customer customer= new Customer(customerDTO.getNip(),
                    customerDTO.getName(),
                    customerDTO.getPhoneNumber(),
                    customerDTO.getStreet(),
                    customerDTO.getHouseNumber(),
                    customerDTO.getFlatNumber(),
                    customerDTO.getPostalCode(),
                    customerDTO.getCity(),
                    customerDTO.getDiscount());
            customerService.save(customer);
            return showHome(model);
        }
        return "redirect:/?tabName=klienci";
    }

    @PutMapping(value = "/update/{id}")
    public String update(@PathVariable int id, @Valid Customer customer, Model model) {
        customerService.save(customer);
        return showHome(model);
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        customerService.delete(id);
        return showHome(model);
    }
}
