package com.bsk.controllers;


import com.bsk.domain.User;
import com.bsk.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/user/create")
    public String createUser(@RequestBody User user){
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public @ResponseBody List<User> getUsers(){
        return userRepository.findAll();
    }
}
