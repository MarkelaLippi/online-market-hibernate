package com.gmail.roadtojob2019.controllermodule.controllers;

import com.gmail.roadtojob2019.repositorymodule.models.Review;
import com.gmail.roadtojob2019.repositorymodule.models.Role;
import com.gmail.roadtojob2019.repositorymodule.models.User;
import com.gmail.roadtojob2019.repositorymodule.repositories.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ReviewRepository reviewRepository;

    @Test
    void testGetPageOfReviews() throws Exception {
        //given
        final User user = getUser();
        final Review review = getReview(user);
        final List<Review> reviews = List.of(review);
        final PageRequest pageParameters = PageRequest.of(1, 10);
        final Page<Review> pageOfReviews = new PageImpl<>(reviews, pageParameters, 1L);
        willReturn(pageOfReviews).given(reviewRepository).findAll(pageParameters);
        //when
        mockMvc.perform(get("/reviews"))
                //then
                .andExpect(status().isOk());
    }

    private Review getReview(User user) {
        return Review.builder()
                .id(1L)
                .content("I would like to notice...")
                .date(new Date())
                .isActive(true)
                .user(user)
                .build();
    }

    private User getUser() {
        return User.builder()
                .id(1L)
                .lastName("Rogov")
                .name("Petr")
                .middleName("Petrovich")
                .email("Rogov@gmail.com")
                .password("1234")
                .role(Role.ADMINISTRATOR)
                .isActive(true)
                .reviews(Collections.emptySet())
                .articles(Collections.emptySet())
                .comments(Collections.emptySet())
                .build();
    }
}