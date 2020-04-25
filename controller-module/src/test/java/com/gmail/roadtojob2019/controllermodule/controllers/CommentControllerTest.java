package com.gmail.roadtojob2019.controllermodule.controllers;

import com.gmail.roadtojob2019.repositorymodule.repositories.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
public class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @MockBean
    private CommentRepository commentRepository;

    @Test
    void testDeleteCommentByIdIsOk() throws Exception {
        //given
        final Long commentID = 1L;
        willDoNothing().given(commentRepository).deleteById(commentID);
        //when
        mockMvc.perform(get("/comments/delete")
                .param("commentID", commentID.toString()))
                //then
                .andExpect(status().isOk());
        verify(commentRepository).deleteById(commentID);
    }
}
