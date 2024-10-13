package com.travel.travelapi.repository;

import com.travel.travelapi.entity.Article;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    //Article findById(String id);
    //void deleteArticle(Integer id);

}
