package com.gmail.roadtojob2019.servicemodule.services.impl;

import com.gmail.roadtojob2019.repositorymodule.models.Review;
import com.gmail.roadtojob2019.repositorymodule.repositories.ReviewRepository;
import com.gmail.roadtojob2019.servicemodule.services.ReviewService;
import com.gmail.roadtojob2019.servicemodule.services.converters.ReviewConverter;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ReviewDTO;
import com.gmail.roadtojob2019.servicemodule.services.mappers.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewConverter reviewConverter;
    private final ReviewMapper reviewMapper;

    @Override
    @Transactional
    public Page<ReviewDTO> getPageOfReviews(int pageNumber, int pageSize) {
        Pageable pageParameters = PageRequest.of(pageNumber - 1, pageSize);
        return reviewRepository
                .findAll(pageParameters)
                .map(reviewMapper::reviewToReviewDto);
    }

    @Override
    @Transactional
    public void deleteCheckedReviews(int[] ids) {
        List<Long> idsAsLong = Arrays.stream(ids)
                .asLongStream()
                .boxed()
                .collect(Collectors.toList());

        List<Review> checkedReviews = reviewRepository.findAllById(idsAsLong);
        reviewRepository.deleteAll(checkedReviews);
    }
}
