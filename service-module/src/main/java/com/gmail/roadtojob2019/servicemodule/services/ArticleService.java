package com.gmail.roadtojob2019.servicemodule.services;

import com.gmail.roadtojob2019.servicemodule.services.dtos.ArticleDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ArticleService {
    Page<ArticleDTO> getPageOfArticlesSortedByDate(int pageNumber, int pageSize);

    ArticleDTO findArticleById(Long id);

    List<ArticleDTO> findAllArticles();

    void addArticle(ArticleDTO articleDTO);

    void deleteArticle(Long id);
}
