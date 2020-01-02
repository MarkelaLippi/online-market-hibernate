package com.gmail.roadtojob2019.servicemodule.services.converters.impl;

import com.gmail.roadtojob2019.repositorymodule.models.Review;
import com.gmail.roadtojob2019.repositorymodule.models.User;
import com.gmail.roadtojob2019.servicemodule.services.converters.ReviewConverter;
import com.gmail.roadtojob2019.servicemodule.services.converters.UserConverter;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewConverterImpl implements ReviewConverter {
    @Autowired
    UserConverter userConverter;

    @Override
    public ReviewDTO reviewToDTO(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setContent(review.getContent());
        reviewDTO.setDate(review.getDate());
        reviewDTO.setActive(review.isActive());
        User user = review.getUser();
        com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO userDTO = new com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLastName(user.getLastName());
        userDTO.setName(user.getName());
        userDTO.setMiddleName(user.getMiddleName());
        reviewDTO.setUserDTO(userDTO);
        return reviewDTO;
    }

    @Override
    public Review dtoToReview(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setContent(reviewDTO.getContent());
        review.setDate(reviewDTO.getDate());
        review.setActive(reviewDTO.isActive());
        review.setUser(userConverter.dtoToUser(reviewDTO.getUserDTO()));
        return review;
    }
}
