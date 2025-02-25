package com.vdehoyos.tmbackend.application.usecase;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

import com.vdehoyos.tmbackend.application.dto.BaseTaskDTO;
import com.vdehoyos.tmbackend.application.dto.UpdateTaskDTO;
import com.vdehoyos.tmbackend.domain.events.TaskStatusChangedEvent;
import com.vdehoyos.tmbackend.domain.model.Task;
import com.vdehoyos.tmbackend.domain.model.User;
import com.vdehoyos.tmbackend.domain.ports.NotificationSender;
import com.vdehoyos.tmbackend.domain.repository.TaskRepository;
import com.vdehoyos.tmbackend.domain.repository.UserRepository;
import com.vdehoyos.tmbackend.infraestructure.factory.NotificationFactory;
import com.vdehoyos.tmbackend.presentation.mapper.TaskMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateTaskUseCase {

	private final UserRepository userRepository;
	private final TaskRepository taskRepository;
	private final TaskMapper taskMapper;
	
	private final ApplicationEventPublisher eventPublisher;
    private final NotificationFactory notificationFactory;

	@Transactional
	public Task execute(Long id, UpdateTaskDTO dto) throws Exception {
		try {
			Task task = taskRepository.findById(id);
			taskMapper.updateTaskFromDto(dto, task);
			
			if(UpdateTaskUseCase.userValidateFields(dto)) {
				User user = userRepository.findByEmail(dto.getUser().getEmail());
				task.setUser(user);
			}
			
			task.setSupervisor(null);
				
			Task taskUpdated = taskRepository.saveUpd(task);
			if ("Completada".equalsIgnoreCase(taskUpdated.getStatus())) {
				eventPublisher.publishEvent(new TaskStatusChangedEvent(this, taskUpdated));
				NotificationSender emailNotifier = notificationFactory.createNotificationSender("email");
				NotificationSender websocketNotifier = notificationFactory.createNotificationSender("websocket");
				
				emailNotifier.sendNotification(task);
		        websocketNotifier.sendNotification(task);
			}
			
			return taskUpdated;
			
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	private static boolean userValidateFields(BaseTaskDTO dto) {
		if (dto.getUser() != null && !dto.getUser().getEmail().isBlank()) {
			return true;
		}
		return false;
	}

	public static boolean supervisorFieldsValid(BaseTaskDTO dto) {
		return dto != null && dto.getSupervisor() != null && dto.getSupervisor().getEmail() != null
				&& !dto.getSupervisor().getEmail().isBlank();
	}

}
