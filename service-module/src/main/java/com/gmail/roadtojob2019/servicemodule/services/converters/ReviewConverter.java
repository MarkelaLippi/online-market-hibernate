package com.gmail.roadtojob2019.servicemodule.services.converters;

import com.gmail.roadtojob2019.repositorymodule.models.Review;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ReviewDTO;

public interface ReviewConverter {
    ReviewDTO reviewToDTO(Review review);

    Review dtoToReview(ReviewDTO reviewDTO);
}
