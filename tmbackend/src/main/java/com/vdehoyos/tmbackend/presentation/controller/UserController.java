package com.vdehoyos.tmbackend.presentation.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vdehoyos.tmbackend.application.dto.CreateUserDTO;
import com.vdehoyos.tmbackend.application.usecase.CreateUserUseCase;
import com.vdehoyos.tmbackend.application.usecase.FindAllRolesUseCase;
import com.vdehoyos.tmbackend.application.usecase.FindUserByRolUseCase;
import com.vdehoyos.tmbackend.domain.model.Role;
import com.vdehoyos.tmbackend.domain.model.User;
import com.vdehoyos.tmbackend.presentation.mapper.RoleMapper;
import com.vdehoyos.tmbackend.presentation.mapper.UserMapper;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final CreateUserUseCase createUserUseCase;
	private final FindUserByRolUseCase findUserByRolUseCase;
	private final FindAllRolesUseCase findAllRolesUseCase;
	private final UserMapper userMapper;
	private final RoleMapper roleMapper;

	public UserController(CreateUserUseCase createUserUseCase, UserMapper userMapper, 
			FindUserByRolUseCase findUserByRolUseCase, FindAllRolesUseCase findAllRolesUseCase, RoleMapper roleMapper) {
		this.createUserUseCase = createUserUseCase;
		this.userMapper = userMapper;
		this.findUserByRolUseCase = findUserByRolUseCase;
		this.findAllRolesUseCase = findAllRolesUseCase;
		this.roleMapper = roleMapper;
	}
	
	@PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> register(@RequestBody CreateUserDTO dto) {
		User user = createUserUseCase.execute(dto);
		return ResponseEntity.ok(userMapper.toDTO(user));
	}
	
	@GetMapping(path = "/role/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> register(@PathVariable Long roleId) {
		List<User> users = findUserByRolUseCase.execute(roleId);
		return ResponseEntity.ok(userMapper.toDTOList(users));
	}
	
	@GetMapping(path = "/role/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllRoles() {
		List<Role> roles = findAllRolesUseCase.execute();
		return ResponseEntity.ok(roleMapper.toDTOList(roles));
	}

}
