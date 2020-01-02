package com.gmail.roadtojob2019.repositorymodule.repositories;

import com.gmail.roadtojob2019.repositorymodule.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long> {
}
