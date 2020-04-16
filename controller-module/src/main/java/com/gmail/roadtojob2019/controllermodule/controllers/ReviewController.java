package com.gmail.roadtojob2019.controllermodule.controllers;

import com.gmail.roadtojob2019.servicemodule.services.ReviewService;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/reviews")
    String getPageOfReviews(@RequestParam Optional<Integer> pageNumber,
                            @RequestParam Optional<Integer> pageSize,
                                  Model model) {
        Integer currentPageNumber = pageNumber.orElse(1);
        Integer currentPageSize = pageSize.orElse(10);
        Page<ReviewDTO> reviews = reviewService.getPageOfReviews(currentPageNumber, currentPageSize);
        model.addAttribute("reviews", reviews);
        return "reviews";
    }

    @PostMapping("/reviews/delete")
    String deleteCheckedReviews(@RequestParam int[] reviewsIDs) {
        if (reviewsIDs != null) {
            reviewService.deleteCheckedReviews(reviewsIDs);
        }
        return "forward:/reviews";
    }
}