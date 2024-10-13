package com.travel.travelapi.service;

import java.util.List;


import com.travel.travelapi.dto.ArticleDTO;


public interface ArticleService {
    ArticleDTO addArticle(ArticleDTO articleDTO);
    List<ArticleDTO> getAllArticles();
    
    //Article findById(Integer id);

    void deleteArticle(Integer id);
    
}
