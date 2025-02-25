package com.vdehoyos.tmbackend.application.usecase;

import com.vdehoyos.tmbackend.domain.model.User;
import com.vdehoyos.tmbackend.domain.repository.UserRepository;

public class FindUserByEmailUseCase {
	
	private final UserRepository repository;
	
	public FindUserByEmailUseCase(UserRepository repository) {
		this.repository = repository;
	}
	
	public User execute(String email) {
		
		User user = repository.findByEmail(email);

        return user;
    }

}
