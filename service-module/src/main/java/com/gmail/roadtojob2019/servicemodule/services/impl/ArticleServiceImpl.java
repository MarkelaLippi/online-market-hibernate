package com.gmail.roadtojob2019.servicemodule.services.impl;

import com.gmail.roadtojob2019.repositorymodule.models.Article;
import com.gmail.roadtojob2019.repositorymodule.repositories.ArticleRepository;
import com.gmail.roadtojob2019.servicemodule.services.ArticleService;
import com.gmail.roadtojob2019.servicemodule.services.converters.ArticleConverter;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ArticleDTO;
import com.gmail.roadtojob2019.servicemodule.services.dtos.CommentDTO;
import com.gmail.roadtojob2019.servicemodule.services.exception.OnlineMarketSuchArticleNotFoundException;
import com.gmail.roadtojob2019.servicemodule.services.mappers.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

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
    public ArticleDTO getArticleByIdWithCommentsSortedByDate(Long articleID) throws OnlineMarketSuchArticleNotFoundException {
        final Article article = articleRepository.findById(articleID)
                .orElseThrow(() -> new OnlineMarketSuchArticleNotFoundException("Article with id = " + articleID + " was not found"));
        final ArticleDTO articleDTO = articleMapper.articleToArticleDto(article);
        final Set<CommentDTO> nonSortedByDateComments = articleDTO.getComments();
        final Set<CommentDTO> sortedByDateComments = sortCommentsByDate(nonSortedByDateComments);
        articleDTO.setComments(sortedByDateComments);
        return articleDTO;
    }

    private Set<CommentDTO> sortCommentsByDate(Set<CommentDTO> nonSortedByDateComments) {
        return nonSortedByDateComments
                .stream()
                .sorted(comparing(CommentDTO::getDate).reversed())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    @Transactional
    public List<ArticleDTO> getAllArticles() {
        final List<Article> articles = articleRepository.findAll();
        return toArticleDTOs(articles);
    }

    private List<ArticleDTO> toArticleDTOs(List<Article> articles) {
        return articles
                .stream()
                .map(articleMapper::articleToArticleDto)
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
