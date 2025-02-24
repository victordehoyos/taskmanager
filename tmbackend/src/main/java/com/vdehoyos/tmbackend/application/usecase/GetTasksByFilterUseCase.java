package com.vdehoyos.tmbackend.application.usecase;

import java.util.ArrayList;
import java.util.List;

import com.vdehoyos.tmbackend.domain.model.Task;
import com.vdehoyos.tmbackend.domain.model.User;
import com.vdehoyos.tmbackend.domain.repository.TaskRepository;
import com.vdehoyos.tmbackend.domain.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetTasksByFilterUseCase {
	
	private final TaskRepository taskRepository;
	private final UserRepository userRepository;
	
	public List<Task> execute(String status, String email) {
		
		if (status != null && email != null) {
            return taskRepository.findByStatusAndAssignedUser(status, email);
        } else if (status != null) {
            return taskRepository.findByStatus(status);
        } else if (email != null) {
        	User user = userRepository.findByEmail(email);
            return taskRepository.findByUser(user);
        } else {
            return new ArrayList<>();
        }
		
	}
}
