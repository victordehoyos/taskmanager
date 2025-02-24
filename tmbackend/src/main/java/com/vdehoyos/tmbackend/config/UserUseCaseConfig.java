package com.vdehoyos.tmbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vdehoyos.tmbackend.application.usecase.CreateUserUseCase;
import com.vdehoyos.tmbackend.application.usecase.FindAllRolesUseCase;
import com.vdehoyos.tmbackend.application.usecase.FindUserByRolUseCase;
import com.vdehoyos.tmbackend.domain.repository.RoleRepository;
import com.vdehoyos.tmbackend.domain.repository.UserRepository;

@Configuration
public class UserUseCaseConfig {

	@Bean
	public CreateUserUseCase createUserUseCase(UserRepository userRepository, RoleRepository roleRepository) {
		return new CreateUserUseCase(userRepository, roleRepository);
	}
	
	@Bean
	public FindUserByRolUseCase findUserByRolUseCase(UserRepository userRepository, RoleRepository roleRepository) {
		return new FindUserByRolUseCase(userRepository, roleRepository);
	}
	
	@Bean
	public FindAllRolesUseCase findAllRolesUseCase(RoleRepository roleRepository) {
		return new FindAllRolesUseCase(roleRepository);
	}
	
}
 