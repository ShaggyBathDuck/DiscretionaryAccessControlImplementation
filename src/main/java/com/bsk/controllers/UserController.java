package com.bsk.controllers;


import com.bsk.domain.User;
import com.bsk.repositories.UserRepository;
import com.bsk.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private UserService userService;

    private UserRepository userRepository;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    public String showHome(Model model) {
        model.addAttribute("users", userService.read());
        return "redirect:/dashboard?tabName=uzytkownicy";
    }

    @GetMapping
    List<User> findAll(){
        return userRepository.findAll();
    }

    @PostMapping("/create")
    public String create(User user, Model model) {
        userService.save(user);
        return showHome(model);
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable Integer id, User user, Model model) {
        userService.save(user);
        return showHome(model);
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        userService.delete(id);
        return showHome(model);
    }
}
