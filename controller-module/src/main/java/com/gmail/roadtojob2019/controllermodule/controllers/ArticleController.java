package com.gmail.roadtojob2019.controllermodule.controllers;

import com.gmail.roadtojob2019.servicemodule.services.ArticleService;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ArticleDTO;
import com.gmail.roadtojob2019.servicemodule.services.exception.OnlineMarketSuchArticleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static java.util.Comparator.comparing;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/articles")
    @ResponseStatus(HttpStatus.OK)
    String getPageOfArticlesSortedByDate(@RequestParam final Optional<Integer> pageNumber,
                                         @RequestParam final Optional<Integer> pageSize,
                                         Model model) {
        final Integer currentPageNumber = pageNumber.orElse(1);
        final Integer currentPageSize = pageSize.orElse(10);
        final Page<ArticleDTO> articles = articleService.getPageOfArticlesSortedByDate(currentPageNumber, currentPageSize);
        model.addAttribute("articles", articles);
        return "articles";
    }

    @GetMapping("/article")
    @ResponseStatus(HttpStatus.OK)
    String getArticleByIdWithCommentsSortedByDate(@RequestParam final Long articleID, final Model model) throws OnlineMarketSuchArticleNotFoundException {
        final ArticleDTO article = articleService.getArticleByIdWithCommentsSortedByDate(articleID);
        model.addAttribute("article", article);
        return "article";
    }

    @GetMapping("/articles/delete/{userID}")
    @ResponseStatus(HttpStatus.OK)
    String deleteArticle(@PathVariable final Long userID) {
        articleService.deleteArticleById(userID);
        return "forward: /articles";
    }

    @PostMapping("/articles/add")
    @ResponseStatus(HttpStatus.CREATED)
    String addArticle(@RequestBody final ArticleDTO articleDTO){
        articleService.addArticle(articleDTO);
        return "forward: /articles";
    }

    @PostMapping("/articles/change")
    @ResponseStatus(HttpStatus.OK)
    String changeArticle(@RequestBody final ArticleDTO articleDTO) throws OnlineMarketSuchArticleNotFoundException {
        articleService.changeArticle(articleDTO);
        return "forward: /articles";
    }
}
