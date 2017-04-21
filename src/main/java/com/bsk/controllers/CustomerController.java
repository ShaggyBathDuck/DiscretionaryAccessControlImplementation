package com.bsk.controllers;

import com.bsk.domain.Customer;
import com.bsk.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller()
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/create")
    public String add(@RequestBody Customer customer, Model model) {
        customerService.create(customer);
        model.addAttribute("customers", customerService.read());
        return "redirect:/dashboard";
    }

    @DeleteMapping(value = "/delete/{nip}")
    public String delete(@PathVariable String nip, Model model) {
        customerService.delete(nip);
        model.addAttribute("customers", customerService.read());
        return "redirect:/dashboard";
    }

    @PutMapping(value = "/update/{nip}")
    public String update(@PathVariable String nip, @RequestBody Customer customer, Model model){
        customerService.update(nip, customer);
        model.addAttribute("customers", customerService.read());
        return "redirect:/dashboard";
    }
}
