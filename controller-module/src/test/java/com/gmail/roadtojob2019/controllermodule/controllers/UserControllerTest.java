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

import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    void getPageOfUsersSortedByEmail_pageNumberAndPageSize_returnUsersPageSortedByEmail() throws Exception {
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

//        verify(userRepository,times(1)).findAll(pageParameters);
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

    @Test
    void deleteCheckedUsers_arrayOfUserID_deleteUsers() throws Exception {
        //given
        final List<Long> usersIDs = List.of(1L, 2L);
        willDoNothing().given(userRepository).deleteUsersByIdIn(usersIDs);
        //when
//        mockMvc.perform(post("/users/delete?usersIDs=1&usersIDs=2"))
        mockMvc.perform(post("/users/delete").param("usersIDs", "1", "2"))
                //then
                .andExpect(status().is3xxRedirection());
    }
}