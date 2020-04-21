package com.gmail.roadtojob2019.servicemodule.services;

import com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO;
import com.gmail.roadtojob2019.servicemodule.services.exception.OnlineMarketSuchUserNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    Page<UserDTO> getPageOfUsersSortedByEmail(int pageNumber, int pageSize);

    void deleteCheckedUsers(int ids[]);

    void changeUserPasswordAndSendItByEmail(Long id) throws OnlineMarketSuchUserNotFoundException;

    void changeUserRole(Long id, String role) throws OnlineMarketSuchUserNotFoundException;

    Long addUser(UserDTO user);

    UserDTO findUserByEmail(String email);

    UserDTO getUserById(Long userID) throws OnlineMarketSuchUserNotFoundException;

    UserDTO changeProfile(UserDTO userDTO) throws OnlineMarketSuchUserNotFoundException;
}
