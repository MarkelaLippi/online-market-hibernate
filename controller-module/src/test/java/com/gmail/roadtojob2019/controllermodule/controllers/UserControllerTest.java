package com.gmail.roadtojob2019.controllermodule.controllers;

import com.gmail.roadtojob2019.repositorymodule.models.User;
import com.gmail.roadtojob2019.repositorymodule.repositories.UserRepository;
import com.gmail.roadtojob2019.servicemodule.services.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.floatThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestService testService;

    @MockBean
    @Autowired
    private UserRepository userRepository;

    @Test
    void testGetPageOfUsersSortedByEmailIsOk() throws Exception {
        //given
        final int pageNumber = 1;
        final int pageSize = 10;
        final String sortField = "email";
        final Sort.Direction sortDirection = Sort.Direction.ASC;
        final Pageable pageParameters = PageRequest.of(pageNumber, pageSize, sortDirection, sortField);
        final User user = testService.getUser();
        final List<User> users = List.of(user);
        final long totalAmountOfUsers = 1L;
        final Page<User> page = new PageImpl<User>(users, pageParameters, totalAmountOfUsers);
        willReturn(page).given(userRepository).findAll(pageParameters);
        //when
        mockMvc.perform(get("/users"))
                //then
                .andExpect(status().isOk());
        verify(userRepository, times(1)).findAll(pageParameters);
    }

    @Test
    void testDeleteCheckedUsersIsOk() throws Exception {
        //given
        final List<Long> usersIDs = List.of(1L, 2L);
        willDoNothing().given(userRepository).deleteUsersByIdIn(usersIDs);
        //when
        mockMvc.perform(post("/users/delete").param("usersIDs", "1", "2"))
                //then
                .andExpect(status().isOk());
        verify(userRepository, times(1)).deleteUsersByIdIn(usersIDs);
    }

    @Test
    void testChangeUserPasswordAndSendItByEmailIsOk() throws Exception {
        //given
        final User user = testService.getUser();
        final Long userId = user.getId();
        final Optional<User> userBeforeChangingPassword = Optional.of(user);
        willReturn(userBeforeChangingPassword).given(userRepository).findById(userId);
        //when
        mockMvc.perform(post("/users/change/password").param("userID", userId.toString()))
                //then
                .andExpect(status().isOk());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testChangeUserPasswordAndSendItByEmailThrowsOnlineMarketSuchUserNotFoundException() throws Exception {
        //given
        final Long userId = 10L;
        willReturn(Optional.empty()).given(userRepository).findById(userId);
        //when
        mockMvc.perform(post("/users/change/password").param("userID", userId.toString()))
                //then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errorMessage").value("User with id = " + userId + " was not found"));
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testChangeUserRoleIsOk() throws Exception {
        //given
        final User user = testService.getUser();
        final Long userId = user.getId();
        final Optional<User> userBeforeChangingRole = Optional.of(user);
        willReturn(userBeforeChangingRole).given(userRepository).findById(userId);
        final String newUserRole = "SALE_USER";
        final MultiValueMap<String, String> requestParameters = new LinkedMultiValueMap<>();
        requestParameters.add("userID", userId.toString());
        requestParameters.add("userRole", newUserRole);
        //when
        mockMvc.perform(post("/users/change/role").params(requestParameters))
                //then
                .andExpect(status().isOk());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testChangeUserRoleThrowsOnlineMarketSuchUserNotFoundException() throws Exception {
        //given
        final Long userId = 10L;
        willReturn(Optional.empty()).given(userRepository).findById(userId);
        final String newUserRole = "SALE_USER";
        final MultiValueMap<String, String> requestParameters = new LinkedMultiValueMap<>();
        requestParameters.add("userID", userId.toString());
        requestParameters.add("userRole", newUserRole);
        //when
        mockMvc.perform(post("/users/change/role").params(requestParameters))
                //then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errorMessage").value("User with id = " + userId + " was not found"));
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testAddUserIsOk() throws Exception {
        //given
        final User newUser = testService.getUser();
        willReturn(newUser).given(userRepository).save(any(User.class));
        //when
        mockMvc.perform(post("/users/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("  {\n" +
                        "      \"lastName\" : \"Rogov\", \n" +
                        "      \"name\" : \"Petr\", \n" +
                        "      \"middleName\" : \"Petrovich\", \n" +
                        "      \"email\" : \"Rogov@gmail.com\", \n" +
                        "      \"password\" : \"1234\", \n" +
                        "      \"role\" : \"CUSTOMER_USER\" \n" +
                        "   }\n"))
                .andExpect(status().isOk());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testGetProfileIsOk() throws Exception {
        //given
        final User user = testService.getUser();
        final Optional<User> requiredUser = Optional.of(user);
        final Long userID=user.getId();
        willReturn(requiredUser).given(userRepository).findById(userID);
        //when
        mockMvc.perform(get("/users/profile/1"))
                //then
                .andExpect(status().isOk());
    }
}