package com.foodrecipes.webapp.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodrecipes.webapp.dto.RecipeDTO;
import com.foodrecipes.webapp.model.Recipe;
import com.foodrecipes.webapp.repository.RecipeRepository;
import com.foodrecipes.webapp.service.RecipeConversionService;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final RecipeRepository recipeRepository;
    private final RecipeConversionService recipeConversionService;

    @Autowired
    public StatisticsController(RecipeRepository recipeRepository, RecipeConversionService recipeConversionService) {
        this.recipeRepository = recipeRepository;
        this.recipeConversionService = recipeConversionService;
    }

    /**
     * This controller will return top [5] recipes that sorted by views
     * change limit to modify size, remove ".reversed()" to apply normal order
     */
    @GetMapping("/popular-recipes-views")
    public Iterable<RecipeDTO> sortRecipesByViews() {
        // get List from repository
        List<Recipe> recipes = StreamSupport.stream(recipeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        // sort recipes by views
        List<Recipe> resultRecipes = recipes.stream().sorted(Comparator.comparingInt(Recipe::getViews).reversed())
                .collect(Collectors.toList());

        // return Iterable<RecipeDTO>
        return resultRecipes.stream()
                .map(recipeConversionService::convertToDto).limit(5).collect(Collectors.toList());
    }

    /**
     * This controller will return top [10] recipes that sorted by ratings
     * change limit to modify size, remove ".reversed()" to apply normal order
     */
    @GetMapping("/popular-recipes-ratings")
    public Iterable<RecipeDTO> sortRecipesByRatings() {
        // get List from repository
        List<Recipe> recipes = StreamSupport.stream(recipeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        // sort recipes by ratings
        List<Recipe> resultRecipes = recipes.stream().sorted(Comparator.comparingDouble(Recipe::getRating).reversed())
                .collect(Collectors.toList());

        // return Iterable<RecipeDTO>
        return resultRecipes.stream()
                .map(recipeConversionService::convertToDto).limit(10).collect(Collectors.toList());
    }
}
