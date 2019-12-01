package com.gmail.roadtojob2019.controllermodule.controllers;

import com.gmail.roadtojob2019.servicemodule.services.UserService;
import com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    String getAllUsersPaginatedAndSortedByEmail(@RequestParam Optional<Integer> page,
                                                @RequestParam Optional<Integer> size,
                                                Model model) {
        Integer currentPage = page.orElse(1);
        Integer sizePage = size.orElse(10);
        Page<UserDTO> users = userService.findAllUsersPaginatedAndSortedByEmail(currentPage, sizePage);
        model.addAttribute("users", users);
        return "usersPage";
    }

    @PostMapping("/users/delete")
    String deleteCheckedUsers(@RequestParam(required = false, name = "ids") int[] ids) {
        if (ids==null){
            return "redirect:/users";
        }
        userService.deleteCheckedUsers(ids);
        return "redirect:/users";
    }

    @PostMapping("/users/change/password")
    String changeUserPassword(@RequestParam(name = "id") Long id) {
        System.out.println(id);
        userService.changeUserPassword(id);
        return "redirect:/users";
    }
}
