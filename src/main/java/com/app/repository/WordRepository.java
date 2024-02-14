package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.WordEntity;


public interface WordRepository extends JpaRepository<WordEntity,Long> {
//	@Query("select w from WordEntity w where w.crossword.crosswordId = ?1")
	List<WordEntity> findByCrosswordCrosswordId(Long crosswordId);

	WordEntity findByWordId(Long wordId);
}
