package com.foodrecipes.webapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.foodrecipes.webapp.model.Recipe;

/**
 * Recipe Repository interface extends CRUD
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    Optional<Recipe> findByTitle(String recipe);

    @Modifying
    @Query("UPDATE Recipe r SET r.user = null WHERE r.user.id = :userId")
    void updateUserIdToNull(@Param("userId") Long userId);
}
