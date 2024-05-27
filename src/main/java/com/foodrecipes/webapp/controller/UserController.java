package com.foodrecipes.webapp.controller;

// Importing necessary classes from Spring framework and Java standard library
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foodrecipes.webapp.dto.CommentDTO;
import com.foodrecipes.webapp.dto.RecipeDTO;
import com.foodrecipes.webapp.dto.UserDTO;
import com.foodrecipes.webapp.model.Comment;
import com.foodrecipes.webapp.model.Recipe;
import com.foodrecipes.webapp.model.User;
import com.foodrecipes.webapp.repository.CommentRepository;
import com.foodrecipes.webapp.repository.RecipeRepository;
import com.foodrecipes.webapp.repository.UserLogRepository;
import com.foodrecipes.webapp.repository.UserRepository;
import com.foodrecipes.webapp.service.CommentConversionService;
import com.foodrecipes.webapp.service.ImageStorageService;
import com.foodrecipes.webapp.service.RecipeConversionService;
import com.foodrecipes.webapp.service.UserConversionService;
import com.foodrecipes.webapp.service.UserLogConversionService;

import jakarta.transaction.Transactional;

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
@RequestMapping("/api/users")
public class UserController {
    // List to hold User objects. Initialized as an ArrayList.
    private final UserRepository userRepository;
    private final UserConversionService conversionService;
    private final RecipeRepository recipeRepository;
    private final RecipeConversionService recipeConversionService;
    private final CommentRepository commentRepository;
    private final CommentConversionService commentConversionService;
    private final ImageStorageService imageStorageService;
    private final UserLogRepository userLogRepository;
    private final UserLogConversionService userLogConversionService;

    // logger for checking authentication
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * Constructor for UserController.
     * 
     * @param userRepository
     * @param conversionService
     */
    @Autowired
    public UserController(UserRepository userRepository, UserConversionService conversionService,
            RecipeRepository recipeRepository, RecipeConversionService recipeConversionService,
            CommentRepository commentRepository, CommentConversionService commentConversionService,
            ImageStorageService imageStorageService, UserLogRepository userLogRepository,
            UserLogConversionService userLogConversionService) {
        this.userRepository = userRepository;
        this.conversionService = conversionService;
        this.recipeRepository = recipeRepository;
        this.recipeConversionService = recipeConversionService;
        this.commentRepository = commentRepository;
        this.commentConversionService = commentConversionService;
        this.imageStorageService = imageStorageService;
        this.userLogConversionService = userLogConversionService;
        this.userLogRepository = userLogRepository;
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

    /**
     * Note: record log
     * 
     * @param id
     * @return
     */
    @GetMapping("/{id}/recipes")
    public Set<Recipe> getRecipesOfUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if (user != null) {
            // record log
            String detail = String.format("Search recipes -- Found recipes under user %d: %d recipes in total.", id,
                    user.getRecipes().size());
            userLogRepository.save(userLogConversionService.recordLog(detail, id));
            return user.getRecipes();
        }
        return new HashSet<>();
    }

    /**
     * Note: record log
     * 
     * @param id
     * @return
     */
    @GetMapping("/{id}/comments")
    public Set<Comment> getCommentsOfUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if (user != null) {
            // record log
            String detail = String.format("Search comments -- Found recipes under user %d: %d comments in total.", id,
                    user.getComments().size());
            userLogRepository.save(userLogConversionService.recordLog(detail, id));
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
        // record log
        String detail = String.format("Create user -- Create user %d.", user.getId());
        userLogRepository.save(userLogConversionService.recordLog(detail, user.getId()));
        // return Dto in 200 status
        return ResponseEntity.ok(responseDto);
    }

