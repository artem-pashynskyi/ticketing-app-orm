package com.ticketingapp.controller;

import com.ticketingapp.dto.UserDTO;
import com.ticketingapp.service.RoleService;
import com.ticketingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;

    @GetMapping({"/create", "/add", "/initialize"})
    public String createUser(Model model) {
        model.addAttribute("user", new UserDTO());
        model.addAttribute("roles", roleService.listAllRoles());
        model.addAttribute("users", userService.listAllUsers());
        return "user/create";
    }

    @PostMapping("/create")
    public String insertUser(UserDTO user) {
        userService.save(user);
        return "redirect:/user/create";
    }

//    @GetMapping("/edit/{username}")
//    public String editUser(@PathVariable("username") String username, Model model) {
//        model.addAttribute("user", userService.findById(username));
//        model.addAttribute("users", userService.findAll());
//        model.addAttribute("roles", roleService.findAll());
//        return "user/edit";
//    }
//
//    @PostMapping("/update/{username}")
//    public String updateUser(UserDTO user) {
//        userService.update(user);
//        return "redirect:/user/create";
//    }
//
//    @GetMapping("/delete/{username}")
//    private String deleteUser(@PathVariable("username") String username) {
//        userService.deleteById(username);
//        return "redirect:/user/create";
//    }

}
