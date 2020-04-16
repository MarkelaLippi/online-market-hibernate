package com.gmail.roadtojob2019.servicemodule.services.mappers;

import com.gmail.roadtojob2019.repositorymodule.models.Comment;
import com.gmail.roadtojob2019.servicemodule.services.dtos.CommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mappings({
            @Mapping(target = "userLastName", source = "user.lastName"),
            @Mapping(target = "userName", source = "user.name"),
    })
    CommentDTO commentToCommentDto(Comment comment);

    Comment commentDtoToComment(CommentDTO commentDTO);
}
