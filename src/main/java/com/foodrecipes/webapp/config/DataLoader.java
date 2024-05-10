package com.foodrecipes.webapp.config;

import org.springframework.stereotype.Component;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.foodrecipes.webapp.model.User;
import com.foodrecipes.webapp.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {

    private final UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void loadData() {
        userRepository.saveAll(
            List.of(
                new User(),
                new User(),
                new User(),
                new User())
        );
    }
}
