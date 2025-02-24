package com.vdehoyos.tmbackend.domain.repository;

import java.util.List;

import com.vdehoyos.tmbackend.domain.model.Task;
import com.vdehoyos.tmbackend.domain.model.User;

public interface TaskRepository {

	Task saveUpd(Task user);
	Task findById(Long id);
	List<Task> findByStatus(String status);
	List<Task> findByUser(User user);
	List<Task> findByStatusAndAssignedUser(String status, String email);
	
}
