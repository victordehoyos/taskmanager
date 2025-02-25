package com.vdehoyos.tmbackend.application.usecase;

import java.util.List;

import com.vdehoyos.tmbackend.domain.model.Role;
import com.vdehoyos.tmbackend.domain.repository.RoleRepository;

public class FindAllRolesUseCase {
	
	private final RoleRepository roleRepository;

	public FindAllRolesUseCase(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public List<Role> execute() {

		List<Role> roles = roleRepository.findAll();

        return roles;
    }

}
