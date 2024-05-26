package com.foodrecipes.webapp.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.foodrecipes.webapp.model.Comment;

import jakarta.transaction.Transactional;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    @Modifying
    @Query("UPDATE Comment c SET c.user = null WHERE c.user.id = :userId")
    void updateUserIdToNull(@Param("userId") Long userId);

    @Modifying
    @Query("UPDATE Comment c SET c.recipe = null WHERE c.recipe.id = :recipeId")
    void updateRecipeIdToNull(@Param("recipeId") Long recipeId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Comment c WHERE c.recipe.id = :recipeId")
    void deleteByRecipeId(@Param("recipeId") Long recipeId);
}
