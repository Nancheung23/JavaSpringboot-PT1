package com.foodrecipes.webapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.foodrecipes.webapp.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
