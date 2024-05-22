package com.foodrecipes.webapp.config;

import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;
import com.foodrecipes.webapp.model.Comment;
import com.foodrecipes.webapp.model.Recipe;
import com.foodrecipes.webapp.model.User;
import com.foodrecipes.webapp.dto.UserDTO;
import com.foodrecipes.webapp.dto.CommentDTO;
import com.foodrecipes.webapp.dto.RecipeDTO;
import com.foodrecipes.webapp.repository.CommentRepository;
import com.foodrecipes.webapp.repository.RecipeRepository;
import com.foodrecipes.webapp.repository.UserRepository;
import com.foodrecipes.webapp.service.RecipeConversionService;
import com.foodrecipes.webapp.service.UserConversionService;
import com.foodrecipes.webapp.service.CommentConversionService;

/**
 * DataLoader class is responsible for initializing the database with default
 * data.
 * It loads predefined users, recipes, and comments into the database upon
 * application start.
 * This class uses service layers to convert DTOs to entities before saving them
 * to the database.
 */
@Component
public class DataLoader {

        private final UserRepository userRepository;
        private final UserConversionService conversionService;
        private final RecipeRepository recipeRepository;
        private final RecipeConversionService recipeConversionService;
        private final CommentRepository commentRepository;
        private final CommentConversionService commentConversionService;

        /**
         * Constructs a new DataLoader with required repositories and conversion
         * services.
         *
         * @param userRepository           the user repository used for accessing user
         *                                 data.
         * @param conversionService        the service for converting user DTOs to user
         *                                 entities.
         * @param recipeRepository         the recipe repository used for accessing
         *                                 recipe data.
         * @param recipeConversionService  the service for converting recipe DTOs to
         *                                 recipe entities.
         * @param commentRepository        the comment repository used for accessing
         *                                 comment data.
         * @param commentConversionService the service for converting comment DTOs to
         *                                 comment entities.
         */
        public DataLoader(UserRepository userRepository, UserConversionService conversionService,
                        RecipeRepository recipeRepository, RecipeConversionService recipeConversionService,
                        CommentRepository commentRepository, CommentConversionService commentConversionService) {
                this.userRepository = userRepository;
                this.conversionService = conversionService;
                this.recipeRepository = recipeRepository;
                this.recipeConversionService = recipeConversionService;
                this.commentRepository = commentRepository;
                this.commentConversionService = commentConversionService;
        }

        /**
         * Loads predefined data into the database. This method is called automatically
         * after the component initialization.
         * It filters users to avoid duplicates based on emails and usernames, and then
         * saves valid users, recipes, and comments.
         *
         * @throws NoSuchAlgorithmException if there is an error during data processing,
         *                                  related to security algorithms.
         */
        @PostConstruct
        @Transactional
        public void loadData() throws NoSuchAlgorithmException {
                List<UserDTO> dtos = List.of(
                                new UserDTO("Yannan Zhang", "nan", "admin", "http://www.google.com",
                                                "yannan.zhang@tuni.fi", 27),
                                new UserDTO("Test User", "test", "test", "https://chatgpt.com", "testUser@test.fi", 99),
                                new UserDTO("Test User 2", "Test", "test", "http://www.youtube.com", "test@test.fi",
                                                1));

                List<User> users = dtos.stream()
                                .map(t -> {
                                        try {
                                                return conversionService.convertToEntity(t);
                                        } catch (NoSuchAlgorithmException e) {
                                                e.printStackTrace();
                                        }
                                        return null;
                                })
                                .filter(user -> !userRepository.findByEmail(user.getEmail()).isPresent())
                                .filter(user -> !userRepository.findByName(user.getName()).isPresent())
                                .collect(Collectors.toList());

                userRepository.saveAll(users);

                List<RecipeDTO> dtosR = List.of(
                                new RecipeDTO("Test Recipe1", "Test Recipe1", 0, 0, "", 1L),
                                new RecipeDTO("Test Recipe2", "Test Recipe2", 0, 0, "", 2L),
                                new RecipeDTO("Test Recipe3", "Test Recipe3", 0, 0, "", 3L),
                                new RecipeDTO("Test Recipe4", "Test Recipe4", 0, 0, "", 1L));

                List<Recipe> recipes = dtosR.stream()
                                .map(recipeConversionService::convertToEntity)
                                .collect(Collectors.toList());

                recipeRepository.saveAll(recipes);

                List<CommentDTO> dtosC = List.of(
                                new CommentDTO("Test Comment1", "2024-05-15 14:45:11","", 1L, 1L),
                                new CommentDTO("Test Comment2", "2023-06-20 07:34:55","", 2L, 1L),
                                new CommentDTO("Test Comment3", "2022-08-11 22:16:03","", 2L, 3L),
                                new CommentDTO("Test Comment4", "2024-09-07 18:02:47","", 2L, 1L),
                                new CommentDTO("Test Comment5", "2023-04-21 17:15:20","", 1L, 2L),
                                new CommentDTO("Test Comment6", "2022-11-03 13:51:29","", 3L, 1L));

                List<Comment> comments = dtosC.stream()
                                .map(commentConversionService::convertToEntity)
                                .collect(Collectors.toList());

                commentRepository.saveAll(comments);
        }
}
