package com.gmail.roadtojob2019.servicemodule.services.mappers;

import com.gmail.roadtojob2019.repositorymodule.models.Article;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ArticleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    @Mappings({
            @Mapping(target = "userLastName", source = "user.lastName"),
            @Mapping(target = "userName", source = "user.name"),
    })
    ArticleDTO articleToArticleDto(Article article);

    Article articleDtoToArticle(ArticleDTO articleDTO);
}
