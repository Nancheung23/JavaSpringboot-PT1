package com.foodrecipes.webapp.controller;

// Importing necessary classes from Spring framework and Java standard library
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.foodrecipes.webapp.dto.UserDTO;
import com.foodrecipes.webapp.model.Comment;
import com.foodrecipes.webapp.model.Recipe;
import com.foodrecipes.webapp.model.User;
import com.foodrecipes.webapp.repository.RecipeRepository;
import com.foodrecipes.webapp.repository.UserRepository;
import com.foodrecipes.webapp.service.UserConversionService;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

/**
 * The UserController class handles HTTP requests related to user operations.
 * It is annotated with @RestController, indicating that it's a Spring MVC
 * controller
 * with its responses automatically serialized into JSON or XML.
 * The @RequestMapping("/") annotation specifies that all the handler methods
 * in this controller will be treated as having the base path "/".
 */
@RestController
@RequestMapping("/users")
public class UserController {
    // List to hold User objects. Initialized as an ArrayList.
    private final UserRepository userRepository;
    private final UserConversionService conversionService;

    /**
     * Constructor for UserController.
     * 
     * @param userRepository
     * @param conversionService
     */
    // @Autowired
    public UserController(UserRepository userRepository, UserConversionService conversionService) {
        this.userRepository = userRepository;
        this.conversionService = conversionService;
    }

    /**
     * Handler method for GET requests to "/users".
     * 
     * @return an Iterable of User objects containing all users.
     */
    @GetMapping("/")
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Handler method for GET requests to "/users/{id}".
     * It retrieves a user by their ID.
     * 
     * @param id the ID of the user to retrieve.
     * @return an Optional containing the found user or empty if not found.
     */
    @GetMapping("/{id}")
    Optional<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @GetMapping("/{id}/recipes")
    public Set<Recipe> getRecipesOfUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if (user != null) {
            return user.getRecipes();
        }
        return new HashSet<>();
    }

    @GetMapping("/{id}/comments")
    public Set<Comment> getCommentsOfUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if (user != null) {
            return user.getComments();
        }
        return new HashSet<>();
    }

    /**
     * Post Data with DTO
     * 
     * @param userDto
     * @return status of DTO REQUEST.POST
     * @throws NoSuchAlgorithmException
     */
    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDto) throws NoSuchAlgorithmException {
        // DTO -> User
        User user = conversionService.convertToEntity(userDto);
        // save User entity
        user = userRepository.save(user);
        // convert back UserDTO
        UserDTO responseDto = conversionService.convertToDTO(user);
        // return Dto in 200 status
        return ResponseEntity.ok(responseDto);
    }

    /**
     * Put DTO into Table, create when doesn't exist in database
     * when successful, send HTTPStaus: OK
     * 
     * @param id
     * @param userDto
     * @return
     * @throws NoSuchAlgorithmException
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> putUser(@PathVariable Long id, @RequestBody UserDTO userDto)
            throws NoSuchAlgorithmException {
        User user = conversionService.convertToEntity(userDto);
        if (!userRepository.existsById(id)) {
            return createUser(userDto);
        } else {
            user.setId(id);
            userRepository.save(user);
            return ResponseEntity.ok(conversionService.convertToDTO(user));
        }
    }

    /**
     * Handler method for DELETE requests to "/user/{id}".
     * Removes the user with the specified ID from the users list.
     * 
     * @param id the ID of the user to delete.
     */
    @DeleteMapping("/{id}")
    ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) throws NoSuchAlgorithmException {
        User user = userRepository.findById(id).orElse(null);
        userRepository.deleteById(id);
        return ResponseEntity.ok(conversionService.convertToDTO(user));
    }
}
