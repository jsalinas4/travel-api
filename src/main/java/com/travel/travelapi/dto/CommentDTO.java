package com.travel.travelapi.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CommentDTO {
    private Integer id;
    private String content;
    private LocalDateTime createdAt;
    private Integer userId;
    private Integer articleId;
    private String userFullName;
}
