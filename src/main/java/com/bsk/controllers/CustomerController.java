package com.bsk.controllers;

import com.bsk.domain.Customer;
import com.bsk.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(value = "/customers/update/{nip}")
    public String update(@PathVariable String nip, @RequestBody Customer customer, Model model){
        customerService.update(nip, customer);
        model.addAttribute("allCustomers", customerService.getCustomers());
        return "redirect:/dashboard";
    }
}
