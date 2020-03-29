package com.gmail.roadtojob2019.servicemodule.services.impl;

import com.gmail.roadtojob2019.repositorymodule.models.User;
import com.gmail.roadtojob2019.repositorymodule.repositories.UserRepository;
import com.gmail.roadtojob2019.servicemodule.services.EmailService;
import com.gmail.roadtojob2019.servicemodule.services.UserPasswordGenerator;
import com.gmail.roadtojob2019.servicemodule.services.UserService;
import com.gmail.roadtojob2019.servicemodule.services.converters.UserConverter;
import com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO;
import com.gmail.roadtojob2019.servicemodule.services.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserConverter userConverter;

    private final UserPasswordGenerator userPasswordGenerator;

    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;


    @Override
    @Transactional
    public Page<UserDTO> getPageOfUsersSortedByEmail(int pageNumber, int pageSize) {
        final String SORTING_PARAMETER = "email";
        Pageable pageParameters = PageRequest.of(pageNumber - 1, pageSize, Sort.Direction.ASC, SORTING_PARAMETER);
        return userRepository
                .findAll(pageParameters)
                .map(userMapper::userToUserDto);
    }

    @Override
    @Transactional
    public void deleteCheckedUsers(int[] usersIDs) {
        List<Long> usersIDsAsLong = Arrays.stream(usersIDs)
                .asLongStream()
                .boxed()
                .collect(Collectors.toList());
        userRepository.deleteUsersByIdIn(usersIDsAsLong);
/*
        List<User> checkedUsers = userRepository.findAllById(usersIDsAsLong);
        userRepository.deleteAll(checkedUsers);
*/
    }

    @Override
    @Transactional
    public List<UserDTO> findAllUsers() {
        List<UserDTO> userDTOs = userRepository
                .findAll()
                .stream()
                .map(userConverter::userToDTO)
                .collect(Collectors.toList());
        return userDTOs;
    }

    @Override
    @Transactional
    public void changeUserPassword(Long id) {
        final String MAILSUBJECT = "Your password was changed";
        final String PASSWORD = userPasswordGenerator.generateRandomPassword();
        UserDTO userDTO = userConverter.userToDTO(userRepository.getOne(id));
        userDTO.setPassword(PASSWORD);
        User user = userConverter.dtoToUser(userDTO);
        userRepository.save(user);
        emailService.sendUserPassword(user.getEmail(), MAILSUBJECT, PASSWORD);
    }

    @Override
    @Transactional
    public void changeUserRole(Long id, String role) {
        UserDTO userDTO = userConverter.userToDTO(userRepository.getOne(id));
        userDTO.setRole(role);
        User user = userConverter.dtoToUser(userDTO);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void addUser(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userConverter.dtoToUser(userDTO);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDTO findUserByEmail(String email) {
        return userConverter.userToDTO(userRepository.findUserByEmail(email));
    }
}
