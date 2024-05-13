package com.foodrecipes.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foodrecipes.webapp.model.Recipe;
import com.foodrecipes.webapp.repository.UserRepository;
import com.foodrecipes.webapp.dto.RecipeDTO;

@Service
public class RecipeConversionService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Convert DTO object to Recipe object,
     * invoke getter and setter to verify data correctness.
     * 
     * @param dto
     * @return
     */
    public Recipe convertToEntity(RecipeDTO dto) {
        Recipe recipe = new Recipe();
        recipe.setTitle(dto.getTitle());
        recipe.setContent(dto.getContent());
        recipe.setUser(userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid User")));
        return recipe;
    }

    /**
     * Convert recipe object to dto object,
     * no need for verification because user is a response entity.
     * 
     * @param entity
     * @return
     */
    public RecipeDTO convertTDto(Recipe entity) {
        return new RecipeDTO(entity.getTitle(), entity.getContent(), entity.getUser().getId());
    }
}
