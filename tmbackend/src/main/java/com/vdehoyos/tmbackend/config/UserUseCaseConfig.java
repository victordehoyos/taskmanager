package com.vdehoyos.tmbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vdehoyos.tmbackend.application.usecase.CreateTaskUseCase;
import com.vdehoyos.tmbackend.application.usecase.CreateUserUseCase;
import com.vdehoyos.tmbackend.application.usecase.FindAllRolesUseCase;
import com.vdehoyos.tmbackend.application.usecase.FindUserByRolUseCase;
import com.vdehoyos.tmbackend.application.usecase.GetTasksByFilterUseCase;
import com.vdehoyos.tmbackend.application.usecase.UpdateTaskUseCase;
import com.vdehoyos.tmbackend.domain.repository.RoleRepository;
import com.vdehoyos.tmbackend.domain.repository.TaskRepository;
import com.vdehoyos.tmbackend.domain.repository.UserRepository;
import com.vdehoyos.tmbackend.presentation.mapper.TaskMapper;
import com.vdehoyos.tmbackend.presentation.mapper.UserMapper;

@Configuration
public class UserUseCaseConfig {
 
	@Bean 
	public CreateUserUseCase createUserUseCase(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
		return new CreateUserUseCase(userRepository, roleRepository, userMapper);
	} 
	
	@Bean
	public FindUserByRolUseCase findUserByRolUseCase(UserRepository userRepository, RoleRepository roleRepository) {
		return new FindUserByRolUseCase(userRepository, roleRepository);
	}
	
	@Bean
	public FindAllRolesUseCase findAllRolesUseCase(RoleRepository roleRepository) {
		return new FindAllRolesUseCase(roleRepository);
	}
	
	@Bean
	public CreateTaskUseCase createTaskUseCase(UserRepository userRepository, TaskRepository taskRepository, TaskMapper taskMapper) {
		return new CreateTaskUseCase(userRepository, taskRepository, taskMapper);
	}
	
	@Bean
	public UpdateTaskUseCase updateTaskUseCase(UserRepository userRepository, TaskRepository taskRepository, TaskMapper taskMapper) {
		return new UpdateTaskUseCase(userRepository, taskRepository, taskMapper);
	}
	
	@Bean
	public GetTasksByFilterUseCase getTasksByFilterUseCase(UserRepository userRepository, TaskRepository taskRepository) {
		return new GetTasksByFilterUseCase(taskRepository, userRepository);
	}
	
}
 