package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.User;
import com.app.service.IUserService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;
	
	@GetMapping("/{userEmail}")
	public ResponseEntity<?> getUserDetails(@PathVariable String userEmail){
		User userByEmail = userService.getUserByEmail(userEmail);
		return ResponseEntity.status(HttpStatus.OK).body(userByEmail);
	}
}
