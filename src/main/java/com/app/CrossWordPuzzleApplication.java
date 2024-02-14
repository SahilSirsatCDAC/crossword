package com.app;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.app.dto.UserRole;
import com.app.entities.Role;
import com.app.repository.RoleRepository;
import com.app.service.IUserService;

@SpringBootApplication
//@ComponentScan
public class CrossWordPuzzleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrossWordPuzzleApplication.class, args);
	}
	
	@Bean
	public ModelMapper mapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
//	@Bean
//	CommandLineRunner run(RoleRepository roleRepo, IUserService userService) {
//
//		List<UserRole> dbRoles = roleRepo.findAll().stream().map(r -> r.getRoleName()).collect(Collectors.toList());
//		List<UserRole> allRoles = Arrays.asList(UserRole.values()).stream().filter(myrole -> !dbRoles.contains(myrole))
//				.collect(Collectors.toList());
//		allRoles.forEach(myrole -> roleRepo.save(new Role(myrole)));
//		return null;
//	}

}
//(exclude={org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
