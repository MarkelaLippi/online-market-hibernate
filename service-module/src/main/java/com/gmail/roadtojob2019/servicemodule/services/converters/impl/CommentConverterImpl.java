package com.gmail.roadtojob2019.servicemodule.services.converters.impl;

import com.gmail.roadtojob2019.repositorymodule.models.Comment;
import com.gmail.roadtojob2019.repositorymodule.models.User;
import com.gmail.roadtojob2019.servicemodule.services.converters.ArticleConverter;
import com.gmail.roadtojob2019.servicemodule.services.converters.CommentConverter;
import com.gmail.roadtojob2019.servicemodule.services.converters.UserConverter;
import com.gmail.roadtojob2019.servicemodule.services.dtos.CommentDTO;
import com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentConverterImpl implements CommentConverter {
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private ArticleConverter articleConverter;

    @Override
    public CommentDTO commentToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setDate(comment.getDate());
        commentDTO.setContent(comment.getContent());
        User user = comment.getUser();
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setLastName(user.getLastName());
//        commentDTO.setUserDTO(userDTO);
        return commentDTO;
    }

    @Override
    public Comment dtoToComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setDate(commentDTO.getDate());
        comment.setContent(commentDTO.getContent());
/*
        comment.setUser(userConverter.dtoToUser(commentDTO.getUserDTO()));
        comment.setArticle(articleConverter.dtoToArticle(commentDTO.getArticleDTO()));
*/
        return comment;
    }
}
