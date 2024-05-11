package com.foodrecipes.webapp.config;

import org.springframework.beans.factory.annotation.Autowired;
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

    private final UserRepository userRepository;
    private final UserConversionService conversionService;

    @Autowired
    public DataLoader(UserRepository userRepository, UserConversionService conversionService) {
        this.userRepository = userRepository;
        this.conversionService = conversionService;
    }

    @PostConstruct
    public void loadData() throws NoSuchAlgorithmException {
        List<UserDTO> dtos = List.of(
            new UserDTO((long) 99999999, "Yannan zhang", "nan", "admin", "www.google.com", "yannan.zhang@tuni.fi", 27)
        );

        List<User> users = dtos.stream()
            .map(conversionService::convertToEntity)
            .filter(user -> !userRepository.findByEmail(user.getEmail()).isPresent()) // Check if email already exists
            .collect(Collectors.toList());

        userRepository.saveAll(users);
    }
}
