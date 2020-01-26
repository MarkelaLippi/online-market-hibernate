package com.gmail.roadtojob2019.servicemodule.services.impl;

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

import java.util.List;
import java.util.stream.Collectors;

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
    @Transactional
    public ArticleDTO findArticleById(Long id) {
        return articleConverter.articleToDTO(articleRepository.findById(id).orElseThrow());
    }

    @Override
    @Transactional
    public List<ArticleDTO> findAllArticles() {
        return articleRepository
                .findAll()
                .stream()
                .map(articleConverter::articleToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addArticle(ArticleDTO articleDTO) {
        articleRepository.save(articleConverter.dtoToArticle(articleDTO));
    }

    @Override
    @Transactional
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
}
