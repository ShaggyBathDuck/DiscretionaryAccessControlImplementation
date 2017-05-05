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

    public String showHome(Model model) {
        model.addAttribute("customers", customerService.read());
        return "redirect:/dashboard?tabName=klienci";
    }

    @PostMapping(value = "/create")
    public String create(Customer customer, Model model) {
        customerService.save(customer);
        return showHome(model);
    }

    @PutMapping(value = "/update")
    public String update(Customer customer, Model model) {
        customerService.save(customer);
        return showHome(model);
    }

    @DeleteMapping(value = "/delete/{nip}")
    public String delete(@PathVariable String nip, Model model) {
        customerService.delete(nip);
        return showHome(model);
    }
}
