package com.foodrecipes.webapp.service;

import com.foodrecipes.webapp.model.User;
import com.foodrecipes.webapp.dto.UserDTO;

import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

@Service
public class UserConversionService {

    public User convertToEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setNickName(dto.getNickName());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setAvatarUrl(dto.getAvatarUrl());
        user.setAge(dto.getAge());
        return user;
    }

    public UserDTO convertToDTO(User user) throws NoSuchAlgorithmException{
        return new UserDTO(user.getId(), user.getName(), user.getNickName(), user.getPassword(), user.getAvatarUrl(), user.getEmail(), user.getAge());
    }
}
