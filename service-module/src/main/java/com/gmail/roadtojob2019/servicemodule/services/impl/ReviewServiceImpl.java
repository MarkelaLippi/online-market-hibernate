package com.gmail.roadtojob2019.servicemodule.services.impl;

import com.gmail.roadtojob2019.repositorymodule.ReviewRepository;
import com.gmail.roadtojob2019.repositorymodule.models.Review;
import com.gmail.roadtojob2019.servicemodule.services.ReviewService;
import com.gmail.roadtojob2019.servicemodule.services.converters.ReviewConverter;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewConverter reviewConverter;

    @Override
    @Transactional
    public Page<ReviewDTO> findAllReviewsPaginated(int pageNumber, int pageSize) {
        return reviewRepository
                .findAll(PageRequest.of(pageNumber - 1, pageSize))
                .map(reviewConverter::reviewToDTO);
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
