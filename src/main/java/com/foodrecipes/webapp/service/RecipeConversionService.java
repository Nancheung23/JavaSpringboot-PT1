package com.foodrecipes.webapp.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foodrecipes.webapp.model.Recipe;
import com.foodrecipes.webapp.model.User;
import com.foodrecipes.webapp.repository.RecipeRepository;
import com.foodrecipes.webapp.repository.UserRepository;

import jakarta.transaction.Transactional;

import com.foodrecipes.webapp.dto.RecipeDTO;

@Service
public class RecipeConversionService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    /**
     * Convert DTO object to Recipe object,
     * invoke getter and setter to verify data correctness.
     * 
     * @param dto
     * @return
     */
    @Transactional
    public Recipe convertToEntity(RecipeDTO dto) {
        Recipe recipe = new Recipe();
        recipe.setTitle(dto.getTitle());
        recipe.setContent(dto.getContent());
        recipe.setUser(userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid User")));
        return recipe;
    }

    /**
     * Convert Recipe object to DTO object
     * @param entity
     * @return
     */
    @Transactional
    /**
     * Convert recipe object to dto object,
     * no need for verification because user is a response entity.
     * 
     * @param entity
     * @return
     */
    public RecipeDTO convertToDto(Recipe entity) {
        return new RecipeDTO(entity.getTitle(), entity.getContent(), entity.getRating(), entity.getViews(),
                entity.getPicture(), entity.getUser().getId());
    }

    @Transactional
    /**
     * In this service method, with proper userId and recipeId (author cannot
     * increase views), add one view in views
     * 
     * @param recipeId
     * @param userId
     */
    public void incrementRecipeViews(Long recipeId, Long userId) {
        Recipe recipe = recipeRepository.findById(recipeId)
            .orElseThrow(() -> new NoSuchElementException("Recipe not found with id: " + recipeId));

        recipe.setViews(recipe.getViews() + 1);
        recipeRepository.save(recipe);
    }
}
