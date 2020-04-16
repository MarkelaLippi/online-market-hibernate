package com.gmail.roadtojob2019.controllermodule.controllers;

import com.gmail.roadtojob2019.servicemodule.services.ArticleService;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ArticleDTO;
import com.gmail.roadtojob2019.servicemodule.services.exception.OnlineMarketSuchArticleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/articles")
    String getPageOfArticlesSortedByDate(@RequestParam Optional<Integer> pageNumber,
                                         @RequestParam Optional<Integer> pageSize,
                                         Model model) {
        Integer currentPageNumber = pageNumber.orElse(1);
        Integer currentPageSize = pageSize.orElse(10);
        Page<ArticleDTO> articles = articleService.getPageOfArticlesSortedByDate(currentPageNumber, currentPageSize);
        model.addAttribute("articles", articles);
        return "articles";
    }

    @GetMapping("/article")
    String getArticle(@RequestParam Long articleID, Model model) throws OnlineMarketSuchArticleNotFoundException {
        ArticleDTO article = articleService.getArticleById(articleID);
        model.addAttribute("article", article);
        return "article";
    }
}
