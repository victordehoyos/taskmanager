package com.vdehoyos.tmbackend.infraestructure.adapters;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.vdehoyos.tmbackend.domain.model.Task;
import com.vdehoyos.tmbackend.domain.ports.NotificationSender;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailNotificationSender implements NotificationSender {
	
	private final JavaMailSender mailSender;

	@Override
	public void sendNotification(Task task) {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(task.getUser().getEmail());
        message.setSubject("Actualización de Tarea");
        message.setText("La tarea ( " + task.getTitle() + " ) ha sido completada");
        mailSender.send(message);
	}

}
