package com.vdehoyos.tmbackend.domain.repository;

import java.util.List;
import java.util.Optional;

import com.vdehoyos.tmbackend.domain.model.Role;
import com.vdehoyos.tmbackend.domain.model.User;


public interface UserRepository {
	
	User save(User user);
	User update(User user);
	Optional<User> findById(Long id);
	List<User> findByRole(Role role);
	List<User> findAll();
	User findByEmail(String email);

}
