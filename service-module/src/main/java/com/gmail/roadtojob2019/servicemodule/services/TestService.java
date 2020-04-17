package com.gmail.roadtojob2019.servicemodule.services;

import com.gmail.roadtojob2019.repositorymodule.models.Article;
import com.gmail.roadtojob2019.repositorymodule.models.Comment;
import com.gmail.roadtojob2019.repositorymodule.models.Review;
import com.gmail.roadtojob2019.repositorymodule.models.User;

public interface TestService {
    User getUser();

    Review getReview(User user);

    Article getArticle(User user);

    Comment getComment(User user);
}
