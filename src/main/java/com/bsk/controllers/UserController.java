package com.bsk.controllers;


import com.bsk.services.UserService;
import com.bsk.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public String createUser(@RequestBody User user){
        userService.createUser(user);
        return "redirect:/users";
    }
}
