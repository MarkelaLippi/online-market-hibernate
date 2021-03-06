package com.gmail.roadtojob2019.servicemodule.services.impl;

import com.gmail.roadtojob2019.repositorymodule.models.Role;
import com.gmail.roadtojob2019.repositorymodule.models.User;
import com.gmail.roadtojob2019.repositorymodule.repositories.UserRepository;
import com.gmail.roadtojob2019.servicemodule.services.EmailService;
import com.gmail.roadtojob2019.servicemodule.services.UserPasswordGenerator;
import com.gmail.roadtojob2019.servicemodule.services.UserService;
import com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO;
import com.gmail.roadtojob2019.servicemodule.services.exception.OnlineMarketSuchUserNotFoundException;
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
    private final UserPasswordGenerator userPasswordGenerator;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public Page<UserDTO> getPageOfUsersSortedByEmail(int pageNumber, int pageSize) {
        final String SORTING_PARAMETER = "email";
        final Pageable pageParameters = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, SORTING_PARAMETER);
        final Page<User> pageOfUsers = userRepository.findAll(pageParameters);
        return pageOfUsers.map(userMapper::userToUserDto);
    }

    @Override
    @Transactional
    public void deleteCheckedUsers(int[] usersIDs) {
        final List<Long> usersIDsAsLong = toLongUsersIDs(usersIDs);
        userRepository.deleteUsersByIdIn(usersIDsAsLong);
    }

    private List<Long> toLongUsersIDs(int[] usersIDs) {
        return Arrays.stream(usersIDs)
                .asLongStream()
                .boxed()
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void changeUserPasswordAndSendItByEmail(Long userID) throws OnlineMarketSuchUserNotFoundException {
        final User user = userRepository.findById(userID)
                .orElseThrow(() -> new OnlineMarketSuchUserNotFoundException("User with id = " + userID + " was not found"));
        final String newUserPassword = changeUserPassword(user);
        SendNewPasswordToUserByEmail(user, newUserPassword);
    }

    private String changeUserPassword(User user) {
        final String newUserPassword = userPasswordGenerator.generateRandomPassword();
        user.setPassword(newUserPassword);
        userRepository.save(user);
        return newUserPassword;
    }

    private void SendNewPasswordToUserByEmail(User user, String newUserPassword) {
        final String userEmail = user.getEmail();
        final String MAIL_SUBJECT = "Your password was changed";
        emailService.sendUserPassword(userEmail, MAIL_SUBJECT, newUserPassword);
    }

    @Override
    @Transactional
    public void changeUserRole(Long userID, String userRole) throws OnlineMarketSuchUserNotFoundException {
        final User user = userRepository.findById(userID)
                .orElseThrow(() -> new OnlineMarketSuchUserNotFoundException("User with id = " + userID + " was not found"));
        final Role newUserRole = Role.valueOf(userRole);
        user.setRole(newUserRole);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public Long addUser(UserDTO userDTO) {
        final String userPassword = userDTO.getPassword();
        final String encodedUserPassword = passwordEncoder.encode(userPassword);
        final User user = userMapper.userDtoToUser(userDTO);
        user.setPassword(encodedUserPassword);
        final User createdUser = userRepository.save(user);
        return createdUser.getId();
    }

    @Override
    @Transactional
    public UserDTO findUserByEmail(String email) {
        final User user = userRepository.findUserByEmail(email);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDTO getUserById(Long userID) throws OnlineMarketSuchUserNotFoundException {
        final User user = userRepository.findById(userID)
                .orElseThrow(() -> new OnlineMarketSuchUserNotFoundException("User with id = " + userID + " was not found"));
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDTO changeProfile(UserDTO userDTO) throws OnlineMarketSuchUserNotFoundException {
        final Long userID = userDTO.getId();
        final User user = userRepository.findById(userID)
                .orElseThrow(() -> new OnlineMarketSuchUserNotFoundException("User with id = " + userID + " was not found"));
        changeUserFields(userDTO, user);
        final User changedUser = userRepository.save(user);
        return userMapper.userToUserDto(changedUser);
    }

    private void changeUserFields(UserDTO userDTO, User user) {
        user.setLastName(userDTO.getLastName());
        user.setName(userDTO.getName());
        user.setAddress(userDTO.getAddress());
        user.setPhone(userDTO.getPhone());
        final String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(encodedPassword);
    }
}
