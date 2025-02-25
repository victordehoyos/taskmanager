package com.vdehoyos.tmbackend.domain.ports;

import com.vdehoyos.tmbackend.domain.model.Task;

public interface NotificationSender {
	
	void sendNotification(Task task);

}
