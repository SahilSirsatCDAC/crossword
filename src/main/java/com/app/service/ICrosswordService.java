package com.app.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.app.dto.Crossword;

public interface ICrosswordService {
	List<Crossword> getPaginatedAllCrosswordsList(Pageable pageable);
}
