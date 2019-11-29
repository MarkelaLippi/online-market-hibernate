package com.gmail.roadtojob2019.servicemodule.services;

import com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAllUsers();
}
