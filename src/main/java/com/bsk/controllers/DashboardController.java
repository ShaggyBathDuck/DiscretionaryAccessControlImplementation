package com.bsk.controllers;


import com.bsk.domain.Customer;
import com.bsk.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DashboardController {

    private CustomerService customerService;

    public DashboardController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @ModelAttribute("allCustomers")
    @ResponseBody
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }
}
