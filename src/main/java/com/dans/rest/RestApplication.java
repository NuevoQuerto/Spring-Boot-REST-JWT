package com.dans.rest;

import com.dans.rest.model.entity.User;
import com.dans.rest.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class RestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(UserService userService, PasswordEncoder passwordEncoder) {
		return args -> {
			UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
					.username("Test1")
					.password("Test12345678")
					.passwordEncoder(passwordEncoder::encode)
					.build();

			User user = new User();
			user.setUsername(userDetails.getUsername());
			user.setPassword(userDetails.getPassword());
			userService.createUser(user);
		};
	}
}
