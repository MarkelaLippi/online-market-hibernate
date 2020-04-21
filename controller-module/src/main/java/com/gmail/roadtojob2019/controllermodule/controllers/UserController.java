package com.gmail.roadtojob2019.controllermodule.controllers;

import com.gmail.roadtojob2019.repositorymodule.models.Role;
import com.gmail.roadtojob2019.servicemodule.services.RoleService;
import com.gmail.roadtojob2019.servicemodule.services.UserService;
import com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO;
import com.gmail.roadtojob2019.servicemodule.services.exception.OnlineMarketSuchUserNotFoundException;
import com.gmail.roadtojob2019.servicemodule.services.validators.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    String getPageOfUsersSortedByEmail(@RequestParam Optional<Integer> pageNumber,
                                       @RequestParam Optional<Integer> pageSize,
                                       Model model) {
        final Integer currentPageNumber = pageNumber.orElse(1);
        final Integer currentPageSize = pageSize.orElse(10);
        final Page<UserDTO> users = userService.getPageOfUsersSortedByEmail(currentPageNumber, currentPageSize);
        model.addAttribute("users", users);
        final List<Role> roles = roleService.getRoles();
        model.addAttribute("roles", roles);
        return "users";
    }

    @PostMapping("/users/delete")
    @ResponseStatus(HttpStatus.OK)
    String deleteCheckedUsers(@RequestParam final int[] usersIDs) {
        if (usersIDs != null) {
            userService.deleteCheckedUsers(usersIDs);
        }
        return "forward:/users";
    }

    @PostMapping("/users/change/password")
    @ResponseStatus(HttpStatus.OK)
    String changeUserPasswordAndSendItByEmail(@RequestParam Long userID) throws OnlineMarketSuchUserNotFoundException {
        userService.changeUserPasswordAndSendItByEmail(userID);
        return "forward:/users";
    }

    @PostMapping("/users/change/role")
    @ResponseStatus(HttpStatus.OK)
    String changeUserRole(@RequestParam Long userID, @RequestParam String userRole) throws OnlineMarketSuchUserNotFoundException {
        userService.changeUserRole(userID, userRole);
        return "forward:/users";
    }

    @PostMapping("/users/add")
    @ResponseStatus(HttpStatus.CREATED)
    String addUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        userValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "forward:/user";
        } else {
            userService.addUser(userDTO);
            return "forward:/users";
        }
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    String getAddUserPage(Model model) {
        final UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/users/profile/{userID}")
    @ResponseStatus(HttpStatus.OK)
    String getProfile(@PathVariable Long userID, Model model) throws OnlineMarketSuchUserNotFoundException {
        final UserDTO user = userService.getUserById(userID);
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/users/profile/change")
    @ResponseStatus(HttpStatus.OK)
    String changeUserProfile(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) throws OnlineMarketSuchUserNotFoundException {
        userValidator.validate(userDTO, bindingResult);
        if (!bindingResult.hasErrors()) {
            userService.changeProfile(userDTO);
        }
        return "forward:/users/profile/" + userDTO.getId() + "";
    }
}