package com.bsk.controllers;


import com.bsk.domain.Customer;
import com.bsk.services.CustomerService;
import com.bsk.services.UserService;
import com.bsk.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/dashboard/add")
    public String add(Customer customer, Model model){
        customerService.add(customer);
        model.addAttribute("allCustomers", customerService.getCustomers());
        return "redirect:/dashboard";
    }

    @DeleteMapping(value = "/dashboard/delete/{nip}")
    public String delete(@PathVariable String nip, Model model){
        customerService.remove(nip);
        model.addAttribute("allCustomers", customerService.getCustomers());
        return "redirect:/dashboard";
    }
}
