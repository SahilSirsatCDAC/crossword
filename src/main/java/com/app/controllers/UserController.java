package com.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.User;
import com.app.dto.UserProgress;
import com.app.entities.UserProgressEntity;
import com.app.service.IUserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserProgressService userProgressService;
	
	@GetMapping("/email/{userEmail}")
	public ResponseEntity<?> getUserByEmail(@PathVariable String userEmail){
		User userByEmail = userService.getUserByEmail(userEmail);
		return ResponseEntity.status(HttpStatus.OK).body(userByEmail);
		
	}
	@GetMapping("/id/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable Integer userId){
		User userById = userService.getUserById(userId);
		return ResponseEntity.status(HttpStatus.OK).body(userById);
	}
	
	@PostMapping("/progress/save")
	public ResponseEntity<?> saveUserProgress(@RequestBody Map<String, Object> requestMap) {
		ArrayList<Integer> wordIds = (ArrayList<Integer>) requestMap.get("wordIds");
		Long crosswordId = Long.valueOf((int)requestMap.get("crosswordId")) ;
//		log.info(getClass().getName()+" in addBuilding" + userProgress);
//		List<UserProgressEntity> b = userProgressService.saveUserProgress(wordIds, crosswordId);
//		for (UserProgressEntity userProgressEntity : b) {
//			if (userProgressEntity==null) return  ResponseEntity.status(HttpStatus.CONFLICT).body("Progress not saved!");
//		}
		return  ResponseEntity.status(HttpStatus.OK).body(new UserProgressEntity());
	}
}
