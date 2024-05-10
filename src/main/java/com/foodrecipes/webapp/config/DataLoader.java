package com.foodrecipes.webapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
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
    public void loadData() {
        List<UserDTO> dtos = List.of(
            new UserDTO("Yannan Zhang", "yannan.zhang@tuni.fi", ""),
            new UserDTO("Nan", "nan@tuni.fi", "")
        );

        List<User> users = dtos.stream()
            .map(conversionService::convertToEntity)
            .collect(Collectors.toList());

        userRepository.saveAll(users);
    }
}
