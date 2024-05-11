package com.foodrecipes.webapp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.foodrecipes.webapp.model.User;

public interface UserRepository  extends CrudRepository<User, Long>{
    Optional<User> findByEmail(String email);
} 
