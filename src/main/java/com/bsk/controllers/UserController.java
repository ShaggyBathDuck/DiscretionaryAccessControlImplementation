package com.bsk.controllers;


import com.bsk.domain.User;
import com.bsk.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService, DashboardController dashboardController) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public String createUser(@RequestBody User user, Model model) {
        userService.create(user);
        model.addAttribute("users", userService.read());
        return "redirect:/dashboard";
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable Integer id, Model model) {
        userService.delete(id);
    }
}
