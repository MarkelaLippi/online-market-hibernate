package com.gmail.roadtojob2019.servicemodule.services;

import com.gmail.roadtojob2019.repositorymodule.models.*;

public interface TestService {
    User getUser();

    Review getReview(User user);

    Article getArticle(User user);

    Comment getComment(User user);

    User getChangedUser(User user);

    Article getChangedArticle (Article article);

    Item getItem(User user);
}
