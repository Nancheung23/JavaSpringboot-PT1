package com.foodrecipes.webapp.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodrecipes.webapp.dto.CommentDTO;
import com.foodrecipes.webapp.model.Comment;
import com.foodrecipes.webapp.repository.RecipeRepository;
import com.foodrecipes.webapp.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class CommentConversionService {
    @Autowired
    private UserRepository userRepository;
    private RecipeRepository recipeRepository;

    DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Constructor
     * 
     * @param userRepository
     * @param recipeRepository
     * @param commentRepository
     */
    public CommentConversionService(UserRepository userRepository, RecipeRepository recipeRepository) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
    }

    @Transactional
    public Comment convertToEntity(CommentDTO commentDto) {
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setLdt(LocalDateTime.parse(commentDto.getDate(), dft));
        Long recipeId = commentDto.getRecipeId();
        Long userId = commentDto.getUserId();
        comment.setUser(userRepository.findById(userId).orElseThrow(NoSuchElementException::new));
        comment.setRecipe(recipeRepository.findById(recipeId).orElseThrow(NoSuchElementException::new));
        return comment;
    }

    public CommentDTO convertToDto(Comment comment) {
        return new CommentDTO(comment.getContent(), comment.getLdt().toString(), comment.getRecipe().getId(),
                comment.getUser().getId());
    }
}
