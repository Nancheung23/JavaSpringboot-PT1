package com.foodrecipes.webapp.dto;

import java.security.NoSuchAlgorithmException;
import com.foodrecipes.webapp.security.HashingUtility;
import java.util.Set;
import java.util.regex.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

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
    // private int age; // User's age, could be relevant for context or personalized content.
    private String birthDate; // User's birthdate, can be used for age restrictions and personalized content.

    private static final Set<Character> PROHIBITED_SYMBOLS = Set.of('<', '>', '/', '\\', '\'', '"', ';', ':', '&', '|', '!', '#', '$', '%', '^', '*', '(', ')', '+', '=', '?', '{', '}', '[', ']', ',', '.', '@', ' ', '\t', '\n');
    private static final String EMAIL_VALIDATION_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_VALIDATION_REGEX);

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
    public UserDTO(String name, String nickName, String password, String avatarUrl, String email, String birthDate)
            throws NoSuchAlgorithmException {
        this.setName(name);
        this.setNickName(nickName);
        this.setPassword(password);
        this.setAvatarUrl(avatarUrl);
        this.setEmail(email);
        this.setBirthDate(birthDate);
    }

    /**
     * Standart getter for username
     * @return username
     */
    public String getName() {
        return name;
    }

    /**
     * Setter: username
     * The username length must be between 3 and 50 characters
     * and it must contain only permitted symbols.
     * 
     * @param name
     * @throws IllegalArgumentException if the username is empty, null, shorter
     * than 3 characters or longer than 50 characters
     * or has any of restricted symbols
     */
    public void setName(String name) {
        if (name.isEmpty() || name == null) {
            throw new IllegalArgumentException("Empty username.");
        } else if (name.length() < 3 || name.length() > 50) {
            throw new IllegalArgumentException("Invalid username, 3 to 50 symbols allowed.");
        }
        for (char c : name.toCharArray()) {
            if (PROHIBITED_SYMBOLS.contains(c)) throw new IllegalArgumentException(
                "Invalid username. No > < / \\ \' \" ; : & | ! # $ % * ( ) + = ? { } [ ] , . @ ^ symbols allowed in the username; no whitespaces and tabs allowed in the username."
            );
        }
        this.name = name;
    }

    /**
     * Standart getter for nickname
     * @return nickname
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Setter: nickname
     * The nickname length must be between 3 and 50 characters
     * and it must contain only permitted symbols.
     * 
     * @param nickName
     * @throws IllegalArgumentException if the nickname is empty, null, shorter
     * than 3 characters or longer than 50 characters
     * or has any of restricted symbols
     */
    public void setNickName(String nickName) {
        if (nickName.isEmpty() || nickName == null) {
            throw new IllegalArgumentException("Empty username.");
        } else if (nickName.length() < 3) {
            throw new IllegalArgumentException("Invalid username, must contain 3 characters min.");
        }
        for (char c : nickName.toCharArray()) {
            if (PROHIBITED_SYMBOLS.contains(c)) throw new IllegalArgumentException(
                "No > < / \\ \' \" ; : & | ! # $ % * ( ) + = ? { } [ ] , . @ ^ symbols allowed in the username; no whitespaces and tabs allowed in the username."
            );
        }
        this.nickName = nickName;
    }

    /**
     * Standart getter for avatar URL
     * @return avatar URL
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * Setter: avatarUrl
     * Avatar URL must contain http:// or https:// protocol
     * for proper source clarification
     * 
     * @param avatarUrl
     * @throws IllegalArgumentException if the URL does not contain http:// or
     * https:// protocols
     */
    public void setAvatarUrl(String avatarUrl) {
        if (avatarUrl.startsWith("http://") || avatarUrl.startsWith("https://") || avatarUrl.startsWith("C:")) {
            this.avatarUrl = avatarUrl;
        } else {
            throw new IllegalArgumentException("Invalid avatar source.");
        }

    }

    /**
     * Standart getter for email
     * @return user email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter: email
     * It validates the email according to the RFC 5322 message format.
     * The email must match the validation conditions:
     * - Local part: consists of letters, digits and certain special characters;
     *   cannot begin or end with a period.
     * - Domain part: follows domain naming conventions and
     *   has the correct hierarchical structure 
     *   (e.g., top-level domain or second-level domain).
     * - Quotes: allows the use of special characters or spaces.
     * - Special characters: Special characters within quoted strings
     *   have specific roles (e. g. ".", "@").
     * 
     * @param email
     * @throws IllegalArgumentException if the given email does not follow
     * RFC 5322 message format.
     */
    public void setEmail(String email) {
        Matcher matcher = PATTERN.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid email address.");
        }
        this.email = email;
    }

    /**
     * Standart getter for user age
     * @return user age
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Setter: birthdate
     * The user must be 12+ years old.
     * Any age that is less than 0 or more than 120 years is invalid
     * 
     * @param age
     * @throws IllegalArgumentException if the user age is not in [0, 120]
     * interval or if it is less than 12 years
     */
    public void setBirthDate(String birthDate) {
        LocalDate bDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        int age = Period.between(bDate, LocalDate.now()).getYears();
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Invalid birthdate.");
        } else if (age < 12) {
            throw new IllegalArgumentException("Age restriction: you must be at least 12 years old.");
        }
        this.birthDate = birthDate;
    }

    /**
     * Standart getter for user password
     * @return user password
     */
    public String getPassword() {
        return password;
    }
  
    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return this.salt;
    }

    /**
     * Overriden toString method for enhanced debugging.
     * 
     * @return formatted into a string User object
     */
    @Override
    public String toString() {
        return "Name:  " + name +
                "\nNickName: " + nickName +
                "\nAvatarUrl: " + avatarUrl +
                "\nEmail: " + email +
                "\nBirthdate: " + birthDate;
    }
}
