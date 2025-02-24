package com.vdehoyos.tmbackend.infraestructure.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.vdehoyos.tmbackend.domain.model.Role;
import com.vdehoyos.tmbackend.infraestructure.persistence.entity.RoleEntity;

@Mapper(componentModel = "spring")
public interface RoleEntityMapper {
	
	RoleEntityMapper INSTANCE = Mappers.getMapper(RoleEntityMapper.class);

	public Role toDomain(RoleEntity entity);

	@InheritInverseConfiguration
	public RoleEntity toEntity(Role domain);
	
	public List<Role> toDomainList(List<RoleEntity> entities);
	public List<RoleEntity> toEntityList(List<Role> domains);

}
