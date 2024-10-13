package com.travel.travelapi.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.travel.travelapi.dto.ArticleDTO;
import com.travel.travelapi.entity.Article;

@Component
public class ArticleMapper {

    private final ModelMapper modelMapper;

    public ArticleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ArticleDTO toArticleDTO(Article article) {
        return modelMapper.map(article, ArticleDTO.class);
    }

    public Article toArticle(ArticleDTO articleDTO) {
        return modelMapper.map(articleDTO, Article.class);
    }
}
