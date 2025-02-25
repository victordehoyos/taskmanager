package com.vdehoyos.tmbackend.infraestructure.factory;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.vdehoyos.tmbackend.domain.ports.NotificationSender;
import com.vdehoyos.tmbackend.infraestructure.adapters.EmailNotificationSender;
import com.vdehoyos.tmbackend.infraestructure.adapters.WebSocketNotificationSender;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class NotificationFactory {
	
	private final SimpMessagingTemplate messagingTemplate;
    private final JavaMailSender mailSender;
	
	public NotificationSender createNotificationSender(String type) {
		if ("websocket".equalsIgnoreCase(type)) {
            return new WebSocketNotificationSender(messagingTemplate);
        } else if ("email".equalsIgnoreCase(type)) {
            return new EmailNotificationSender(mailSender);
        }
        throw new IllegalArgumentException("Typo de notificación desconocida: " + type);
	}
}
