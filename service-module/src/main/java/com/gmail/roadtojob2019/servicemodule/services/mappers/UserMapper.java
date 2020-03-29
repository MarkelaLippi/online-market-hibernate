package com.gmail.roadtojob2019.servicemodule.services.mappers;

import com.gmail.roadtojob2019.repositorymodule.models.User;
import com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userToUserDto(User user);
}
