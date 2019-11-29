package com.gmail.roadtojob2019.controllermodule.controllers;

import com.gmail.roadtojob2019.servicemodule.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    String getAllUsers (Model model){
        model.addAttribute("users", userService.findAllUsers());
        return "usersPage";
    }
}
