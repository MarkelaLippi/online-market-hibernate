package com.gmail.roadtojob2019.controllermodule.controllers;

import com.gmail.roadtojob2019.repositorymodule.models.Role;
import com.gmail.roadtojob2019.servicemodule.services.RoleService;
import com.gmail.roadtojob2019.servicemodule.services.UserService;
import com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO;
import com.gmail.roadtojob2019.servicemodule.services.validators.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final UserValidator userValidator;

    @GetMapping("/users")
    String getPageOfUsersSortedByEmail(@RequestParam Optional<Integer> pageNumber,
                                       @RequestParam Optional<Integer> pageSize,
                                       Model model) {
        Integer currentPageNumber = pageNumber.orElse(1);
        Integer currentPageSize = pageSize.orElse(10);
        Page<UserDTO> users = userService.getPageOfUsersSortedByEmail(currentPageNumber, currentPageSize);
        model.addAttribute("users", users);
        List<Role> roles = roleService.getRoles();
        model.addAttribute("roles", roles);
        return "users";
    }

    @PostMapping("/users/delete")
    String deleteCheckedUsers(@RequestParam final int[] usersIDs) {
        if (usersIDs != null) {
            userService.deleteCheckedUsers(usersIDs);
        }
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

    @GetMapping("/user")
    String getAddUserPage(Model model) {
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "addUserPage";
    }

    @PostMapping("/users/add")
    String addUser(@Valid @ModelAttribute UserDTO userDTO, BindingResult bindingResult) {
        userValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "redirect:/user";
        } else {
            userService.addUser(userDTO);
            return "redirect:/users";
        }
    }
}