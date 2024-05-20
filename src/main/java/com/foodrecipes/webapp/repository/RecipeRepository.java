package com.foodrecipes.webapp.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.foodrecipes.webapp.model.Recipe;

/**
 * Recipe Repository interface extends CRUD
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    Optional<Recipe> findByTitle(String recipe);
}
