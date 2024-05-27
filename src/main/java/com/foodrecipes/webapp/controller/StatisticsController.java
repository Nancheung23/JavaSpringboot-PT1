package com.foodrecipes.webapp.controller;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodrecipes.webapp.dto.RecipeDTO;
import com.foodrecipes.webapp.model.Recipe;
import com.foodrecipes.webapp.model.User;
import com.foodrecipes.webapp.repository.RecipeRepository;
import com.foodrecipes.webapp.repository.UserRepository;
import com.foodrecipes.webapp.service.RecipeConversionService;
import com.foodrecipes.webapp.service.UserConversionService;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final RecipeRepository recipeRepository;
    private final RecipeConversionService recipeConversionService;
    private final UserRepository userRepository;
    private final UserConversionService userConversionService;

    @Autowired
    public StatisticsController(RecipeRepository recipeRepository, RecipeConversionService recipeConversionService,
            UserRepository userRepository, UserConversionService userConversionService) {
        this.recipeRepository = recipeRepository;
        this.recipeConversionService = recipeConversionService;
        this.userRepository = userRepository;
        this.userConversionService = userConversionService;
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

    @GetMapping("/searchByUserId")
    public Iterable<RecipeDTO> searchRecipesByUserId(@RequestParam Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        if (user != null) {
            // return recipes from user
            return user.getRecipes().stream().map(recipeConversionService::convertToDto).collect(Collectors.toList());
        } else {
            throw new NoSuchElementException("Can't find user with UserId: " + userId);
        }
    }

    /**
     * Search familiar recipes with keyword
     * 
     * @param keyword
     * @return Iterable<RecipeDTO>, which fullfill the filter
     */
    @GetMapping("/searchByKeyWord/{option}")
    public Iterable<RecipeDTO> searchRecipesByKeyWord(@RequestParam String keyword,
            @PathVariable String option) {
        if (!option.equals("title") && !option.equals("content")) {
            throw new IllegalArgumentException(String.format("Option value %s is invalid", option));
        }

        // get List from repository
        List<Recipe> recipes = StreamSupport.stream(recipeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        if (option.equals("title")) {

            // filter keywords for title
            List<Recipe> resultRecipes = recipes.stream().filter(r -> r.getTitle().contains(keyword))
                    .sorted(Comparator.comparingInt(Recipe::getViews)).collect(Collectors.toList());

            // return Iterable<RecipeDTO>
            return resultRecipes.stream().map(recipeConversionService::convertToDto).collect(Collectors.toList());
        } else if (option.equals("content")) {

            // filter keywords for title
            List<Recipe> resultRecipes = recipes.stream().filter(r -> r.getContent().contains(keyword))
                    .sorted(Comparator.comparingInt(Recipe::getViews)).collect(Collectors.toList());

            // return Iterable<RecipeDTO>
            return resultRecipes.stream().map(recipeConversionService::convertToDto).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    /**
     * Check recipes under user and fullfill the keyword filter with specific option
     * @param keyword
     * @param userId
     * @param option
     * @return Iterable<RecipeDTO>
     */
    @GetMapping("/searchByKeyWordFromUser/{option}")
    public Iterable<RecipeDTO> searchRecipesByKeyWordFromUser(@RequestParam String keyword,
            @RequestParam Long userId, @PathVariable String option) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        if (user != null) {
            // recipes from user
            return StreamSupport.stream(searchRecipesByKeyWord(keyword, option).spliterator(), false)
                .filter(r -> r.getUserId().equals(userId)).collect(Collectors.toList());

        } else {
            throw new NoSuchElementException("Can't find user with UserId: " + userId);
        }
    }

    /**
     * With filter number and option, get recipes fullfill option's oriented value requirements
     * @param filteNumber
     * @param option
     * @return Iterable<RecipeDTO>
     */
    @GetMapping("/recipeFilter/{option}")
    public Iterable<RecipeDTO> recipeFilterByOption(@RequestParam Number filterNumber, @PathVariable String option) {
        if (!option.equals("view") && !option.equals("rating")) {
            throw new IllegalArgumentException(String.format("Option value %s is invalid", option));
        }
        // get List from repository
        List<Recipe> recipes = StreamSupport.stream(recipeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        if (option.equals("rating")) {
            Double value = filterNumber.doubleValue();
            return recipes.stream().filter(r -> r.getRating() >= value).map(recipeConversionService::convertToDto).collect(Collectors.toList());
        } else if (option.equals("view")) {
            Integer value = filterNumber.intValue();
            return recipes.stream().filter(r -> r.getViews() >= value).map(recipeConversionService::convertToDto).collect(Collectors.toList());
        } else {
            return null;
        }
    }
}
