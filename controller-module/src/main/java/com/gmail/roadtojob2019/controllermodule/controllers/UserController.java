package com.gmail.roadtojob2019.controllermodule.controllers;

import com.gmail.roadtojob2019.repositorymodule.models.Role;
import com.gmail.roadtojob2019.servicemodule.services.RoleService;
import com.gmail.roadtojob2019.servicemodule.services.UserService;
import com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/users")
    String getAllUsersPaginatedAndSortedByEmail(@RequestParam Optional<Integer> page,
                                                @RequestParam Optional<Integer> size,
                                                Model model) {
        Integer currentPage = page.orElse(1);
        Integer sizePage = size.orElse(10);
        Page<UserDTO> users = userService.findAllUsersPaginatedAndSortedByEmail(currentPage, sizePage);
        model.addAttribute("users", users);
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("roles", roles);
        return "usersPage";
    }

    @PostMapping("/users/delete")
    String deleteCheckedUsers(@RequestParam(required = false, name = "ids") int[] ids) {
        if (ids == null) {
            return "redirect:/users";
        }
        userService.deleteCheckedUsers(ids);
        return "redirect:/users";
    }

    @PostMapping("/users/change/password")
    String changeUserPassword(@RequestParam(name = "id") Long id) {
        userService.changeUserPassword(id);
        return "redirect:/users";
    }

    @PostMapping("/users/change/role")
    String changeUserRole(@RequestParam(name = "id") Long id, @RequestParam(name = "role") String role) {
        userService.changeUserRole(id, role);
        return "redirect:/users";
    }

    @PostMapping("/users/add")
    String addUser(@RequestParam(name = "last name") String lastName,
                   @RequestParam(name = "first name") String firstName,
                   @RequestParam(name = "middle name") String middleName,
                   @RequestParam(name = "email") String email,
                   @RequestParam(name = "role") String role) {

        return "redirect:/users";
    }
}
