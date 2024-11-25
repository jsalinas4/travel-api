package com.travel.travelapi.service;

import com.travel.travelapi.dto.CommentDTO;
import com.travel.travelapi.dto.CreateCommentDTO;
import com.travel.travelapi.entity.Article;
import com.travel.travelapi.entity.Comment;
import com.travel.travelapi.entity.User;
import com.travel.travelapi.exception.ResourceNotFoundException;
import com.travel.travelapi.mapper.CommentMapper;
import com.travel.travelapi.repository.ArticleRepository;
import com.travel.travelapi.repository.CommentRepository;
import com.travel.travelapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final CommentMapper commentMapper;

    @Transactional
    public CommentDTO createComment(CreateCommentDTO createCommentDTO, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Article article = articleRepository.findById(createCommentDTO.getArticleId())
                .orElseThrow(() -> new ResourceNotFoundException("Artículo no encontrado"));

        Comment comment = commentMapper.toEntity(createCommentDTO);
        comment.setUser(user);
        comment.setArticle(article);

        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toDTO(savedComment);
    }

    @Transactional
    public void deleteComment(Integer commentId, String userEmail) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario no encontrado"));

        if (!comment.getUser().getEmail().equals(userEmail)) {
            throw new IllegalStateException("No tienes permiso para eliminar este comentario");
        }

        commentRepository.delete(comment);
    }
}
