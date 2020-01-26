package com.gmail.roadtojob2019.servicemodule.services.impl;

import com.gmail.roadtojob2019.repositorymodule.repositories.UserRepository;
import com.gmail.roadtojob2019.repositorymodule.models.User;
import com.gmail.roadtojob2019.servicemodule.services.EmailService;
import com.gmail.roadtojob2019.servicemodule.services.UserPasswordGenerator;
import com.gmail.roadtojob2019.servicemodule.services.UserService;
import com.gmail.roadtojob2019.servicemodule.services.converters.UserConverter;
import com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
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

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private UserPasswordGenerator userPasswordGenerator;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordEncoder passwordEncoder;


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
    public Page<UserDTO> findAllUsersPaginatedAndSortedByEmail(int pageNumber, int pageSize) {
        final String fieldSorting = "email";
        Pageable userPageParameters = PageRequest.of(pageNumber - 1, pageSize, Sort.Direction.ASC, fieldSorting);
        return userRepository
                .findAll(userPageParameters)
                .map(userConverter::userToDTO);
    }

    @Override
    @Transactional
    public void deleteCheckedUsers(int[] ids) {
        List<Long> idsAsLong = Arrays.stream(ids)
                .asLongStream()
                .boxed()
                .collect(Collectors.toList());

        List<User> checkedUsers = userRepository.findAllById(idsAsLong);
        userRepository.deleteAll(checkedUsers);
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
