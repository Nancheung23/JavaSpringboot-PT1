package com.foodrecipes.webapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultRouter {
    private static final String TEXT = "Java SpringBoot project for practical training 1";

    @GetMapping("/")
    public ResponseEntity<String> sendDefaultText() {
        return ResponseEntity.ok(TEXT);
    }
}
