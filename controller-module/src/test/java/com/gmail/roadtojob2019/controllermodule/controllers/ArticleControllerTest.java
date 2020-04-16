package com.gmail.roadtojob2019.controllermodule.controllers;

import com.gmail.roadtojob2019.repositorymodule.models.Article;
import com.gmail.roadtojob2019.repositorymodule.models.User;
import com.gmail.roadtojob2019.repositorymodule.repositories.ArticleRepository;
import com.gmail.roadtojob2019.servicemodule.services.TestService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class ArticleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestService testService;

    @Mock
    private ArticleRepository articleRepository;

    @Test
    void getPageOfArticlesSortedByDate() throws Exception {
        //given
        final int pageNumber = 1;
        final int pageSize = 10;
        final String sortField = "date";
        final Sort.Direction sortDirection = Sort.Direction.DESC;
        final Pageable pageParameters = PageRequest.of(pageNumber, pageSize, sortDirection, sortField);
        final User user = testService.getUser();
        final Article article = testService.getArticle(user);
        final List<Article> articles = List.of(article);
        final int totalAmountOfArticles = articles.size();
        Page<Article> page = new PageImpl<>(articles, pageParameters, totalAmountOfArticles);
        willReturn(page).given(articleRepository).findAll(pageParameters);
        //when
        mockMvc.perform(get("/articles"))
                //then
                .andExpect(status().isOk());
    }

    @Test
    void getArticle() {
    }
}