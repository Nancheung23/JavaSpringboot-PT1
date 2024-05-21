package com.foodrecipes.webapp.controller;

import com.foodrecipes.webapp.security.JwtUtility;
import com.foodrecipes.webapp.service.UserConversionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foodrecipes.webapp.dto.AuthRequest;
import com.foodrecipes.webapp.dto.AuthResponse;
import com.foodrecipes.webapp.repository.CustomPasswordEncoder;
import com.foodrecipes.webapp.repository.UserRepository;

import java.util.Optional;

import javax.management.InvalidAttributeValueException;

import com.foodrecipes.webapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    // generate jwt token
    private JwtUtility jwtUtility;

    private UserConversionService userConversionService;

    private UserRepository userRepository;

    // customized passwordEncoder
    private CustomPasswordEncoder passwordEncoder;

    // logger for checking authentication
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    /**
     * No-Arg
     */
    public AuthController() {
    }

    /**
     * Constructor
     * 
     * @param authenticationManager
     * @param jwtUtility
     * @param userConversionService
     * @param userRepository
     * @param passwordEncoder
     */
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtility jwtUtility,
            UserConversionService userConversionService, UserRepository userRepository,
            CustomPasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtility = jwtUtility;
        this.userConversionService = userConversionService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Key Mapping, post username and password to auth,
     * recieve jwt token in AuthResponse object
     * 
     * @param authRequest
     * @return
     * @throws InvalidAttributeValueException
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest)
            throws InvalidAttributeValueException {
        try {
            String rawPassword = authRequest.getPassword();
            Optional<User> userOptional = userRepository.findByName(authRequest.getUsername());

            // Check if user exists
            if (!userOptional.isPresent()) {
                throw new InvalidAttributeValueException("INVALID_USERNAME");
            }

            // Check if password is correct
            User user = userOptional.get();
            String salt = user.getSalt();
            passwordEncoder.setSalt(salt);
            String hashedPassword = passwordEncoder.encode(rawPassword);
            // for development
            logger.debug("Input password: {}\nEncode result: {}\n", hashedPassword, user.getPassword());

            // Check if password is correct
            if (passwordEncoder.matches(rawPassword, user.getPassword())) {
                // Authenticate user
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(), rawPassword);
                authenticationManager.authenticate(authenticationToken);

                // Load user details and generate JWT
                UserDetails userDetails = userConversionService.loadUserByUsername(authRequest.getUsername());
                String jwt = jwtUtility.generateToken(userDetails);
                return ResponseEntity.ok(new AuthResponse(jwt));
            } else {
                throw new InvalidAttributeValueException("INVALID_PASSWORD");
            }
        } catch (Exception e) {
            // Log specific exception
            logger.error("Invalid credentials: " + e.getMessage(), e);
            throw new InvalidAttributeValueException("INVALID_CREDENTIALS");
        }
    }

}
