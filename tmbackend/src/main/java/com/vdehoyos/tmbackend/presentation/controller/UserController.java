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
import com.vdehoyos.tmbackend.application.dto.LoginDTO;
import com.vdehoyos.tmbackend.application.usecase.CreateUserUseCase;
import com.vdehoyos.tmbackend.application.usecase.FindAllRolesUseCase;
import com.vdehoyos.tmbackend.application.usecase.FindAllUser;
import com.vdehoyos.tmbackend.application.usecase.FindUserByEmailUseCase;
import com.vdehoyos.tmbackend.application.usecase.FindUserByRolUseCase;
import com.vdehoyos.tmbackend.domain.model.Role;
import com.vdehoyos.tmbackend.domain.model.User;
import com.vdehoyos.tmbackend.presentation.mapper.RoleMapper;
import com.vdehoyos.tmbackend.presentation.mapper.UserMapper;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
	
	private final CreateUserUseCase createUserUseCase;
	private final FindUserByRolUseCase findUserByRolUseCase;
	private final FindAllRolesUseCase findAllRolesUseCase;
	private final UserMapper userMapper;
	private final RoleMapper roleMapper;
	private final FindUserByEmailUseCase findUserByEmailUseCase;
	private final FindAllUser findAllUser;

	
	@PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> register(@RequestBody CreateUserDTO dto) {
		User user = createUserUseCase.execute(dto);
		return ResponseEntity.ok(userMapper.toDTO(user));
	}
	
	@GetMapping(path = "/role/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findByRole(@PathVariable Long roleId) {
		List<User> users = findUserByRolUseCase.execute(roleId);
		return ResponseEntity.ok(userMapper.toDTOList(users));
	}
	
	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findByEmail(@RequestBody LoginDTO dto) {
		User user = findUserByEmailUseCase.execute(dto.getEmail());
		return ResponseEntity.ok(userMapper.toDTOLogin(user));
	}
	
	@GetMapping(path = "/role/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllRoles() {
		List<Role> roles = findAllRolesUseCase.execute();
		return ResponseEntity.ok(roleMapper.toDTOList(roles));
	}
	
	@GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllUser() {
		List<User> users = findAllUser.execute();
		return ResponseEntity.ok(userMapper.toDTOList(users));
	}

}
