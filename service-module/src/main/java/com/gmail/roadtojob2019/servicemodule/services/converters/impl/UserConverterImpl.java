package com.gmail.roadtojob2019.servicemodule.services.converters.impl;

import com.gmail.roadtojob2019.repositorymodule.models.Role;
import com.gmail.roadtojob2019.repositorymodule.models.User;
import com.gmail.roadtojob2019.servicemodule.services.converters.ReviewConverter;
import com.gmail.roadtojob2019.servicemodule.services.converters.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserConverterImpl implements UserConverter {
    @Autowired
    ReviewConverter reviewConverter;

    @Override
    public com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO userToDTO(User user) {
        com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO userDTO = new com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLastName(user.getLastName());
        userDTO.setName(user.getName());
        userDTO.setMiddleName(user.getMiddleName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole().toString());
        userDTO.setActive(user.isActive());
        userDTO.setReviewDTOs(user.getReviews()
                .stream()
                .map(reviewConverter::reviewToDTO)
                .collect(Collectors.toSet()));
        return userDTO;
    }

    @Override
    public User dtoToUser(com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setLastName(userDTO.getLastName());
        user.setName(userDTO.getName());
        user.setMiddleName(userDTO.getMiddleName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(Role.valueOf(userDTO.getRole()));
        if (userDTO.getActive() == null) {
            user.setActive(true);
        } else {
            user.setActive(userDTO.getActive());
        }

        //if (userDTO.getReviewDTOs()==null)
        user.setReviews(userDTO.getReviewDTOs()
                .stream()
                .map(reviewConverter::dtoToReview)
                .collect(Collectors.toSet()));
        return user;
    }
}
