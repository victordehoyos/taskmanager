package com.vdehoyos.tmbackend.infraestructure.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.vdehoyos.tmbackend.domain.model.Task;
import com.vdehoyos.tmbackend.infraestructure.persistence.entity.TaskEntity;

@Mapper(componentModel = "spring")
public interface TaskEntityMapper {
	
	TaskEntityMapper INSTANCE = Mappers.getMapper(TaskEntityMapper.class);
	
	public Task toDomain(TaskEntity entity);

	@InheritInverseConfiguration
	public TaskEntity toEntity(Task model);
	
	public List<Task> toDomainList(List<TaskEntity> entities);
	public List<TaskEntity> toEntityList(List<Task> domains);

}
