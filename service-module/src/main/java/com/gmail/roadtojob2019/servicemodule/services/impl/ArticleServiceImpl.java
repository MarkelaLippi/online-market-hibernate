package com.gmail.roadtojob2019.servicemodule.services.impl;

import com.gmail.roadtojob2019.repositorymodule.models.Article;
import com.gmail.roadtojob2019.repositorymodule.repositories.ArticleRepository;
import com.gmail.roadtojob2019.servicemodule.services.ArticleService;
import com.gmail.roadtojob2019.servicemodule.services.converters.ArticleConverter;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleConverter articleConverter;

    @Override
    @Transactional
    public Page<ArticleDTO> findAllArticlesPaginatedAndSortedByDate(int pageNumber, int pageSize) {
        final String fieldSorting = "date";
        Pageable userPageParameters = PageRequest.of(pageNumber - 1, pageSize, Sort.Direction.DESC, fieldSorting);
        return articleRepository
                .findAll(userPageParameters)
                .map(articleConverter::articleToDTO);
    }

    @Override
    public ArticleDTO findArticleById(Long id) {
        return articleConverter.articleToDTO(articleRepository.findById(id).orElseThrow());
    }
}
