package com.foodrecipes.webapp.dto;

import java.security.NoSuchAlgorithmException;
import com.foodrecipes.webapp.security.HashingUtility;

/**
 * Data Transfer Object for user details. Used to pass user data safely without
 * exposing sensitive information.
 */
public class UserDTO {

    private String name; // Username, important for display or user identification purposes.
    private String nickName; // User's nickname, often used for greetings or personalizations.
    private String password; // User's password, important for authentication purposes.
    private String avatarUrl; // URL of the user's avatar image, important for user interface display.
    private String email; // User's email address, can be used for contact or identification purposes.
    private int age; // User's age, could be relevant for context or personalized content.
    private String salt;

    // Non-parameter constructor
    public UserDTO() {
    }

    /**
     * Constructor for user DTO
     * 
     * @param name
     * @param nickName
     * @param password
     * @param avatarUrl
     * @param email
     * @param age
     * @throws NoSuchAlgorithmException
     */
    public UserDTO(String name, String nickName, String password, String avatarUrl, String email, int age)
            throws NoSuchAlgorithmException {
        this.name = name;
        this.nickName = nickName;
        // encrypt password
        setPassword(password);
        this.avatarUrl = avatarUrl;
        this.email = email;
        this.age = age;
    }

    // Standard getters and setters

    public String getName() {
        return name;
    }

    /**
     * Setter: username
     * if a username's length is below 3, then throw IllegalArgument exception
     * 
     * @param name
     */
    public void setName(String name) {
        if (name.length() < 3) {
            throw new IllegalArgumentException("Invalid username");
        }
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    /**
     * Setter: nickname
     * if a nickname's length is below 3, then throw IllegalArgument exception
     * 
     * @param nickName
     */
    public void setNickName(String nickName) {
        if (nickName.length() < 3) {
            throw new IllegalArgumentException("Invalid nickname");
        }
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * Setter: avatarUrl
     * if a avatarUrl doesn't start with 'http://' or 'https://',
     * then throw IllegalArgument exception
     * 
     * @param avatarUrl
     */
    public void setAvatarUrl(String avatarUrl) {
        if (avatarUrl.startsWith("http://") || avatarUrl.startsWith("https://")) {
            this.avatarUrl = avatarUrl;
        } else {
            throw new IllegalArgumentException("Invalid avatar source");
        }

    }

    public String getEmail() {
        return email;
    }

    /**
     * Setter: email
     * if a email doesn't have '@', then throw IllegalArgument exception
     * 
     * @param email
     */
    public void setEmail(String email) {
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    /**
     * Setter: age
     * if a age is not in range: [0, 120], then throw IllegalArgument exception
     * 
     * @param age
     */
    public void setAge(int age) {
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Invalid age");
        }
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Translate raw password to hashpassword (SHA-256) with salt value,
     * when set password into DTO object. Protect user password.
     * 
     * @param password
     * @return salt
     */
    public void setPassword(String password)  {
        this.password = password;
    }
    
    public String getSalt() {
        return this.salt;
    }

    @Override
    public String toString() {
        return "Name:  " + name +
                "\nNickName: " + nickName +
                "\nAvatarUrl: " + avatarUrl +
                "\nEmail: " + email +
                "\nAge: " + age;
    }
}
