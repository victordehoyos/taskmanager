package com.vdehoyos.tmbackend.presentation.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.vdehoyos.tmbackend.application.dto.BaseUserDTO;
import com.vdehoyos.tmbackend.application.dto.CreateUserDTO;
import com.vdehoyos.tmbackend.application.dto.UserFindResponseDTO;
import com.vdehoyos.tmbackend.domain.model.Role;
import com.vdehoyos.tmbackend.domain.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mapping(source = "role.id", target = "roleId")
	public CreateUserDTO toDTO(User domain);

	public User toDomain(BaseUserDTO dto);
	
	@Mapping(source = "roleId", target = "role")
	public User toDomain(CreateUserDTO dto);
	
	default Role mapRole(Long roleId) {
		return new Role(roleId, null, null);
	}
	
	public List<UserFindResponseDTO> toDTOList(List<User> domains);
	
}