    /**
     * Post comment under recipe for user, and record log
     * 
     * @param userId
     * @param recipeId
     * @param commentDto
     * @return
     */
    @PostMapping("/{userId}/comments/{recipeId}/post")
    public ResponseEntity<CommentDTO> createCommentFromUserForRecipe(@PathVariable Long userId,
            @PathVariable Long recipeId, @RequestBody CommentDTO commentDto) {
        User user = userRepository.findById(userId).orElse(null);
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);
        if (user != null && recipe != null) {
            Comment comment = commentConversionService.convertToEntity(commentDto);
            comment.setUser(user);
            comment.setRecipe(recipe);
            commentRepository.save(comment);
            user.setComment(comment);
            recipe.setComment(comment);
            // record log
            String detail = String.format(
                    "Create comment -- Create comment under recipe %d for user %d. Comment id: %d", recipeId, userId,
                    comment.getId());
            userLogRepository.save(userLogConversionService.recordLog(detail, userId));
            return ResponseEntity.ok(commentConversionService.convertToDto(comment));
        } else {
            // error log
            String detail = String.format("Create comment -- Can't find user id: %d", userId);
            userLogRepository.save(userLogConversionService.recordLog(detail, userId));
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Rating one recipe by user, record log
     * @param userId
     * @param recipeId
     * @param rating
     * @return
     */
    @PostMapping("/{userId}/comments/{recipeId}/rating")
    public ResponseEntity<RecipeDTO> createRatingFromUserForRecipe(@PathVariable Long userId,
            @PathVariable Long recipeId, @RequestBody double rating) {
        User user = userRepository.findById(userId).orElse(null);
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);
        if (user != null && recipe != null) {
            if (!user.getRecipes().contains(recipe)) {
                recipe.setRating(rating);
                recipe.setId(recipeId);
                recipeRepository.save(recipe);
                // record log
                String detail = String.format(
                        "Create rating -- Create rating for recipe %d by user %d.", recipeId,
                        userId);
                userLogRepository.save(userLogConversionService.recordLog(detail, userId));
                return ResponseEntity.ok(recipeConversionService.convertToDto(recipe));
            } else {
                String detail = String.format("Create rating -- Recipe %d doesn't exist", recipeId);
                userLogRepository.save(userLogConversionService.recordLog(detail, userId));
                return ResponseEntity.badRequest().build();
            }
        } else {
            String detail = String.format("Create rating -- User %d or Recipe %d is null", userId, recipeId);
            userLogRepository.save(userLogConversionService.recordLog(detail, userId));
            return ResponseEntity.badRequest().build();
        }
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
        logger.info("user: {}", user.getName());
        if (!userRepository.existsById(id)) {
            logger.info("new user: {}", id);
            return createUser(userDto);
        } else {
            user.setId(id);
            userRepository.save(user);
            logger.info("user changed: {}", user.getName());
            String detail = String.format("Modify user -- User %d modified", user.getId());
            userLogRepository.save(userLogConversionService.recordLog(detail, user.getId()));
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
    @Transactional
    ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) throws NoSuchAlgorithmException {
        User user = userRepository.findById(id).orElse(null);
        logger.info("user: {}", user.getName());
        commentRepository.updateUserIdToNull(id);
        recipeRepository.updateUserIdToNull(id);
        userRepository.deleteById(id);
        logger.info("deleted id: {}", id);
        // record log
        String detail = String.format("Delete user -- User %d deleted", id);
        userLogRepository.save(userLogConversionService.recordLog(detail, id));
        return ResponseEntity.ok(conversionService.convertToDTO(user));
    }

    /**
     * Every time when user upload a image, store into /upload, then save url info
     * of user
     * 
     * @param id
     * @param file
     * @return
     * @throws NoSuchAlgorithmException
     */
    @PatchMapping("/{id}/avatar")
    ResponseEntity<UserDTO> uploadAvatar(@PathVariable Long id, @RequestParam("file") MultipartFile file)
            throws NoSuchAlgorithmException {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        String fileUrl = imageStorageService.fileStore(file);
        logger.info("file url: {}", fileUrl);
        user.setAvatarUrl(fileUrl);
        user.setId(id);
        userRepository.save(user);
        // record log
        String detail = String.format("Upload avatar -- User %d uploaded avatar: %s", user.getId(), fileUrl);
        userLogRepository.save(userLogConversionService.recordLog(detail, user.getId()));
        return ResponseEntity.ok(conversionService.convertToDTO(user));
    }
}
