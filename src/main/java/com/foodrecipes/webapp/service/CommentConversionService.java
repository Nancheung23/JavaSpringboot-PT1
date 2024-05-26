package com.foodrecipes.webapp.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Autowired
    private RecipeRepository recipeRepository;

    DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // logger for checking authentication
    private static final Logger logger = LoggerFactory.getLogger(CommentConversionService.class);

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
        logger.info("comment content: {}", commentDto.getContent());
        comment.setContent(commentDto.getContent());
        logger.info("comment date: {}", commentDto.getDate());
        comment.setLdt(LocalDateTime.parse(commentDto.getDate(), dft));
        Long recipeId = commentDto.getRecipeId();
        Long userId = commentDto.getUserId();
        comment.setUser(userRepository.findById(userId).orElseThrow(NoSuchElementException::new));
        comment.setRecipe(recipeRepository.findById(recipeId).orElseThrow(NoSuchElementException::new));
        return comment;
    }
    
    /**
     * Convert recipe object to dto object,
     * 
     * @param comment
     * @return
     */
    @Transactional
    public CommentDTO convertToDto(Comment comment) {
        return new CommentDTO(comment.getContent(), comment.getLdt().toString(), comment.getPicture(), comment.getRecipe().getId(),
                comment.getUser().getId());
    }
}
