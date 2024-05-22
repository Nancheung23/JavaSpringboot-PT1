package com.foodrecipes.webapp.service;

import com.foodrecipes.webapp.model.User;
import com.foodrecipes.webapp.dto.UserDTO;

import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

@Service
public class UserConversionService {

    /**
     * Convert DTO object to User object,
     * invoke getter and setter to verify data correctness.
     * 
     * @param dto
     * @return user
     */
    public User convertToEntity(UserDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setNickName(dto.getNickName());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setAvatarUrl(dto.getAvatarUrl());
        user.setBirthDate(dto.getBirthDate());
        return user;
    }

    /**
     * Convert user object to dto object,
     * no need for verification because user is a response entity.
     * 
     * @param user
     * @return new DTO
     * @throws NoSuchAlgorithmException
     */
    public UserDTO convertToDTO(User user) throws NoSuchAlgorithmException {
        return new UserDTO(user.getName(), user.getNickName(), user.getPassword(), user.getAvatarUrl(),
                user.getEmail(), user.getBirthDate());
    }
}
