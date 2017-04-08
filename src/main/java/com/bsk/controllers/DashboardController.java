package com.bsk.controllers;


import com.bsk.domain.Customer;
import com.bsk.services.CustomerService;
import com.bsk.services.UserService;
import com.bsk.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DashboardController {

    private UserService userService;
    private CustomerService customerService;

    public DashboardController(UserService userService, CustomerService customerService) {
        this.userService = userService;
        this.customerService = customerService;
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @ModelAttribute("allUsers")
    @ResponseBody
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @ModelAttribute("allCustomers")
    @ResponseBody
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }
}
