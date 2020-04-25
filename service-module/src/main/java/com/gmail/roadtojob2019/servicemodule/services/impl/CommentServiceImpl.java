package com.gmail.roadtojob2019.servicemodule.services.impl;

import com.gmail.roadtojob2019.repositorymodule.repositories.CommentRepository;
import com.gmail.roadtojob2019.servicemodule.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public void deleteCommentById(final Long commentID) {
        commentRepository.deleteById(commentID);
    }
}
