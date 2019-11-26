package com.gmail.roadtojob2019.servicemodule.services.converters;

import com.gmail.roadtojob2019.repositorymodule.models.User;
import com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO;

public interface UserConverter {
   UserDTO userToDTO(User user);
   User dtoToUser(UserDTO userDTO);
}
