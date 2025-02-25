package com.vdehoyos.tmbackend.application.usecase;

import com.vdehoyos.tmbackend.domain.model.Task;

public interface TaskObserver {

	void onTaskStatusChanged(Task task);
	
}
