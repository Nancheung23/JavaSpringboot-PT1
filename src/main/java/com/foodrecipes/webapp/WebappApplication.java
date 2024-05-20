package com.foodrecipes.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.foodrecipes.webapp.config.WebAppProperties;

@SpringBootApplication
@EnableConfigurationProperties(WebAppProperties.class)
public class WebappApplication {

	/**
	 * Main func for set up backend program
	 */
	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
	}

}
