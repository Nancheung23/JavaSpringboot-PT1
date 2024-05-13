package com.foodrecipes.webapp.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.foodrecipes.webapp.model.User;
import java.util.List;

/**
 * User Repository interface extends CRUD,
 * userrepository has same functionality as a collection,
 * but pre-set methods for example:
 * save(User user);
 * findById(Long id);
 * deleteById(Long id);
 */
public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * Abstract method: "findByEmail", Override in "DataLoader.java",
     * receive email parameter and verify if exist email value in dataBase
     * 
     * @param email
     * @return Optional<User>
     */
    Optional<User> findByEmail(String email);

    /**
     * Abstract method: "findByName", Override in "DataLoader.java",
     * receive username parameter and verify if exist username value in dataBase
     * 
     * @param username
     * @return
     */
    Optional<User> findByName(String name);
}
