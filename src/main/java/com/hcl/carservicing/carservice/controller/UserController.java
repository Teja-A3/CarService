package com.hcl.carservicing.carservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.carservicing.carservice.model.AppUser;
import com.hcl.carservicing.carservice.service.UserService;

import jakarta.validation.Valid;

@RestController

@RequestMapping("/api/users")

public class UserController {

	private final UserService userService;

	public UserController (UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@Valid @RequestBody AppUser user) {
		userService.register(user);
		return ResponseEntity.status (HttpStatus.CREATED).body("User registered Successfully");

	}

	@PostMapping("/login")
	public ResponseEntity<String> login (@RequestParam String userId,@RequestParam String password) {
		userService.login (userId, password);
		return ResponseEntity.ok("Login Successfully");

	}
}