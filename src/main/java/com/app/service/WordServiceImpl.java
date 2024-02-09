package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.Word;
import com.app.entities.WordEntity;
import com.app.repository.UserRepository;
import com.app.repository.WordRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class WordServiceImpl implements IWordService {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private WordRepository wordRepo;
	@Override
	public List<Word> findByCrosswordId(long crosswordId) {
		List<WordEntity> words = wordRepo.findByCrosswordCrosswordId(crosswordId);
		return words.stream().map(word -> mapper.map(word, Word.class)).collect(Collectors.toList());
	}

}
