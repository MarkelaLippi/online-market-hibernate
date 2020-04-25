package com.gmail.roadtojob2019.controllermodule.controllers;

import com.gmail.roadtojob2019.repositorymodule.models.Article;
import com.gmail.roadtojob2019.repositorymodule.models.Comment;
import com.gmail.roadtojob2019.repositorymodule.models.User;
import com.gmail.roadtojob2019.repositorymodule.repositories.ArticleRepository;
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

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class ArticleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestService testService;

    @MockBean
    @Autowired
    private ArticleRepository articleRepository;

    @Test
    void testGetPageOfArticlesSortedByDateIsOk() throws Exception {
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
        final Page<Article> page = new PageImpl<>(articles, pageParameters, totalAmountOfArticles);
        willReturn(page).given(articleRepository).findAll(pageParameters);
        //when
        mockMvc.perform(get("/articles"))
                //then
                .andExpect(status().isOk());
        verify(articleRepository, times(1)).findAll(pageParameters);
    }

    @Test
    void testGetArticleByIdWithCommentsSortedByDateIsOk() throws Exception {
        //given
        final Long articleID = 1L;
        final User user = testService.getUser();
        final Article article = testService.getArticle(user);
        final Comment comment = testService.getComment(user);
        article.setComments(Set.of(comment));
        final Optional<Article> requiredArticle = Optional.of(article);
        willReturn(requiredArticle).given(articleRepository).findById(articleID);
        //when
        mockMvc.perform(get("/article")
                .param("articleID", articleID.toString()))
                //then
                .andExpect(status().isOk());
        verify(articleRepository, times(1)).findById(articleID);

    }

    @Test
    void testGetArticleByIdWithCommentsSortedByDateThrowsOnlineMarketSuchArticleNotFoundException() throws Exception {
        //given
        final Long articleID = 100L;
        willReturn(Optional.empty()).given(articleRepository).findById(articleID);
        //when
        mockMvc.perform(get("/article")
                .param("articleID", articleID.toString()))
                //then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errorMessage").value("Article with id = " + articleID + " was not found"));
        verify(articleRepository, times(1)).findById(articleID);
    }

    @Test
    void testDeleteArticleByIdIsOk() throws Exception {
        //given
        Long userID = 1L;
        willDoNothing().given(articleRepository).deleteById(userID);
        //when
        mockMvc.perform(get("/articles/delete/" + userID + ""))
                //then
                .andExpect(status().isOk());
        verify(articleRepository, times(1)).deleteById(userID);
    }

    @Test
    void testAddArticleIsCreated() throws Exception {
        //given
        final User user = testService.getUser();
        final Article article = testService.getArticle(user);
        willReturn(article).given(articleRepository).save(any(Article.class));
        //when
        mockMvc.perform(post("/articles/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("" +
                        "  {\n" +
                        "   \"title\" : \"Title of article\",\n" +
                        "   \"content\" : \"Content of article\",\n" +
                        "   \"description\" : \"Description of article\",\n" +
                        "   \"date\" : \"2020-04-18T17:30:15\",\n" +
                        "   \"userLastName\" : \"Rogov\",\n" +
                        "   \"userName\" : \"Petr\"\n" +
                        "  }\n"))
                //then
                .andExpect(status().isCreated());
        verify(articleRepository, times(1)).save(any(Article.class));
    }

    @Test
    void changeArticleIsOk() throws Exception {
        //given
        final User user = testService.getUser();
        final Article article = testService.getArticle(user);
        final Long articleID = article.getId();
        final Optional<Article> articleBeforeChanging = Optional.of(article);
        willReturn(articleBeforeChanging).given(articleRepository).findById(articleID);
        final Article changedArticle = testService.getChangedArticle(article);
        willReturn(changedArticle).given(articleRepository).save(any(Article.class));
        //when
        mockMvc.perform(post("/articles/change")
                .contentType(MediaType.APPLICATION_JSON)
                .content("" +
                        "  {\n" +
                        "   \"id\" : 1,\n" +
                        "   \"title\" : \"newTitle\",\n" +
                        "   \"content\" : \"newContent\",\n" +
                        "   \"description\" : \"Description of article\",\n" +
                        "   \"date\" : \"2020-04-25T14:21:00\",\n" +
                        "   \"userLastName\" : \"Rogov\",\n" +
                        "   \"userName\" : \"Petr\"\n" +
                        "  }\n"))
                .andExpect(status().isOk());
        verify(articleRepository, times(1)).findById(articleID);
        verify(articleRepository, times(1)).save(any(Article.class));
    }

    @Test
    void changeArticleThrowsOnlineMarketSuchArticleNotFoundException() throws Exception {
        //given
        final Long articleID = 100L;
        willReturn(Optional.empty()).given(articleRepository).findById(articleID);
        //when
        mockMvc.perform(post("/articles/change")
                .contentType(MediaType.APPLICATION_JSON)
                .content("" +
                        "  {\n" +
                        "   \"id\" : 100,\n" +
                        "   \"title\" : \"newTitle\",\n" +
                        "   \"content\" : \"newContent\",\n" +
                        "   \"description\" : \"Description of article\",\n" +
                        "   \"date\" : \"2020-04-25T14:21:00\",\n" +
                        "   \"userLastName\" : \"Rogov\",\n" +
                        "   \"userName\" : \"Petr\"\n" +
                        "  }\n"))
                //then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errorMessage").value("Article with id = " + articleID + " was not found"));
        verify(articleRepository, times(1)).findById(articleID);
    }
}