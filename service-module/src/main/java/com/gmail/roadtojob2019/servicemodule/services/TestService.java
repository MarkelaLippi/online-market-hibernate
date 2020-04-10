package com.gmail.roadtojob2019.servicemodule.services;

import com.gmail.roadtojob2019.repositorymodule.models.Review;
import com.gmail.roadtojob2019.repositorymodule.models.User;

public interface TestService {
    User getUser();

    Review getReview(User user);
}
