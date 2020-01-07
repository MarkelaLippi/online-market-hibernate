package com.gmail.roadtojob2019.controllermodule.controllers;

import com.gmail.roadtojob2019.servicemodule.services.ArticleService;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/articles")
    String getAllArticlesPaginatedAndSortedByDate(@RequestParam Optional<Integer> page,
                                                  @RequestParam Optional<Integer> size,
                                                  Model model) {
        Integer currentPage = page.orElse(1);
        Integer sizePage = size.orElse(10);
        Page<ArticleDTO> articleDTOs = articleService.findAllArticlesPaginatedAndSortedByDate(currentPage, sizePage);
        model.addAttribute("articles", articleDTOs);
        return "articlesPage";
    }

    @GetMapping("/article")
    String getArticle(@RequestParam Long id, Model model) {
        ArticleDTO articleDTO = articleService.findArticleById(id);
        model.addAttribute("article", articleDTO);
        return "articlePage";
    }

}
