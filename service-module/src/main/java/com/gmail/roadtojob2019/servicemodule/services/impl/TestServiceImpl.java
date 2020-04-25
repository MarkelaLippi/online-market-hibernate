package com.gmail.roadtojob2019.servicemodule.services.impl;

import com.gmail.roadtojob2019.repositorymodule.models.*;
import com.gmail.roadtojob2019.servicemodule.services.TestService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public User getUser() {
        return User.builder()
                .id(1L)
                .lastName("Rogov")
                .name("Petr")
                .middleName("Petrovich")
                .address("Orsha")
                .phone("+375296666666")
                .email("Rogov@gmail.com")
                .password("1234")
                .role(Role.ADMINISTRATOR)
                .isActive(true)
                .reviews(Collections.emptySet())
                .articles(Collections.emptySet())
                .comments(Collections.emptySet())
                .build();
    }

    @Override
    public Review getReview(User user) {
        return Review.builder()
                .id(1L)
                .content("I would like to notice...")
                .date(LocalDateTime.of(2020, 4, 10, 15, 43, 25))
                .isActive(true)
                .user(user)
                .build();
    }

    @Override
    public Article getArticle(User user) {
        return Article.builder()
                .id(1L)
                .title("Title of article")
                .content("Content of article")
                .description("Description of article")
                .date(LocalDateTime.of(2020, 4, 18, 17, 30, 15))
                .user(user)
                .comments(Collections.emptySet())
                .build();
    }

    @Override
    public Comment getComment(User user) {
        return Comment.builder()
                .id(1L)
                .date(LocalDateTime.now())
                .content("Content of comment")
                .user(user)
                .build();
    }

    @Override
    public User getChangedUser(User user) {
        user.setName("NewName");
        user.setLastName("NewLastName");
        user.setAddress("NewAddress");
        user.setPhone("NewPhone");
        user.setPassword("NewPassword");
        return user;
    }

    @Override
    public Article getChangedArticle(Article article) {
        article.setTitle("NewTitle");
        article.setContent("NewContent");
        article.setDate(LocalDateTime.now());
        return article;
    }
}
