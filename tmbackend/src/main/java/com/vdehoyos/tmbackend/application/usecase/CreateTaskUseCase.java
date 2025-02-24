package com.vdehoyos.tmbackend.application.usecase;

import java.util.Objects;

import org.springframework.transaction.annotation.Transactional;

import com.vdehoyos.tmbackend.application.dto.BaseTaskDTO;
import com.vdehoyos.tmbackend.domain.model.Task;
import com.vdehoyos.tmbackend.domain.model.User;
import com.vdehoyos.tmbackend.domain.repository.TaskRepository;
import com.vdehoyos.tmbackend.domain.repository.UserRepository;
import com.vdehoyos.tmbackend.presentation.mapper.TaskMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTaskUseCase {
	
	private final UserRepository userRepository;
	private final TaskRepository taskRepository;
	private final TaskMapper taskMapper;

	@Transactional 
	public Task execute(BaseTaskDTO dto) {
		CreateTaskUseCase.userValidateFields(dto);
		User user = userRepository.findByEmail(dto.getUser().getEmail());
		Task task = taskMapper.toDomainBase(dto);
		task.setUser(user);
		if(CreateTaskUseCase.supervisorFieldsValid(dto)) {
			User supervisor = userRepository.findByEmail(dto.getSupervisor().getEmail());
			task.setSupervisor(supervisor);
		}
		
		return taskRepository.saveUpd(task);
	}
	
	private static void userValidateFields(BaseTaskDTO dto) {
	    Objects.requireNonNull(dto, "Debe enviar la información requerida");
	    Objects.requireNonNull(dto.getUser(), "Debe proporcionar la información del usuario a asignar");
	    Objects.requireNonNull(dto.getUser().getEmail(), "Debe informarse el email del usuario");

	    if (dto.getUser().getEmail().isBlank()) {
	        throw new IllegalArgumentException("Debe informarse el email del usuario");
	    }
	}
	
	public static boolean supervisorFieldsValid(BaseTaskDTO dto) {
	    return dto != null &&
	           dto.getSupervisor() != null &&
	           dto.getSupervisor().getEmail() != null &&
	           !dto.getSupervisor().getEmail().isBlank();
	}
	
}
