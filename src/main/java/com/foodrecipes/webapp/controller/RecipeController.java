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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import com.foodrecipes.webapp.dto.RecipeDTO;
import com.foodrecipes.webapp.dto.UserDTO;
import com.foodrecipes.webapp.model.Comment;
import com.foodrecipes.webapp.model.Recipe;
import com.foodrecipes.webapp.repository.RecipeRepository;
import com.foodrecipes.webapp.service.ImageStorageService;
import com.foodrecipes.webapp.service.RecipeConversionService;
import com.foodrecipes.webapp.service.UserConversionService;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    private final RecipeRepository recipeRepository;
    private final RecipeConversionService recipeConversionService;
    private final UserConversionService userConversionService;
    private final ImageStorageService imageStorageService;

    @Autowired
    public RecipeController(RecipeRepository recipeRepository, RecipeConversionService recipeConversionService,
            UserConversionService userConversionService, ImageStorageService imageStorageService) {
        this.recipeRepository = recipeRepository;
        this.recipeConversionService = recipeConversionService;
        this.userConversionService = userConversionService;
        this.imageStorageService = imageStorageService;
    }

    @GetMapping("/")
    public Iterable<Recipe> getRecipes() {
        return recipeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Recipe> getRecipeById(@PathVariable Long id) {
        return recipeRepository.findById(id);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<Set<Comment>> getCommentsOfRecipe(@PathVariable Long id) {
        return recipeRepository.findById(id)
                .map(Recipe::getComments)
                .map(comments -> ResponseEntity.ok().body(comments))
                .orElse(ResponseEntity.notFound().build());
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
            return ResponseEntity.ok(recipeConversionService.convertToDto(recipe));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RecipeDTO> deleteRecipe(@PathVariable Long id) {
        Recipe recipe = recipeRepository.findById(id).orElse(null);
        if (recipe != null) {
            recipeRepository.delete(recipe);
            return ResponseEntity.ok(recipeConversionService.convertToDto(recipe));
        }
        return ResponseEntity.ofNullable(null);

    }

    @PostMapping("/{id}/view")
    public ResponseEntity<RecipeDTO> viewRecipe(@PathVariable Long id, @RequestBody UserDTO user) throws NoSuchAlgorithmException {
        recipeConversionService.incrementRecipeViews(id, userConversionService.convertToEntity(user).getId());
        return ResponseEntity.ok(
                recipeConversionService.convertToDto(userConversionService.convertToEntity(user).getRecipes().stream()
                        .filter(e -> e.getId() == id).findFirst().orElseThrow(NoSuchElementException::new)));
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<RecipeDTO> postImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Recipe not found"));
        String fileUrl = imageStorageService.fileStore(file);
        recipe.setPicture(fileUrl);
        recipe.setId(id);
        recipeRepository.save(recipe);
        return ResponseEntity.ok(recipeConversionService.convertToDto(recipe));
    }
}
