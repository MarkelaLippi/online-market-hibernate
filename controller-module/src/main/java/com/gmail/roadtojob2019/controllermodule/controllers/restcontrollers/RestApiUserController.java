package com.gmail.roadtojob2019.controllermodule.controllers.restcontrollers;

import com.gmail.roadtojob2019.servicemodule.services.UserService;
import com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class RestApiUserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    void addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
    }
}
