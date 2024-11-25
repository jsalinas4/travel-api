package com.travel.travelapi.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.travel.travelapi.dto.CommentDTO;
import com.travel.travelapi.dto.CreateCommentDTO;
import com.travel.travelapi.entity.Comment;

@Component
public class CommentMapper {

    private final ModelMapper modelMapper;

    public CommentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CommentDTO toDTO(Comment comment) {
        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
        commentDTO.setUserId(comment.getUser().getId());
        commentDTO.setArticleId(comment.getArticle().getIdArticle());
        commentDTO.setUserFullName(comment.getUser().getFullName());
        return commentDTO;
    }

    public Comment toEntity(CreateCommentDTO createCommentDTO) {
        return modelMapper.map(createCommentDTO, Comment.class);
    }
}
