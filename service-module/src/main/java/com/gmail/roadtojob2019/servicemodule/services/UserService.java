package com.gmail.roadtojob2019.servicemodule.services;

import com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> findAllUsers();
    Page<UserDTO> findAllUsersPaginatedAndSortedByEmail(int pageNumber, int pageSize);
    void deleteCheckedUsers(int ids[]);
    void changeUserPassword(Long id);
    void changeUserRole(Long id, String role);
    void addUser(UserDTO user);
    UserDTO findUserByEmail(String email);
    //Optional<UserDTO> findUserByEmail(String email);
}
