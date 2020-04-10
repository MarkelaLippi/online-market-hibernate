package com.gmail.roadtojob2019.servicemodule.services.mappers;

import com.gmail.roadtojob2019.repositorymodule.models.Review;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ReviewDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mappings({
            @Mapping(target = "userLastName", source = "user.lastName"),
            @Mapping(target = "userName", source = "user.name"),
            @Mapping(target = "userMiddleName", source = "user.middleName"),
    })
    ReviewDTO reviewToReviewDto(Review review);

    Review reviewDtoToReview(ReviewDTO reviewDTO);
}
