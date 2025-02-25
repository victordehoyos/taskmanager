package com.vdehoyos.tmbackend.domain.events;

import org.springframework.context.ApplicationEvent;

import com.vdehoyos.tmbackend.domain.model.Task;

import lombok.Getter;

public class TaskStatusChangedEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	@Getter
	private final Task task;
	
	public TaskStatusChangedEvent(Object source, Task task) {
		super(source);
		this.task = task;
	}

}
