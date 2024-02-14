package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.UserProgressEntity;


public interface UserProgressRepository extends JpaRepository<UserProgressEntity,Long> {
//	@Query("select w from WordEntity w where w.crossword.crosswordId = ?1")
	List<UserProgressEntity> findByUserId(Long userId);
}
