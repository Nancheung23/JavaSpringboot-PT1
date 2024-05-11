package com.foodrecipes.webapp.dto;

import java.security.NoSuchAlgorithmException;

import com.foodrecipes.webapp.security.HashingUtility;

/**
 * Data Transfer Object for user details. Used to pass user data safely without exposing sensitive information.
 */
public class UserDTO {

    private Long id;  // Unique identifier for the user, okay to expose if necessary for reference in client operations.
    private String name; // Username, important for display or user identification purposes.
    private String nickName; // User's nickname, often used for greetings or personalizations.
    private String password; // User's password, important for authentication purposes.
    private String avatarUrl; // URL of the user's avatar image, important for user interface display.
    private String email; // User's email address, can be used for contact or identification purposes.
    private int age; // User's age, could be relevant for context or personalized content.

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String nickName, String password, String avatarUrl, String email, int age) throws NoSuchAlgorithmException{
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        // encrypt password
        String salt = HashingUtility.generateSalt();
        this.password =  HashingUtility.hashPassword(password, salt);
        
        this.avatarUrl = avatarUrl;
        this.email = email;
        this.age = age;
    }

    // Standard getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("UserDTO [id=%s, name=%s, nickName=%s, avatarUrl=%s, email=%s, age=%d]",
                            id, name, nickName, avatarUrl, email, age);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException{
        String salt = HashingUtility.generateSalt();
        this.password =  HashingUtility.hashPassword(password, salt);
    }
}
