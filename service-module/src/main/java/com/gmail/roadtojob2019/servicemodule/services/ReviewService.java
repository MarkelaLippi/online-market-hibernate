package com.gmail.roadtojob2019.servicemodule.services;

import com.gmail.roadtojob2019.servicemodule.services.dtos.ReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {
    Page<ReviewDTO> getPageOfReviews(int pageNumber, int pageSize);

    void deleteCheckedReviews(int[] ids);
}
