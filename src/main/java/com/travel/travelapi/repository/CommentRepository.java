package com.travel.travelapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travelapi.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
