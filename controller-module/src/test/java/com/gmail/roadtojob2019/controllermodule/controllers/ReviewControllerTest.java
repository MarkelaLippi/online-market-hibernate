package com.gmail.roadtojob2019.controllermodule.controllers;

import com.gmail.roadtojob2019.repositorymodule.models.Review;
import com.gmail.roadtojob2019.repositorymodule.models.User;
import com.gmail.roadtojob2019.repositorymodule.repositories.ReviewRepository;
import com.gmail.roadtojob2019.servicemodule.services.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestService testService;

    @MockBean
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void testGetPageOfReviewsIsOk() throws Exception {
        //given
        final User user = testService.getUser();
        final Review review = testService.getReview(user);
        final List<Review> reviews = List.of(review);
        final int pageNumber = 1;
        final int pageSize = 10;
        final PageRequest pageParameters = PageRequest.of(pageNumber, pageSize);
        final long totalAmountOfReviews = 1L;
        final Page<Review> pageOfReviews = new PageImpl<>(reviews, pageParameters, totalAmountOfReviews);
        willReturn(pageOfReviews).given(reviewRepository).findAll(pageParameters);
        //when
        mockMvc.perform(get("/reviews"))
                //then
                .andExpect(status().isOk());
        verify(reviewRepository, times(1)).findAll(pageParameters);
    }

    @Test
    void testDeleteCheckedReviewsIsOk() throws Exception {
        //given
        final List<Long> reviewsIds = List.of(1L, 2L);
        willDoNothing().given(reviewRepository).deleteReviewsByIdIn(reviewsIds);
        //when
        mockMvc.perform(post("/reviews/delete")
                .param("reviewsIDs", "1", "2"))
                //then
                .andExpect(status().isOk());
        verify(reviewRepository, times(1)).deleteReviewsByIdIn(reviewsIds);
    }
}