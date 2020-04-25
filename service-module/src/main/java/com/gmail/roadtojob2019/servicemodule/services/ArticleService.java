package com.gmail.roadtojob2019.servicemodule.services;

import com.gmail.roadtojob2019.servicemodule.services.dtos.ArticleDTO;
import com.gmail.roadtojob2019.servicemodule.services.exception.OnlineMarketSuchArticleNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ArticleService {
    Page<ArticleDTO> getPageOfArticlesSortedByDate(int pageNumber, int pageSize);

    ArticleDTO getArticleByIdWithCommentsSortedByDate(Long articleID) throws OnlineMarketSuchArticleNotFoundException;

    List<ArticleDTO> getAllArticles();

    Long addArticle(ArticleDTO articleDTO);

    void deleteArticleById(Long articleID);
}
