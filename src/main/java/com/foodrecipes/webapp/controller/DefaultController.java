package com.foodrecipes.webapp.controller;

import com.foodrecipes.webapp.config.WebAppProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    private final WebAppProperties webAppProperties;

    public DefaultController(WebAppProperties webAppProperties) {
        this.webAppProperties = webAppProperties;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("title", webAppProperties.getTitle());
        model.addAttribute("description", webAppProperties.getDescription());
        model.addAttribute("text", webAppProperties.getText());
        return "home";
    }

}
