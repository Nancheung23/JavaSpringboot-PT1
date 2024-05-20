package com.foodrecipes.webapp.service;

import com.foodrecipes.webapp.model.User;
import com.foodrecipes.webapp.repository.UserRepository;
import com.foodrecipes.webapp.dto.UserDTO;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserConversionService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

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
        user.setAge(dto.getAge());
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
                user.getEmail(), user.getAge());
    }

    // Jwt Session: find user
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        // parse username and password to UserDetails' username and password
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }
}
