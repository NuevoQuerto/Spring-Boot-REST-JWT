package com.dans.rest;

import com.dans.rest.model.entity.User;
import com.dans.rest.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(UserService userService) {
		return args -> {
			User user = new User();
			user.setUsername("Test");
			user.setPassword("Test12345678");
			userService.createUser(user);
		};
	}
}
