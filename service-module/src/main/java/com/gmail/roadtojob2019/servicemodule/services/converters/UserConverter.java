package com.gmail.roadtojob2019.servicemodule.services.converters;

import com.gmail.roadtojob2019.repositorymodule.models.User;

public interface UserConverter {
   com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO userToDTO(User user);
   User dtoToUser(com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO userDTO);
}
