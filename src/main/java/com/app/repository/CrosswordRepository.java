package com.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.app.entities.CrosswordEntity;

public interface CrosswordRepository extends PagingAndSortingRepository<CrosswordEntity, Long> {
	Page<CrosswordEntity> findAll(Pageable pageable);

	CrosswordEntity findByCrosswordId(Long crosswordId);
}
