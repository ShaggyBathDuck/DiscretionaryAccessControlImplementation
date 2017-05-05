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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public String showHome(Model model) {
        model.addAttribute("users", userService.read());
        return "redirect:/dashboard?tabName=uzytkownicy";
    }

    @PostMapping("/create")
    public String create(User user, Model model) {
        userService.save(user);
        return showHome(model);
    }

    @PutMapping("/update")
    public String update(User user, Model model) {
        userService.save(user);
        return showHome(model);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        userService.delete(id);
        return showHome(model);
    }
}
