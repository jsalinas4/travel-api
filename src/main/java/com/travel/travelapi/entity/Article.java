package com.travel.travelapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "articles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    @Id
    @Column(name = "article_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idArticle;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "description")
    private String description;

    @Column(name = "image_name")
    private String imageName;

    public Integer getIdArticle() {
        return idArticle;
    }
}
