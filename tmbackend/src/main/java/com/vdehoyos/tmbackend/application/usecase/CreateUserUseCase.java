package com.vdehoyos.tmbackend.application.usecase;

import java.util.Date;

import com.vdehoyos.tmbackend.application.dto.CreateUserDTO;
import com.vdehoyos.tmbackend.domain.model.Role;
import com.vdehoyos.tmbackend.domain.model.User;
import com.vdehoyos.tmbackend.domain.repository.RoleRepository;
import com.vdehoyos.tmbackend.domain.repository.UserRepository;
import com.vdehoyos.tmbackend.presentation.mapper.UserMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateUserUseCase {
	
	private final UserRepository repository;
	private final RoleRepository roleRepository;
	private final UserMapper userMapper;
	
	public User execute(CreateUserDTO dto) {
		Role role = roleRepository.findById(dto.getRoleId())
				.orElseThrow(() -> new RuntimeException("El rol no existe"));
		
		User user = userMapper.toDomain(dto);
        
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        user.setRole(role);

        return repository.save(user);
    }

}
