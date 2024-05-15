package com.foodrecipes.webapp.config;

import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

import com.foodrecipes.webapp.model.Recipe;
import com.foodrecipes.webapp.model.User;
import com.foodrecipes.webapp.dto.UserDTO;
import com.foodrecipes.webapp.dto.RecipeDTO;
import com.foodrecipes.webapp.repository.RecipeRepository;
import com.foodrecipes.webapp.repository.UserRepository;
import com.foodrecipes.webapp.service.RecipeConversionService;
import com.foodrecipes.webapp.service.UserConversionService;

@Component
public class DataLoader {
    /**
     * userRepository is the dataset linked in dataBase,
     * conversionService is the converter object, return DTO object or User object
     */
    private final UserRepository userRepository;
    private final UserConversionService conversionService;
    private final RecipeRepository recipeRepository;
    private final RecipeConversionService recipeConversionService;

    /**
     * Constructor
     * 
     * @param userRepository
     * @param conversionService
     */
    // @Autowired
    public DataLoader(UserRepository userRepository, UserConversionService conversionService, RecipeRepository recipeRepository, RecipeConversionService recipeConversionService) {
        this.userRepository = userRepository;
        this.conversionService = conversionService;
        this.recipeRepository = recipeRepository;
        this.recipeConversionService = recipeConversionService;
    }

    /**
     * Save initial data in database table, name = 'users',
     * filter if there is same Email and username
     * 
     * @throws NoSuchAlgorithmException
     */
    @PostConstruct
    @Transactional
    public void loadData() throws NoSuchAlgorithmException {
        /**
         * create initialized list of UserDTO instances
         */
        List<UserDTO> dtos = List.of(
                new UserDTO( "Yannan zhang", "nan", "admin", "http://www.google.com", "yannan.zhang@tuni.fi", 27),
                new UserDTO("Test User", "test", "test", "https://chatgpt.com", "testUser@test.fi", 99),
                new UserDTO("Test User 2", "Test", "test", "http://www.youtube.com", "test@test.fi", 1)
        );

        List<RecipeDTO> dtosR = List.of(
            new RecipeDTO("Test Recipe1", "Test Recipe1", 0, 0, (long)1),
            new RecipeDTO("Test Recipe2", "Test Recipe2", 0, 0, (long)2),
            new RecipeDTO("Test Recipe3", "Test Recipe3", 0, 0, (long)3),
            new RecipeDTO("Test Recipe4", "Test Recipe4", 0, 0, (long)1)
        );

        /**
         * convert all objects in list to user object
         * map -> filter for email and username -> collect to new list
         * userRepository.saveAll(users);
         */
        List<User> users = dtos.stream()
                .map(conversionService::convertToEntity)
                .filter(user -> !userRepository.findByEmail(user.getEmail()).isPresent()) // Check if email already
                                                                                          // exists
                .filter(user -> !userRepository.findByName(user.getName()).isPresent()) // Check if username already
                                                                                        // exists
                .collect(Collectors.toList());

        
        List<Recipe> recipes = dtosR.stream()
                .map(recipeConversionService::convertToEntity)
                .collect(Collectors.toList());
                // save to database
        userRepository.saveAll(users);
        recipeRepository.saveAll(recipes);
        
    }
}
