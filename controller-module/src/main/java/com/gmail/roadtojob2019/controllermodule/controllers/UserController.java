package com.gmail.roadtojob2019.controllermodule.controllers;

import com.gmail.roadtojob2019.repositorymodule.models.Role;
import com.gmail.roadtojob2019.servicemodule.services.RoleService;
import com.gmail.roadtojob2019.servicemodule.services.UserService;
import com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO;
import com.gmail.roadtojob2019.servicemodule.services.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserValidator userValidator;

/*
    @InitBinder("userDTO")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
*/

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

/*
    @PostMapping("/users/add")
    String addUser(@ModelAttribute @Valid UserDTO user, BindingResult result) {
        if(result.hasErrors()) {
            return "usersPage";
        }
        userService.addUser(user);
        return "redirect:/users";
    }
*/
}
