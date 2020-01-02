package com.gmail.roadtojob2019.servicemodule.services.converters;

import com.gmail.roadtojob2019.repositorymodule.models.Article;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ArticleDTO;

public interface ArticleConverter {
    ArticleDTO articleToDTO(Article article);

    Article dtoToArticle(ArticleDTO articleDTO);
}
