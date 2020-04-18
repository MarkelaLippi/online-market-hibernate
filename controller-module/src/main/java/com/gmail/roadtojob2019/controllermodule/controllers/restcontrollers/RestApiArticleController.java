package com.gmail.roadtojob2019.controllermodule.controllers.restcontrollers;

import com.gmail.roadtojob2019.servicemodule.services.ArticleService;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ArticleDTO;
import com.gmail.roadtojob2019.servicemodule.services.exception.OnlineMarketSuchArticleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/articles")
@RequiredArgsConstructor
public class RestApiArticleController {

    private final ArticleService articleService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<ArticleDTO> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/{id}")
    ArticleDTO getArticle(@PathVariable Long id) throws OnlineMarketSuchArticleNotFoundException {
        return articleService.getArticleByIdWithCommentsSortedByDate(id);
    }

    @PostMapping
    void addArticle(@RequestBody ArticleDTO articleDTO) {
        articleService.addArticle(articleDTO);
    }

    @DeleteMapping("{id}")
    void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
    }
}
