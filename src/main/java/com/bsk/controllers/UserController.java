package com.bsk.controllers;


import com.bsk.domain.User;
import com.bsk.services.TableNamesService;
import com.bsk.services.UserService;
import com.bsk.util.EntityInfo;
import javafx.util.Pair;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.SortedMap;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    private TableNamesService tableNamesService;

    public UserController(UserService userService, PasswordEncoder passwordEncoder, TableNamesService tableNamesService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.tableNamesService = tableNamesService;
    }

    public String showHome(Model model) {
        model.addAttribute("users", userService.read());
        return "redirect:/?tabName=uzytkownicy";
    }

    @GetMapping
    List<User> findAll() {
        return userService.read();
    }

    @PostMapping("/create")
    public String create(@Valid User user, Model model) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return showHome(model);
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable int id, @Valid User user, Model model) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return showHome(model);
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id, Model model, RedirectAttributes attr) {
        try {
            userService.delete(id);
        } catch (DataIntegrityViolationException exception) {
            attr.addFlashAttribute("foreignKeyError", "Nie można usunąć wiersza - jest kluczem obcym w innej tabeli");
        }
        return showHome(model);
    }

    @RequestMapping(value = "/search")
    public String search(Model model, String content, RedirectAttributes attr) {
        List<User> users = userService.findByAllAttributes(content);
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        SortedMap<String, EntityInfo> entitiesInfo = tableNamesService.getDisplayableTableNames();
        Pair<String, SortedMap<String, EntityInfo>> data = new Pair<>("uzytkownicy", entitiesInfo);
        model.addAttribute("data", data);
        return "fragments/table :: tableDiv";
    }
}
