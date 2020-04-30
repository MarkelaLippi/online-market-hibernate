package com.gmail.roadtojob2019.servicemodule.services;

import com.gmail.roadtojob2019.repositorymodule.models.*;
import com.gmail.roadtojob2019.servicemodule.services.dtos.ItemDto;

public interface TestService {
    User getUser();

    Review getReview(User user);

    Article getArticle(User user);

    Comment getComment(User user);

    User getChangedUser(User user);

    Article getChangedArticle (Article article);

    Item getItem(User user);

    ItemDto getItemDto();
}
