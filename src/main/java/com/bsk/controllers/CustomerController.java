package com.bsk.controllers;

import com.bsk.domain.Customer;
import com.bsk.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller("/customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "customers/add")
    public String add(Customer customer, Model model) {
        customerService.add(customer);
        model.addAttribute("allCustomers", customerService.getCustomers());
        return "redirect:/dashboard";
    }

    @DeleteMapping(value = "customers/delete/{nip}")
    public String delete(@PathVariable String nip, Model model) {
        customerService.remove(nip);
        model.addAttribute("allCustomers", customerService.getCustomers());
        return "redirect:/dashboard";
    }

    //@PutMapping(value = "/")
}
