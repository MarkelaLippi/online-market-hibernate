package com.gmail.roadtojob2019.servicemodule.services.converters.impl;

import com.gmail.roadtojob2019.repositorymodule.models.Article;
import com.gmail.roadtojob2019.repositorymodule.models.User;
import com.gmail.roadtojob2019.servicemodule.services.converters.ArticleConverter;
import com.gmail.roadtojob2019.servicemodule.services.converters.UserConverter;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ArticleDTO;
import com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleConverterImpl implements ArticleConverter {
    @Autowired
    private UserConverter userConverter;

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
