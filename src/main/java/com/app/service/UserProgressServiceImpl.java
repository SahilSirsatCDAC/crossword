package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.app.controllers.IUserProgressService;
import com.app.dto.UserProgress;
import com.app.entities.CrosswordEntity;
import com.app.entities.UserEntity;
import com.app.entities.UserProgressEntity;
import com.app.entities.WordEntity;
import com.app.repository.CrosswordRepository;
import com.app.repository.UserProgressRepository;
import com.app.repository.UserRepository;
import com.app.repository.WordRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
@Service
@Transactional
@Slf4j
public class UserProgressServiceImpl implements IUserProgressService {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CrosswordRepository crosswordRepo;
	@Autowired
	private WordRepository wordRepo;
	@Autowired
	private UserProgressRepository userProgressRepo;
	@Override
	public List<UserProgressEntity> saveUserProgress(ArrayList<Integer> wordIds, Long crosswordId) {
//		log.info(getClass().getName()+" in addBuilding "+userProgress);
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String username = (String) authentication.getPrincipal();
		Optional<UserEntity> userEntity = userRepo.findByEmail(username);
        UserProgress userProgress= new UserProgress();
		//		if (userEntity.isEmpty() || userEntity.get().getContactNo() == null || userEntity.get().getUserAddr() == null) {
//			log.info(getClass().getName()+" userEntity is empty or user has not updated contact details and address");
//			return null;
//		}
//		AddressEntity a = addrRepo.save(mapper.map(building.getBuildingAddr(), AddressEntity.class));
        List<UserProgressEntity> userProgresses = new ArrayList<UserProgressEntity>();
		for (Integer wordId : wordIds) {
			UserProgressEntity u = mapper.map(userProgress, UserProgressEntity.class);
			u.setUser(userEntity.get());
			u.setCrossWord(crosswordRepo.findByCrosswordId(crosswordId));
			u.setWord(wordRepo.findByWordId(Long.valueOf((int)wordId)));
			userProgresses.add(userProgressRepo.save(u));
		}
		log.info(getClass().getName()+" adding building to database");
		return userProgresses;
	}

}
