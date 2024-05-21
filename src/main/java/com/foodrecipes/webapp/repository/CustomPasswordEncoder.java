package com.foodrecipes.webapp.repository;

import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.foodrecipes.webapp.security.HashingUtility;

@Component
public class CustomPasswordEncoder implements PasswordEncoder {

    private String salt;

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        try {
            return HashingUtility.hashPassword(rawPassword.toString(), salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        try {
            String hashedPassword = HashingUtility.hashPassword(rawPassword.toString(), salt);
            return hashedPassword.equals(encodedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }
}
