package com.gmail.roadtojob2019.servicemodule.services.converters.impl;

import com.gmail.roadtojob2019.repositorymodule.models.Review;
import com.gmail.roadtojob2019.servicemodule.services.converters.ReviewConverter;
import com.gmail.roadtojob2019.servicemodule.services.converters.UserConverter;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class ReviewConverterImpl implements ReviewConverter {
    @Autowired
    UserConverter userConverter;

    @Override
    public ReviewDTO reviewToDTO(Review review) {
        ReviewDTO reviewDTO=new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setContent(review.getContent());
        reviewDTO.setDate(review.getDate());
        reviewDTO.setActive(review.isActive());
        reviewDTO.setUserDTO(userConverter.userToDTO(review.getUser()));
        return reviewDTO;
    }

    @Override
    public Review dtoToReview(ReviewDTO reviewDTO) {
        Review review=new Review();
        review.setId(reviewDTO.getId());
        review.setContent(reviewDTO.getContent());
        review.setDate(reviewDTO.getDate());
        review.setActive(reviewDTO.isActive());
        review.setUser(userConverter.dtoToUser(reviewDTO.getUserDTO()));
        return review;
    }
}
