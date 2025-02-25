package com.vdehoyos.tmbackend.presentation.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.vdehoyos.tmbackend.application.dto.RoleDTO;
import com.vdehoyos.tmbackend.domain.model.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

	RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

	public RoleDTO toDTO(Role domain);

	@InheritInverseConfiguration
	public Role toDomain(RoleDTO dto);
	
	public List<RoleDTO> toDTOList(List<Role> domains);

}
