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

    @GetMapping("/{articleID}")
    @ResponseStatus(HttpStatus.OK)
    ArticleDTO getArticleById(@PathVariable Long articleID) throws OnlineMarketSuchArticleNotFoundException {
        return articleService.getArticleByIdWithCommentsSortedByDate(articleID);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Long addArticle(@RequestBody ArticleDTO articleDTO) {
        return articleService.addArticle(articleDTO);
    }

    @DeleteMapping("{articleID}")
    @ResponseStatus(HttpStatus.OK)
    void deleteArticleById(@PathVariable Long articleID) {
        articleService.deleteArticleById(articleID);
    }
}
