package com.vdehoyos.tmbackend.presentation.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vdehoyos.tmbackend.application.dto.BaseTaskDTO;
import com.vdehoyos.tmbackend.application.dto.UpdateTaskDTO;
import com.vdehoyos.tmbackend.application.usecase.CreateTaskUseCase;
import com.vdehoyos.tmbackend.application.usecase.GetTasksByFilterUseCase;
import com.vdehoyos.tmbackend.application.usecase.UpdateTaskUseCase;
import com.vdehoyos.tmbackend.domain.model.Task;
import com.vdehoyos.tmbackend.presentation.mapper.TaskMapper;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {
	
	private final CreateTaskUseCase createTaskUseCase;
	private final UpdateTaskUseCase updateTaskUseCase;
	private final TaskMapper taskMapper; 
	private final GetTasksByFilterUseCase getTasksByFilterUseCase;

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> register(@RequestBody BaseTaskDTO dto) {
		try {
			Task task = createTaskUseCase.execute(dto);
			return ResponseEntity.ok(taskMapper.toDTOBase(task));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", e.getMessage()));
		}
	}
	
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UpdateTaskDTO dto) {
		try {
			Task task = updateTaskUseCase.execute(id, dto);
			return ResponseEntity.ok(taskMapper.toDTOBase(task));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", e.getMessage()));
		}
	}
	
	@GetMapping(path = "getTasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTasks(
        @RequestParam(required = false) String status,
        @RequestParam(required = false) String email) {
		
		try {
			List<Task> tasks = getTasksByFilterUseCase.execute((status != null && !"".equals(status.toString())) ? 
					status : null, email);
			return ResponseEntity.ok(taskMapper.toDTOList(tasks));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", e.getMessage()));
		}
		
	}
	
}
