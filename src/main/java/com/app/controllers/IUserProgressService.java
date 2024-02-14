package com.app.controllers;

import java.util.ArrayList;
import java.util.List;

import com.app.dto.UserProgress;
import com.app.entities.UserProgressEntity;

import jakarta.validation.Valid;

public interface IUserProgressService {

//	UserProgress saveUserProgress(@Valid UserProgress userProgress);

	List<UserProgressEntity> saveUserProgress(ArrayList<Integer> wordIds, Long crosswordId);

}
