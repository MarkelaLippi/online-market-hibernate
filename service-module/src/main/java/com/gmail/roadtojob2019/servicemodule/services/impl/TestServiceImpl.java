package com.gmail.roadtojob2019.servicemodule.services.impl;

import com.gmail.roadtojob2019.repositorymodule.models.Review;
import com.gmail.roadtojob2019.repositorymodule.models.Role;
import com.gmail.roadtojob2019.repositorymodule.models.User;
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
                .date(LocalDateTime.of(2020,4,10, 15, 43, 25))
                .isActive(true)
                .user(user)
                .build();
    }
}
