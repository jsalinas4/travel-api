package com.travel.travelapi.service.impl;

import com.travel.travelapi.dto.ArticleDTO;
import com.travel.travelapi.entity.Article;
import com.travel.travelapi.mapper.ArticleMapper;
import com.travel.travelapi.repository.ArticleRepository;
import com.travel.travelapi.service.ArticleService;


import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    public List<ArticleDTO> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        List<ArticleDTO> articlesAll = new ArrayList<>();
        for (Article article : articles) {
            articlesAll.add(articleMapper.toArticleDTO(article));
        }
        return articlesAll;
    }

    public ArticleDTO addArticle(ArticleDTO ArticleDTO){

            Article article = articleMapper.toArticle(ArticleDTO);
            article = articleRepository.save(article);
            return articleMapper.toArticleDTO(article);

        }
    
    
    public void deleteArticle(Integer id){
        Article article = findById(id);
        articleRepository.delete(article);
    }

    
    public Article findById(Integer id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró usuario con ese id " + id));
    }

}