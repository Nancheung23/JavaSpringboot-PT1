package com.foodrecipes.webapp.config;

import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;
import com.foodrecipes.webapp.model.User;
import com.foodrecipes.webapp.dto.UserDTO;
import com.foodrecipes.webapp.repository.UserRepository;
import com.foodrecipes.webapp.service.UserConversionService;

@Component
public class DataLoader {
    /**
     * userRepository is the dataset linked in dataBase,
     * conversionService is the converter object, return DTO object or User object
     */
    private final UserRepository userRepository;
    private final UserConversionService conversionService;

    /**
     * Constructor
     * 
     * @param userRepository
     * @param conversionService
     */
    // @Autowired
    public DataLoader(UserRepository userRepository, UserConversionService conversionService) {
        this.userRepository = userRepository;
        this.conversionService = conversionService;
    }

    /**
     * Save initial data in database table, name = 'users',
     * filter if there is same Email and username
     * 
     * @throws NoSuchAlgorithmException
     */
    @PostConstruct
    public void loadData() throws NoSuchAlgorithmException {
        /**
         * create initialized list of UserDTO instances
         */
        List<UserDTO> dtos = List.of(
                new UserDTO( "Yannan zhang", "nan", "admin", "http://www.google.com", "yannan.zhang@tuni.fi", 27),
                new UserDTO("Test User", "test", "test", "https://chatgpt.com", "testUser@test.fi", 99),
                new UserDTO("Test User 2", "Test", "test", "http://www.youtube.com", "test@test.fi", 1)
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

        // save to database
        userRepository.saveAll(users);
    }
}
