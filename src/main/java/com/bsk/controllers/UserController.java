package com.bsk.controllers;


import com.bsk.domain.User;
import com.bsk.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public String createUser(User user, Model model) {
        userService.create(user);
        model.addAttribute("users", userService.read());
        return "redirect:/dashboard";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        userService.delete(id);
        model.addAttribute("users", userService.read());
        return "redirect:/dashboard";
    }
}
