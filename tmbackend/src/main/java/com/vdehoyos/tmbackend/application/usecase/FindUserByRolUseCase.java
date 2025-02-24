package com.vdehoyos.tmbackend.application.usecase;

import java.util.List;

import com.vdehoyos.tmbackend.domain.model.Role;
import com.vdehoyos.tmbackend.domain.model.User;
import com.vdehoyos.tmbackend.domain.repository.RoleRepository;
import com.vdehoyos.tmbackend.domain.repository.UserRepository;

public class FindUserByRolUseCase {
	
	private final UserRepository repository;
	private final RoleRepository roleRepository;

	public FindUserByRolUseCase(UserRepository repository, RoleRepository roleRepository) {
		this.repository = repository;
		this.roleRepository = roleRepository;
	}
	
	public List<User> execute(Long roleId) {
		Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new RuntimeException("El rol no existe"));
		
		List<User> users = repository.findByRole(role);

        return users;
    }

}
