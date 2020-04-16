package com.gmail.roadtojob2019.servicemodule.services.impl;

import com.gmail.roadtojob2019.repositorymodule.models.Article;
import com.gmail.roadtojob2019.repositorymodule.repositories.ArticleRepository;
import com.gmail.roadtojob2019.servicemodule.services.ArticleService;
import com.gmail.roadtojob2019.servicemodule.services.converters.ArticleConverter;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ArticleDTO;
import com.gmail.roadtojob2019.servicemodule.services.exception.OnlineMarketSuchArticleNotFoundException;
import com.gmail.roadtojob2019.servicemodule.services.exception.OnlineMarketSuchUserNotFoundException;
import com.gmail.roadtojob2019.servicemodule.services.mappers.ArticleMapper;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleConverter articleConverter;
    private final ArticleMapper articleMapper;

    @Override
    @Transactional
    public Page<ArticleDTO> getPageOfArticlesSortedByDate(int pageNumber, int pageSize) {
        final Sort.Direction sortDirection = Sort.Direction.DESC;
        final String sortField = "date";
        final Pageable pageParameters = PageRequest.of(pageNumber - 1, pageSize, sortDirection, sortField);
        return articleRepository
                .findAll(pageParameters)
                .map(articleMapper::articleToArticleDto);
    }

    @Override
    @Transactional
    public ArticleDTO getArticleById(Long articleID) throws OnlineMarketSuchArticleNotFoundException {
        final Article article = articleRepository.findById(articleID)
                .orElseThrow(() -> new OnlineMarketSuchArticleNotFoundException("Article with id = " + articleID + " was not found"));
        return articleMapper.articleToArticleDto(article);
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
