package com.gmail.roadtojob2019.controllermodule.controllers.restcontrollers;

import com.gmail.roadtojob2019.servicemodule.services.ArticleService;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ArticleDTO;
import com.gmail.roadtojob2019.servicemodule.services.exception.OnlineMarketSuchArticleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/articles")
public class RestApiArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping
    List<ArticleDTO> getAllArticles() {
        return articleService.findAllArticles();
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
