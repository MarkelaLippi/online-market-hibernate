package com.gmail.roadtojob2019.servicemodule.services;

import com.gmail.roadtojob2019.servicemodule.services.dtos.ArticleDTO;
import org.springframework.data.domain.Page;

public interface ArticleService {
    Page<ArticleDTO> findAllArticlesPaginatedAndSortedByDate(int pageNumber, int pageSize);
    ArticleDTO findArticleById(Long id);
}
