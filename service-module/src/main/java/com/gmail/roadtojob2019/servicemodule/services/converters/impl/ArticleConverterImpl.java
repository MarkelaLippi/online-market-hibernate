package com.gmail.roadtojob2019.servicemodule.services.converters.impl;

import com.gmail.roadtojob2019.repositorymodule.models.Article;
import com.gmail.roadtojob2019.repositorymodule.models.Comment;
import com.gmail.roadtojob2019.repositorymodule.models.User;
import com.gmail.roadtojob2019.servicemodule.services.converters.ArticleConverter;
import com.gmail.roadtojob2019.servicemodule.services.converters.CommentConverter;
import com.gmail.roadtojob2019.servicemodule.services.converters.UserConverter;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ArticleDTO;
import com.gmail.roadtojob2019.servicemodule.services.dtos.CommentDTO;
import com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ArticleConverterImpl implements ArticleConverter {
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private CommentConverter commentConverter;

    @Override
    public ArticleDTO articleToDTO(Article article) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setContent(article.getContent());
        articleDTO.setDescription(article.getDescription());
        articleDTO.setDate(article.getDate());
        User user = article.getUser();
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setLastName(user.getLastName());
        articleDTO.setUserDTO(userDTO);
        Set<CommentDTO> commentDTOs = article.getComments()
                .stream()
                .map(commentConverter::commentToDTO)
                .sorted(Comparator.comparing(CommentDTO::getDate).reversed())
                .collect(Collectors.toCollection(LinkedHashSet::new));
        articleDTO.setCommentDTOs(commentDTOs);
        return articleDTO;
    }

    @Override
    public Article dtoToArticle(ArticleDTO articleDTO) {
        Article article = new Article();
        article.setId(articleDTO.getId());
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        article.setDescription(articleDTO.getDescription());
        article.setDate(articleDTO.getDate());
        article.setUser(userConverter.dtoToUser(articleDTO.getUserDTO()));
        return article;
    }
}
