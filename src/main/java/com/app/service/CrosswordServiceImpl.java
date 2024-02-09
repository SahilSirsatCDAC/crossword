package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.dto.Crossword;
import com.app.dto.User;
import com.app.repository.CrosswordRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class CrosswordServiceImpl implements ICrosswordService {
	@Autowired
	CrosswordRepository crosswordRepo;
	@Autowired
	private ModelMapper mapper;
	@Override
	public List<Crossword> getPaginatedAllCrosswordsList(Pageable pageable) {
		return crosswordRepo.findAll(pageable).stream().map(crossword -> mapper.map(crossword, Crossword.class)).collect(Collectors.toList());
	}

}
