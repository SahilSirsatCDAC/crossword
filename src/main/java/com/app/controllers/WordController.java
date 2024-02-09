package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.IWordService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/word")
@Slf4j
public class WordController {
	@Autowired
	private IWordService wordService;
	
	@GetMapping("/{crosswordId}")
	public ResponseEntity<?> findByCrosswordId(@PathVariable Long crosswordId){
		return ResponseEntity.ok(wordService.findByCrosswordId(crosswordId));
	}
}
