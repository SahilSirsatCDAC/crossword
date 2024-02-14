package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.ICrosswordService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/crossword")
@Slf4j
public class CrosswordController {
	@Autowired
	private ICrosswordService crosswordService;
	
	@GetMapping("/{pageNo}/{numberOfElementsPerPage}")
	public ResponseEntity<?> getPaginatedAllCrosswordsList(@PathVariable Integer pageNo,@PathVariable Integer numberOfElementsPerPage) {
		log.info(getClass().getName()+" in getPaginatedAllCrosswordsList");
		Pageable customPageable = PageRequest.of(pageNo, numberOfElementsPerPage);
		return ResponseEntity.ok(crosswordService.getPaginatedAllCrosswordsList(customPageable));
	}
}
