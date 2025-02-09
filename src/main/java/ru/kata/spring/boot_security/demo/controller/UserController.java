package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAll());

        return "users"; // имя представления;
    }

    @GetMapping("/new")
    public String getAddPage(@ModelAttribute("user") User user) {

        return "new";
    }

    @GetMapping("/update")
    public String getUpdatePage(@ModelAttribute("user") User user) {

        return "update";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam Long id) {
        userService.updateUser(id, user);

        return "redirect:/users";
    }

    @PostMapping("/new")
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam("selectedIds") Long[] selectedIds,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors() || user.getPassword() == null || user.getPassword().isEmpty()) {
            // Верните пользователя на форму с сообщением об ошибке
            return "redirect:/new?error=Password cannot be empty";
        }

        userService.saveUser(user,selectedIds);

        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);

        return "redirect:/users";
    }

}
