package com.gmail.roadtojob2019.repositorymodule.repositories;

import com.gmail.roadtojob2019.repositorymodule.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
