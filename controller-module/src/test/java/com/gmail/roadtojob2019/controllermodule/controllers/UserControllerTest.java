package com.gmail.roadtojob2019.controllermodule.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.roadtojob2019.repositorymodule.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ObjectMapper objectMapper;

    @Test
    void getAllUsersPaginatedAndSortedByEmail() throws Exception {
        willReturn(Collections.EMPTY_LIST).given(userRepository).findAll();
        mockMvc.perform(get("/users").contentType("application/json"))
                .andExpect(status().is3xxRedirection());
    }
}