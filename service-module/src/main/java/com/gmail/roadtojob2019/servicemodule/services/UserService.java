package com.gmail.roadtojob2019.servicemodule.services;

import com.gmail.roadtojob2019.repositorymodule.models.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
}
