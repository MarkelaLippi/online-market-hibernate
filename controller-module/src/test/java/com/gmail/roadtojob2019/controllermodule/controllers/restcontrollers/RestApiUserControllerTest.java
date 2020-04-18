package com.gmail.roadtojob2019.controllermodule.controllers.restcontrollers;

import com.gmail.roadtojob2019.repositorymodule.models.User;
import com.gmail.roadtojob2019.repositorymodule.repositories.UserRepository;
import com.gmail.roadtojob2019.servicemodule.services.TestService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class RestApiUserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestService testService;

    @Mock
    private UserRepository userRepository;

    @Test
    void testAddUserIsCreated() throws Exception {
        //given
        final User user = testService.getUser();
        final String createdUserID = "5";
        willReturn(user).given(userRepository).save(user);
        //when
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"lastName\" : \"Rogov\",\n" +
                        "  \"name\" : \"Petr\",\n" +
                        "  \"middleName\" : \"Petrovich\",\n" +
                        "  \"email\" : \"Rogov@gmail.com\",\n" +
                        "  \"password\" : \"1234\",\n" +
                        "  \"role\" : \"CUSTOMER_USER\",\n" +
                        "  \"isActive\" : true\n" +
                        " }"))
                //then
                .andExpect(status().isCreated())
                .andExpect(content().json(createdUserID));
    }
}