package com.gmail.roadtojob2019.controllermodule.controllers.restcontrollers;

import com.gmail.roadtojob2019.repositorymodule.models.Article;
import com.gmail.roadtojob2019.repositorymodule.models.User;
import com.gmail.roadtojob2019.repositorymodule.repositories.ArticleRepository;
import com.gmail.roadtojob2019.servicemodule.services.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class RestApiArticleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestService testService;

    @Autowired
    @MockBean
    private ArticleRepository articleRepository;

    @Test
    void testGetAllArticlesIsOk() throws Exception {
        //given
        final User user = testService.getUser();
        final Article article = testService.getArticle(user);
        final List<Article> articles = List.of(article);
        willReturn(articles).given(articleRepository).findAll();
        //when
        mockMvc.perform(get("/api/articles"))
                //then
                .andExpect(status().isOk())
                .andExpect(content().json("" +
                        "[\n" +
                        "  {\n" +
                        "   \"id\" : 1, \n" +
                        "   \"title\" : \"Title of article\",\n" +
                        "   \"content\" : \"Content of article\",\n" +
                        "   \"description\" : \"Description of article\",\n" +
                        "   \"date\" : \"2020-04-18T17:30:15\",\n" +
                        "   \"userLastName\" : \"Rogov\",\n" +
                        "   \"userName\" : \"Petr\",\n" +
                        "   \"comments\" : []\n" +
                        "  }\n" +
                        "]"));
        verify(articleRepository, times(1)).findAll();
    }

    @Test
    void testGetArticleByIdIsOk() throws Exception {
        //given
        final User user = testService.getUser();
        final Article article = testService.getArticle(user);
        final Optional<Article> requiredArticle = Optional.of(article);
        willReturn(requiredArticle).given(articleRepository).findById(article.getId());
        //when
        mockMvc.perform(get("/api/articles/1"))
                //then
                .andExpect(status().isOk())
                .andExpect(content().json("" +
                        "  {\n" +
                        "   \"id\" : 1, \n" +
                        "   \"title\" : \"Title of article\",\n" +
                        "   \"content\" : \"Content of article\",\n" +
                        "   \"description\" : \"Description of article\",\n" +
                        "   \"date\" : \"2020-04-18T17:30:15\",\n" +
                        "   \"userLastName\" : \"Rogov\",\n" +
                        "   \"userName\" : \"Petr\",\n" +
                        "   \"comments\" : []\n" +
                        "  }\n"));
        verify(articleRepository, times(1)).findById(anyLong());
    }

    @Test
    void testGetArticleByIdThrowsOnlineMarketSuchArticleNotFoundException() throws Exception {
        //given
        willReturn(Optional.empty()).given(articleRepository).findById(anyLong());
        //when
        mockMvc.perform(get("/api/articles/1"))
                //then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errorMessage").value("Article with id = 1 was not found"));
        verify(articleRepository, times(1)).findById(anyLong());
    }

    @Test
    void testAddArticleIsCreated() throws Exception {
        //given
        final User user = testService.getUser();
        final Article article = testService.getArticle(user);
        final String articleId = article.getId().toString();
        willReturn(article).given(articleRepository).save(any(Article.class));
        //when
        mockMvc.perform(post("/api/articles")
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
                .andExpect(status().isCreated())
                .andExpect(content().json(articleId));
        verify(articleRepository, times(1)).save(any(Article.class));
    }

    @Test
    void testDeleteArticleByIdIsOk() throws Exception {
        //given
        final Long articleId = 1L;
        willDoNothing().given(articleRepository).deleteById(articleId);
        //when
        mockMvc.perform(delete("/api/articles/1"))
                //then
                .andExpect(status().isOk());
        verify(articleRepository, times(1)).deleteById(anyLong());
    }
}
