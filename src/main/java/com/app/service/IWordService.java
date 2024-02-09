package com.app.service;

import java.util.List;

import com.app.dto.Word;

public interface IWordService {
	List<Word> findByCrosswordId(long crosswordId);
}
