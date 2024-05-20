package com.foodrecipes.webapp.controller;

import com.foodrecipes.webapp.security.JwtUtility;
import com.foodrecipes.webapp.service.UserConversionService;
import com.foodrecipes.webapp.dto.AuthRequest;
import com.foodrecipes.webapp.dto.AuthResponse;

import javax.management.InvalidAttributeValueException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private UserConversionService userConversionService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(), authRequest.getPassword()));
        } catch (Exception e) {
            throw new InvalidAttributeValueException("INVALID_CREDENTIALS");
        }

        final UserDetails userDetails = userConversionService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtility.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
