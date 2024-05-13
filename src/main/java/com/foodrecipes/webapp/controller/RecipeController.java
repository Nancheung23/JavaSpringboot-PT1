package com.foodrecipes.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import com.foodrecipes.webapp.dto.RecipeDTO;
import com.foodrecipes.webapp.model.Recipe;
import com.foodrecipes.webapp.repository.RecipeRepository;
import com.foodrecipes.webapp.service.RecipeConversionService;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeRepository recipeRepository;
    private final RecipeConversionService recipeConversionService;

    @Autowired
    public RecipeController(RecipeRepository recipeRepository, RecipeConversionService recipeConversionService) {
        this.recipeRepository = recipeRepository;
        this.recipeConversionService = recipeConversionService;
    }

    @GetMapping("/")
    public Iterable<Recipe> getRecipes() {
        return recipeRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Recipe> getRecipeById(@PathVariable Long id) {
        return recipeRepository.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<RecipeDTO> createRecipe(@RequestBody RecipeDTO recipeDto) {
        Recipe recipe = recipeConversionService.convertToEntity(recipeDto);
        recipeRepository.save(recipe);
        return ResponseEntity.ok(recipeDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeDTO> putRecipe(@PathVariable Long id, @RequestBody RecipeDTO recipeDto) {
        Recipe recipe = recipeConversionService.convertToEntity(recipeDto);
        if (!recipeRepository.existsById(id)) {
            return createRecipe(recipeDto);
        } else {
            recipe.setId(id);
            recipeRepository.save(recipe);
            return ResponseEntity.ok(recipeConversionService.convertTDto(recipe));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RecipeDTO> deleteRecipe(@PathVariable Long id) {
        Recipe recipe = recipeRepository.findById(id).orElse(null);
        recipeRepository.delete(recipe);
        return ResponseEntity.ok(recipeConversionService.convertTDto(recipe));
    }
}
