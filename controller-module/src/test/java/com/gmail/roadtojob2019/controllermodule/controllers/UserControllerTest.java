package com.gmail.roadtojob2019.controllermodule.controllers;

import com.gmail.roadtojob2019.repositorymodule.models.Role;
import com.gmail.roadtojob2019.repositorymodule.models.User;
import com.gmail.roadtojob2019.repositorymodule.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willReturn;
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

    @Mock
    private UserRepository userRepository;

    @Test
    void testGetPageOfUsersSortedByEmailIsOk() throws Exception {
        //given
        final Pageable pageParameters = PageRequest.of(1, 10, Sort.Direction.ASC, "email");
        final User user = getUser();
        final List<User> users = List.of(user);
        final Page<User> page = new PageImpl<User>(users, pageParameters, 1L);
        willReturn(page).given(userRepository).findAll(pageParameters);
        //when
        mockMvc.perform(get("/users"))
                //then
                .andExpect(status().isOk());
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
    }

    @Test
    void testChangeUserPasswordAndSendItByEmailIsOk() throws Exception {
        //given
        final User user = getUser();
        final Long userId = user.getId();
        final Optional<User> userBeforeChangingPassword = Optional.of(user);
        willReturn(userBeforeChangingPassword).given(userRepository).findById(userId);
        //when
        mockMvc.perform(post("/users/change/password").param("id", userId.toString()))
                //then
                .andExpect(status().isOk());
    }

    @Test
    void testChangeUserPasswordAndSendItByEmailThrowsOnlineMarketSuchUserNotFoundException() throws Exception {
        //given
        final Long userId = 10L;
        willReturn(Optional.empty()).given(userRepository).findById(userId);
        //when
        mockMvc.perform(post("/users/change/password").param("id", userId.toString()))
                //then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errorMessage").value("User with id = " + userId + " was not found"));
    }

    private User getUser() {
        return new User(1L,
                "Rogov",
                "Petr",
                "Petrovich",
                "Rogov@gmail.com",
                "1234",
                Role.ADMINISTRATOR,
                true,
                Collections.emptySet(),
                Collections.emptySet(),
                Collections.emptySet()
        );
    }
}