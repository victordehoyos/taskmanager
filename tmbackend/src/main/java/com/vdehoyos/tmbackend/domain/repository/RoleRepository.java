package com.vdehoyos.tmbackend.domain.repository;

import java.util.List;
import java.util.Optional;

import com.vdehoyos.tmbackend.domain.model.Role;

public interface RoleRepository {
	
	Optional<Role> findById(Long id);
	Optional<Role> findByName(String name);
	List<Role> findAll();

}
