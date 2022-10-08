package com.firstProject.springBatch;

import com.firstProject.springBatch.domain.AppUser;
import com.firstProject.springBatch.domain.Role;
import com.firstProject.springBatch.service.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class SpringBatchApplication {

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchApplication.class, args);
	}

	@Bean
	CommandLineRunner run(AppUserService userService) {
		String encoded_pass = passwordEncoder().encode("admin");
		return args -> {
			Role r1 = userService.saveRole(new Role("ROLE_USER"));
			userService.saveUser(new AppUser("admin", encoded_pass, Arrays.asList(r1)));


		};
	}
}