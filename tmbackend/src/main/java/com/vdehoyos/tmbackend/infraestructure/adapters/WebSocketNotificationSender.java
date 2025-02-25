package com.vdehoyos.tmbackend.infraestructure.adapters;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.vdehoyos.tmbackend.domain.model.Task;
import com.vdehoyos.tmbackend.domain.ports.NotificationSender;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WebSocketNotificationSender implements NotificationSender {
	
	private final SimpMessagingTemplate messagingTemplate;

	@Override
	public void sendNotification(Task task) {
		messagingTemplate.convertAndSend("/topic/tasks", "La tarea (" + task.getTitle() + ") ha sido completada");
	}

}
