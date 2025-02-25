package com.vdehoyos.tmbackend.presentation.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.vdehoyos.tmbackend.application.dto.BaseTaskDTO;
import com.vdehoyos.tmbackend.application.dto.TaskFindResponseDTO;
import com.vdehoyos.tmbackend.application.dto.UpdateTaskDTO;
import com.vdehoyos.tmbackend.domain.model.Task;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface TaskMapper {

	TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);
	
	public Task toDomainBase(BaseTaskDTO dto);
	public Task toDTOBase(Task dto);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateTaskFromDto(UpdateTaskDTO dto, @MappingTarget Task task);
	
	public List<Task> toDomainList(List<BaseTaskDTO> dtos);
	public List<TaskFindResponseDTO> toDTOList(List<Task> domains);
	
}
