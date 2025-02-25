package com.vdehoyos.tmbackend.application.usecase;

import java.util.List;

import com.vdehoyos.tmbackend.domain.model.User;
import com.vdehoyos.tmbackend.domain.repository.UserRepository;

public class FindAllUser {
	
	private final UserRepository repository;

	public FindAllUser(UserRepository repository) {
		this.repository = repository;
	}
	
	public List<User> execute() {
		
		List<User> users = repository.findAll();

        return users;
    }
	
}
