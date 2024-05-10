package com.foodrecipes.webapp.controller;

// Importing necessary classes from Spring framework and Java standard library
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foodrecipes.webapp.model.User;
import com.foodrecipes.webapp.model.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

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
    // private List<User> users = new ArrayList<>();
    private final UserRepository userRepository;

    /**
     * Constructor for UserController.
     * Adds three new User instances to the users list.
     */
    public UserController(UserRepository userRepository) {
        // Collections.addAll(users,
        // new User(),
        // new User(),
        // new User());

        this.userRepository = userRepository;
        this.userRepository.saveAll(List.of(
                new User(),
                new User(),
                new User()));
    }

    /**
     * Handler method for GET requests to "/users".
     * 
     * @return an Iterable of User objects containing all users.
     */
    @GetMapping("/users")
    public Iterable<User> getUsers() {
        // return users;
        return userRepository.findAll();
    }

    /**
     * Handler method for GET requests to "/users/{id}".
     * It retrieves a user by their ID.
     * 
     * @param id the ID of the user to retrieve.
     * @return an Optional containing the found user or empty if not found.
     */
    @GetMapping("/users/{id}")
    Optional<User> getUserById(@PathVariable Long id) {
        // for (User user : users) {
        //     if (Objects.equals(user.getId(), id)) {
        //         return Optional.of(user);
        //     }
        // }
        // return Optional.empty();
        return userRepository.findById(id);
    }

    /**
     * Handler method for POST requests to "/users".
     * Adds a new User to the users list.
     * 
     * @param user the User object to add, parsed from the request body.
     * @return the added User object.
     */
    @PostMapping("/users")
    User postUser(@RequestBody User user) {
        // users.add(user);
        // return user;
        return userRepository.save(user);
    }

    /**
     * Handler method for PUT requests to "/users/{id}".
     * Updates an existing user or adds a new one if the ID does not exist.
     * 
     * @param id   the ID of the user to update.
     * @param user the new User object to replace the existing one, parsed from the
     *             request body.
     * @return the updated or newly added User object.
     */
    @PutMapping("/users/{id}")
    ResponseEntity<User> putUser(@PathVariable Long id, @RequestBody User user) {
        // int userIndex = -1;
        // for (User u : users) {
        //     if (Objects.equals(u.getId(), id)) {
        //         userIndex = users.indexOf(u);
        //         users.set(userIndex, user);
        //     }
        // }
        // return (userIndex == -1) ? postUser(user) : user;
        return (!userRepository.existsById(id)) ? new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED) : new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    /**
     * Handler method for DELETE requests to "/user/{id}".
     * Removes the user with the specified ID from the users list.
     * 
     * @param id the ID of the user to delete.
     */
    @DeleteMapping("/user/{id}")
    void deleteUser(@PathVariable Long id) {
        // users.removeIf(u -> Objects.equals(u.getId(), id));
        userRepository.deleteById(id);
    }
}
