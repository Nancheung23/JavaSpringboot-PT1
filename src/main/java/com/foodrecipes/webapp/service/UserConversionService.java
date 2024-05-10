package com.foodrecipes.webapp.service;

import com.foodrecipes.webapp.model.User;
import com.foodrecipes.webapp.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserConversionService {

    public User convertToEntity(UserDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAvatarUrl(dto.getAvatarUrl());
        return user;
    }

    public UserDTO convertToDTO(User user) {
        return new UserDTO(user.getName(), user.getEmail(), user.getAvatarUrl());
    }
}
