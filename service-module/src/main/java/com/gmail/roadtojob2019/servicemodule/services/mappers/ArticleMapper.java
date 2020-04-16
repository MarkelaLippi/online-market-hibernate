package com.gmail.roadtojob2019.servicemodule.services.mappers;

import com.gmail.roadtojob2019.repositorymodule.models.Article;
import com.gmail.roadtojob2019.repositorymodule.models.Comment;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ArticleDTO;
import com.gmail.roadtojob2019.servicemodule.services.dtos.CommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    @Mappings({
            @Mapping(target = "userLastName", source = "user.lastName"),
            @Mapping(target = "userName", source = "user.name"),
    })
    ArticleDTO articleToArticleDto(Article article);

    Set<CommentDTO> convertCommentSetToCommentDtoSet(Set<Comment> comments);

    @Mappings({
            @Mapping(target = "userLastName", source = "user.lastName"),
            @Mapping(target = "userName", source = "user.name"),
    })
    CommentDTO commentToCommentDto(Comment comment);

    Article articleDtoToArticle(ArticleDTO articleDTO);
}
