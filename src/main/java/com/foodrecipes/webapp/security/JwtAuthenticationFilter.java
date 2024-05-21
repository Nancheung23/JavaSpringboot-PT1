package com.foodrecipes.webapp.security;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.foodrecipes.webapp.service.UserConversionService;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // inject Jwt util class
    @Autowired
    private JwtUtility jwtUtility;

    // inject User Service
    @Autowired
    private UserConversionService userConversionService;

    /**
     * Filter authorization method, focus on header. 
     * @param HttpServletRequest request,
     * @param HttpServletResponse response,
     * @param FilterChain filterChain
     */

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // get Authorization header
        final String authorizationHeader = request.getHeader("Authorization");
        String jwt = null;
        String username = null;

        // Authorization must start with "Bearer"
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtility.extractUsername(jwt);
        }

        // if username exists but no auth info
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                // load from database 
                UserDetails userDetails = userConversionService.loadUserByUsername(username);
                // check valid jwt
                boolean flag = jwtUtility.validateToken(jwt, userDetails);
                if (flag) {
                    // UsernamePasswordAuthenticationToken
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    // Set auth info details
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // Set auth token into SecurityContextHolder
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            } catch (Exception e) {
                // if User doesn't exist
                throw new NoSuchElementException("No Such User");
            }
        }
        // continue filter
        filterChain.doFilter(request, response);
    }
}
