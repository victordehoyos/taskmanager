package com.vdehoyos.tmbackend.infraestructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vdehoyos.tmbackend.infraestructure.persistence.entity.TaskEntity;
import com.vdehoyos.tmbackend.infraestructure.persistence.entity.UserEntity;

public interface JpaTaskRepository extends JpaRepository<TaskEntity, Long> {

	List<TaskEntity> findByStatus(String status);
	List<TaskEntity> findByUser(UserEntity user);
	List<TaskEntity> findByStatusAndUser(String status, UserEntity user);
	
}
