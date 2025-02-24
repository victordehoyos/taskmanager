package com.vdehoyos.tmbackend.infraestructure.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.vdehoyos.tmbackend.domain.model.User;
import com.vdehoyos.tmbackend.infraestructure.persistence.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
	
	UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);

	public User toDomain(UserEntity entity);

	@InheritInverseConfiguration
	public UserEntity toEntity(User model);
	
	List<User> toDomainList(List<UserEntity> entities);
	List<UserEntity> toEntityList(List<User> domains);
	
}
