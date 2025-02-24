package com.vdehoyos.tmbackend.infraestructure.persistence.adapter;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vdehoyos.tmbackend.domain.model.Task;
import com.vdehoyos.tmbackend.domain.model.User;
import com.vdehoyos.tmbackend.domain.repository.TaskRepository;
import com.vdehoyos.tmbackend.infraestructure.persistence.entity.RoleEntity;
import com.vdehoyos.tmbackend.infraestructure.persistence.entity.TaskEntity;
import com.vdehoyos.tmbackend.infraestructure.persistence.entity.UserEntity;
import com.vdehoyos.tmbackend.infraestructure.persistence.mapper.TaskEntityMapper;
import com.vdehoyos.tmbackend.infraestructure.persistence.mapper.UserEntityMapper;
import com.vdehoyos.tmbackend.infraestructure.persistence.repository.JpaTaskRepository;
import com.vdehoyos.tmbackend.infraestructure.persistence.repository.JpaUserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class TaskRepositoryAdapter implements TaskRepository {
	
	private final JpaTaskRepository jpaTaskRepository;
	private final JpaUserRepository jpaUserRepository;
	private final UserEntityMapper userEntityMapper;
	private final TaskEntityMapper taskEntityMapper;
	
	@PersistenceContext
	private EntityManager entityManager;
	

	@Override
	public Task saveUpd(Task task) {
		TaskEntity entity = taskEntityMapper.toEntity(task);
		
		if(task.getUser() != null && task.getUser().getEmail() != null) {
			UserEntity userEntity = entityManager.getReference(UserEntity.class, task.getUser().getId()); //jpaUserRepository.findByEmail(task.getUser().getEmail());
			if(!entityManager.contains(userEntity)) {
				System.out.println("No gestionado");
				userEntity = entityManager.merge(userEntity);
				System.out.println(entityManager.contains(userEntity));
			}
			RoleEntity roleEntity = entityManager.getReference(RoleEntity.class, task.getUser().getRole().getId());
			if(!entityManager.contains(roleEntity)) {
				System.out.println("No gestionado");
				roleEntity = entityManager.merge(roleEntity);
				System.out.println(entityManager.contains(roleEntity));
			}
			entity.setSupervisor(null);
			userEntity.setRole(roleEntity);
			entity.setUser(userEntity);
		}
		
		/*if(task.getSupervisor() != null && task.getSupervisor().getEmail() != null) {
			UserEntity userEntity = jpaUserRepository.findByEmail(task.getSupervisor().getEmail());
			//entity.setSupervisor(userEntity);
		}*/
		
		return taskEntityMapper.toDomain(jpaTaskRepository.save(entity));
	}


	@Override
	public List<Task> findByStatus(String status) {
		List<TaskEntity> tasks = jpaTaskRepository.findByStatus(status);
		return taskEntityMapper.toDomainList(tasks);
	}


	@Override
	public List<Task> findByUser(User user) {
		List<TaskEntity> tasks = jpaTaskRepository.findByUser(userEntityMapper.toEntity(user));
		return taskEntityMapper.toDomainList(tasks);
	}


	@Override
	public Task findById(Long id) {
		return taskEntityMapper.toDomain(jpaTaskRepository.findById(id).orElseThrow(() -> new RuntimeException("La tarea consultada no existe")));
	}


	@Override
	public List<Task> findByStatusAndAssignedUser(String status, String email) {
		UserEntity userEntity = jpaUserRepository.findByEmail(email);
		return taskEntityMapper.toDomainList(jpaTaskRepository.findByStatusAndUser(status, userEntity));
	}

}
