package com.gmail.roadtojob2019.controllermodule.controllers;

import com.gmail.roadtojob2019.servicemodule.services.ReviewService;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    String getAllReviewsPaginated(@RequestParam Optional<Integer> page,
                                  @RequestParam Optional<Integer> size,
                                  Model model) {
        Integer currentPage = page.orElse(1);
        Integer sizePage = size.orElse(10);
        Page<ReviewDTO> reviews = reviewService.findAllReviewsPaginated(currentPage, sizePage);
        model.addAttribute("reviews", reviews);
        return "reviewsPage";
    }

    @PostMapping("/reviews/delete")
    String deleteCheckedReviews(@RequestParam(required = false, name = "ids") int[] ids) {
        if (ids == null) {
            return "redirect:/reviews";
        }
        reviewService.deleteCheckedReviews(ids);
        return "redirect:/reviews";
    }
}