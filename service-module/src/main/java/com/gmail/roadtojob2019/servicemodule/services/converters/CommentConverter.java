package com.gmail.roadtojob2019.servicemodule.services.converters;

import com.gmail.roadtojob2019.repositorymodule.models.Comment;
import com.gmail.roadtojob2019.servicemodule.services.dtos.CommentDTO;

public interface CommentConverter {
    CommentDTO commentToDTO(Comment comment);

    Comment dtoToComment(CommentDTO commentDTO);
}
