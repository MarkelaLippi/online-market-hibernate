package com.gmail.roadtojob2019.servicemodule.services.impl;

import com.gmail.roadtojob2019.repositorymodule.UserRepository;
import com.gmail.roadtojob2019.repositorymodule.models.User;
import com.gmail.roadtojob2019.servicemodule.services.UserService;
import com.gmail.roadtojob2019.servicemodule.services.converters.UserConverter;
import com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        final String PASSWORD = "new password";
        UserDTO userDTO = userConverter.userToDTO(userRepository.getOne(id));
        userDTO.setPassword(PASSWORD);
        User user = userConverter.dtoToUser(userDTO);
        userRepository.save(user);
    }
}
